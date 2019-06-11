package rs.raf.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.model.Resource;
import rs.raf.model.Workspace;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.tree.WorkspaceModel;

public class MetaSchemeUtils {
	public static void deSerialize(String location) {
		String metaScheme;

		try {
			// Load JSON to string from the file
			metaScheme = new String(Files.readAllBytes(Paths.get(location)), Charset.defaultCharset());
		} catch (IOException e) {
			MainFrame.getInstance().getExceptionsManager().errorFileFailToLoad();
			return;
		}

		if (metaScheme.length() == 0) {
			MainFrame.getInstance().getExceptionsManager().errorEmptyFile();
			return;
		}

		// Parse the JSON
		JSONObject mainObject = new JSONObject(metaScheme);

		// Stop on unsuscessfull parsing
		if (mainObject == null || mainObject.equals(JSONObject.NULL)) {
			MainFrame.getInstance().getExceptionsManager().errorParsingFiles();
			return;
		}

		// Stop if there is no base object
		if (!mainObject.has("resource")) {
			MainFrame.getInstance().getExceptionsManager().errorMissingBaseObject();
			return;
		}

		boolean isSql = false;
		JSONObject resourceObject = mainObject.getJSONObject("resource");

		// Stop if the type is missing
		if (!resourceObject.has("type")) {
			MainFrame.getInstance().getExceptionsManager().errorMissingType();
			return;
		}

		String resourceLocation = "";

		if (resourceObject.getString("type").equals("file")
				|| resourceObject.getString("type").equals("file-relational")) {
			if (!resourceObject.has("location")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingLocation();
				return;
			} else
				resourceLocation = resourceObject.getString("location");
		} else if (resourceObject.getString("type").equals("sql")) {
			if (!resourceObject.has("sql_jdbc_driver_class")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriverClass();
				return;
			} else {
				if (resourceObject.getString("sql_jdbc_driver_class").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriverClass();
					return;
				}
			}

			if (!resourceObject.has("sql_jdbc_driver_subprotocol")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriverSubprotocol();
				return;
			} else {
				if (resourceObject.getString("sql_jdbc_driver_subprotocol").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriverSubprotocol();
					return;
				}
			}

			if (!resourceObject.has("sql_jdbc_driver_name")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriver();
				return;
			} else {
				if (resourceObject.getString("sql_jdbc_driver_name").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLDriver();
					return;
				}
			}

			if (!resourceObject.has("sql_host")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLHost();
				return;
			} else {
				if (resourceObject.getString("sql_host").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLHost();
					return;
				}
			}

			if (!resourceObject.has("sql_database")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLDatabase();
				return;
			} else {
				if (resourceObject.getString("sql_database").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLDatabase();
					return;
				}
			}

			if (!resourceObject.has("sql_username")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLUsername();
				return;
			} else {
				if (resourceObject.getString("sql_username").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLUsername();
					return;
				}
			}

			if (!resourceObject.has("sql_password")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLPassword();
				return;
			} else {
				if (resourceObject.getString("sql_password").length() == 0) {
					MainFrame.getInstance().getExceptionsManager().errorMissingSQLPassword();
					return;
				}
			}

			if (!resourceObject.has("sql_port")) {
				MainFrame.getInstance().getExceptionsManager().errorMissingSQLPort();
				return;
			}

			isSql = true;
		} else if (resourceObject.getString("type").equals("no-sql")) {
		}

		Resource resource;

		if (!isSql)
			resource = new Resource(resourceObject.getString("name"), resourceLocation,
					resourceObject.getString("type"), null, null, null, null, 0);
		else
			resource = new Resource(resourceObject.getString("name"), resourceLocation,
					resourceObject.getString("type"), resourceObject.getString("sql_host"),
					resourceObject.getString("sql_database"), resourceObject.getString("sql_username"),
					resourceObject.getString("sql_password"), resourceObject.getInt("sql_port"));

		if (isSql) {
			Connection connection = null;

			MainFrame.getInstance().getRightPanel().getStatePanel().setLoadingState();
			try {
				connection = SQLUtilities.openConnection(resourceObject.getString("sql_jdbc_driver_class"),
						resourceObject.getString("sql_jdbc_driver_subprotocol"),
						resourceObject.getString("sql_jdbc_driver_name"), resourceObject.getString("sql_host"),
						resourceObject.getString("sql_database"), resourceObject.getString("sql_username"),
						resourceObject.getString("sql_password"), resourceObject.getInt("sql_port"));
			} catch (ClassNotFoundException | JSONException | SQLException e) {
				e.printStackTrace();
				MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
				MainFrame.getInstance().getExceptionsManager().errorSQLFailedConnection();
				return;
			}

			if (connection != null) {
				resource.setSqlConnection(connection);
				MainFrame.getInstance().getExceptionsManager()
						.SQLSuccessfullyConnected(resourceObject.getString("sql_host"));
			}

			MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
		}

		ParsePackets(resource, resourceObject);
		((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot()).addChild(resource);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

		if (resourceObject.getString("type").equals("sql")
				|| resourceObject.getString("type").equals("file-relational")) {
			ParsePacketRelations(resource, resourceObject);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
		}
	}

	private static boolean ParsePackets(Resource resource, JSONObject resourceObject) {
		// Return false if there are no packets
		if (!resourceObject.has("packets"))
			return false;

		// Get entites array
		JSONArray packetsObject = (JSONArray) resourceObject.getJSONArray("packets");

		// Return false if there are no packets
		if (packetsObject == null || packetsObject.equals(JSONObject.NULL))
			return false;

		// Iterate trough packets
		for (int iterator = 0; iterator < packetsObject.length(); iterator++) {
			// Get the current object
			JSONObject packetObject = packetsObject.getJSONObject(iterator);

			// Skip invalid objects
			if (packetObject == null || packetObject.equals(JSONObject.NULL))
				continue;

			// Create packet node
			Packet packet = new Packet(packetObject.getString("name"), !packetObject.has("location") ? null
					: (resource.getSqlDatabase() != null ? null : packetObject.getString("location")));

			if (!ParseEntites(packet, packetObject)) {
				MainFrame.getInstance().getExceptionsManager().errorMissingEntities();
				continue;
			}

			resource.addChild((Packet) packet);
		}

		return true;
	}

	private static boolean ParsePacketRelations(Resource resource, JSONObject resourceObject) {
		// Return false if there are no packets
		if (!resourceObject.has("packets"))
			return false;

		// Get entites array
		JSONArray packetsObject = (JSONArray) resourceObject.getJSONArray("packets");

		// Return false if there are no packets
		if (packetsObject == null || packetsObject.equals(JSONObject.NULL))
			return false;

		// Iterate trough packets
		for (int iterator = 0; iterator < packetsObject.length(); iterator++) {
			// Get the current object
			JSONObject packetObject = packetsObject.getJSONObject(iterator);

			// Skip invalid objects
			if (packetObject == null || packetObject.equals(JSONObject.NULL))
				continue;

			Packet packet = (Packet) NodeHelper.findNode(
					((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot()), null,
					packetObject.getString("name"));

			if (packet == null) {
				System.out.println("Could not find packet: " + packetObject.getString("name"));
				continue;
			}

			if (!ParseRelations(packet, packetObject)) {
				MainFrame.getInstance().getExceptionsManager().errorMissingRelations();
				continue;
			}
		}

		return true;
	}

	private static boolean ParseEntites(Packet packet, JSONObject packetObject) {
		// Return false if there are no entites
		if (!packetObject.has("entities"))
			return false;

		// Create entities
		Entities entities = new Entities("Entities");

		// Get entites array
		JSONArray entitesObject = (JSONArray) packetObject.getJSONArray("entities");

		// Return false if there are no entites
		if (entitesObject == null || entitesObject.equals(JSONObject.NULL))
			return false;

		// iterate trough entites
		for (int iterator = 0; iterator < entitesObject.length(); iterator++) {
			// Get the current object
			JSONObject entityObject = entitesObject.getJSONObject(iterator);

			// Skip invalid objects
			if (entityObject == null || entityObject.equals(JSONObject.NULL))
				continue;

			if (!entityObject.has("name"))
				continue;

			if (!entityObject.has("attributes"))
				continue;

			// Create New Entity
			Entity entity = new Entity(entityObject.getString("name"),
					packet.getLocation() == null ? null : entityObject.getString("location"),
					packet.getLocation() == null ? null : entityObject.getString("file"));

			// Parse attributes
			ParseAttributes(entity, entityObject);

			// Add Entity
			entities.addChild(entity);
		}

		packet.addChild((Entities) entities);
		return true;
	}

	private static boolean ParseRelations(Packet packet, JSONObject packetObject) {
		// Return false if there are no entites
		if (!packetObject.has("relations")) {
			System.out.println("No object: relations");
			return false;
		}

		// Create Relations
		Relations relations = new Relations("Relations");

		// Get entites array
		JSONArray relationsObject = (JSONArray) packetObject.getJSONArray("relations");

		// Return false if there are no entites
		if (relationsObject == null || relationsObject.equals(JSONObject.NULL)) {
			System.out.println("Relations JSON object in java = NULL");
			return false;
		}

		// iterate trough entites
		for (int iterator = 0; iterator < relationsObject.length(); iterator++) {
			// Get the current object
			JSONObject relationObject = relationsObject.getJSONObject(iterator);

			// Skip invalid objects
			if (relationObject == null || relationObject.equals(JSONObject.NULL)) {
				System.out.println("Iteration: " + iterator + "relation object in java = NULL");
				continue;
			}

			if (!relationObject.has("entity1") || !relationObject.has("entity2") || !relationObject.has("foreign_key_1")
					|| !relationObject.has("foreign_key_2")) {
				System.out.println("[" + iterator + "] Missing one of the parameters");
				continue;
			}

			String entity1Value = relationObject.getString("entity1");
			String entity2Value = relationObject.getString("entity2");

			if (entity1Value.length() == 0) {
				System.out.println("[" + iterator + "] Missing entity1");
				continue;
			}

			if (entity2Value.length() == 0) {
				System.out.println("[" + iterator + "] Missing entity2");
				continue;
			}

			String key1 = relationObject.getString("foreign_key_1");
			String key2 = relationObject.getString("foreign_key_2");

			if (key1.length() == 0) {
				System.out.println("[" + iterator + "] Missing key1");
				continue;
			}

			if (key2.length() == 0) {
				System.out.println("[" + iterator + "] Missing key2");
				continue;
			}

			InfViewNode packetNode = NodeHelper.findNode(
					((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot()),
					Packet.class, packet.getName());

			Entity entity1 = (Entity) NodeHelper.findNode(packetNode, Entity.class, entity1Value.trim());
			Entity entity2 = (Entity) NodeHelper.findNode(packetNode, Entity.class, entity2Value.trim());

			if (entity1 == null || entity2 == null) {
				System.out.println("[" + iterator + "] Entity null - entity1: " + entity1 + " - entity2: " + entity2);
				continue;
			}

			Attribute attribute1 = (Attribute) NodeHelper.findNode(entity1, Attribute.class, key1.trim());
			Attribute attribute2 = (Attribute) NodeHelper.findNode(entity2, Attribute.class, key2.trim());

			if (attribute1 == null || attribute2 == null) {
				System.out.println(
						"E1 " + entity1Value + " - E2 " + entity2Value + " Key check for - " + key1 + " and " + key2);
				System.out.println(
						"[" + iterator + "] Keys null - key1: " + attribute1 + " - key2: " + attribute2 + "\n");
				continue;
			}

			relations.addChild((Relation) new Relation(entity1, entity2, attribute1, attribute2));
		}

		packet.addChild((Relations) relations);

		return true;
	}

	private static boolean ParseAttributes(Entity entity, JSONObject entityObject) {
		// Return false if there are no attributes
		if (!entityObject.has("attributes"))
			return false;

		// Get attributes array
		JSONArray attributes = (JSONArray) entityObject.getJSONArray("attributes");

		// Return false if there are no entites
		if (attributes == null || attributes.equals(JSONObject.NULL))
			return false;

		// iterate trough attributes
		for (int iterator = 0; iterator < attributes.length(); iterator++) {
			// Get the current object
			JSONObject attribute = attributes.getJSONObject(iterator);

			// Skip invalid objects
			if (attribute == null || attribute.equals(JSONObject.NULL))
				continue;

			if (!attribute.has("name") || !attribute.has("dataType") || !attribute.has("isPrimaryKey")
					|| !attribute.has("isForeignKey") || !attribute.has("isMandatory") || !attribute.has("defaultValue")
					|| !attribute.has("length"))
				continue;

			// Add Attribute
			Attribute newAttribute = new Attribute(attribute.getString("name"));

			newAttribute.withDataType(attribute.getString("dataType"));
			newAttribute.withPrimaryKey(attribute.getBoolean("isPrimaryKey"));
			newAttribute.withForeignKey(attribute.getBoolean("isForeignKey"));
			newAttribute.withMandatory(attribute.getBoolean("isMandatory"));
			newAttribute.withDefaultValue((Object) attribute.get("defaultValue"));
			newAttribute.withLength(attribute.get("length"));

			entity.addChild(((Attribute) newAttribute).build());
		}

		return true;
	}

	public static Boolean validate(String metaMetaSchemeLocation, String metaSchemeLocation) {
		JSONObject metaMetaSchemaJson;
		JSONObject schemaJson;

		try {
			metaMetaSchemaJson = loadJsonFromFile(metaMetaSchemeLocation);
			schemaJson = loadJsonFromFile(metaSchemeLocation);
		} catch (FileNotFoundException e) {
			System.out.println("File error! " + e.getMessage());
			return false;
		}

		Schema metaMetaSchema = SchemaLoader.load(metaMetaSchemaJson);

		try {
			MainFrame.getInstance().getRightPanel().getStatePanel().setLoadingState();
			metaMetaSchema.validate(schemaJson);
			MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
		} catch (ValidationException e) {
			e.getCausingExceptions().forEach(msg -> System.out.println(msg));
			System.out.println(e.getErrorMessage());
			MainFrame.getInstance().getExceptionsManager().errorMetaSchemaValidation(e.getErrorMessage());
			MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
			return false;
		}

		return true;
	}

	public static JSONObject loadJsonFromFile(String fileName) throws FileNotFoundException {
		Reader reader = new FileReader(fileName);
		return new JSONObject(new JSONTokener(reader));
	}

	public static void serialize(String location) throws FileNotFoundException {
		String metaScheme = MetaSchemeUtils.serialize();

		if (metaScheme.length() == 0)
			return;

		PrintWriter printWriter = new PrintWriter(new File(location));
		printWriter.println(metaScheme);
		printWriter.close();
	}

	public static String serialize() {
		// Create JSON objects
		JSONObject baseObject = new JSONObject();
		JSONObject resourceObject = new JSONObject();

		// If the tree is empty, skip
		if (MainFrame.getInstance().getTree() == null) {
			MainFrame.getInstance().getExceptionsManager().errorTreeEmpty();
			return "";
		}

		// Get the Workspace
		InfViewNode rootNode = ((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot());

		// Get Current Resource
		Resource resource = (Resource) NodeHelper.findNode(rootNode, Resource.class, "");

		// Check if the resource exists
		if (resource == null) {
			MainFrame.getInstance().getExceptionsManager().errorNothingToSave();
			return "";
		}

		// Set resourse json object properties
		resourceObject.put("name", resource.getName());
		resourceObject.put("location", resource.getLocation());
		resourceObject.put("type", resource.getType());

		// Iterate trought packets and serialize
		Enumeration<Packet> packets = (Enumeration<Packet>) resource.children();

		JSONArray packetsArray = new JSONArray();

		if (packets != null) {
			while (packets.hasMoreElements()) {
				Packet packet = (Packet) packets.nextElement();

				if (packet == null)
					continue;

				JSONObject packetObject = new JSONObject();
				packetObject.put("name", packet.getName());
				packetObject.put("location", packet.getLocation());

				Entities entities = (Entities) NodeHelper.findNode(packet, Entities.class, "");

				if (entities == null) {
					MainFrame.getInstance().getExceptionsManager().errorMissingEntities();
					return "";
				}

				packetObject.put("entities", entitiesToJson(entities));

				Relations relations = (Relations) NodeHelper.findNode(packet, Relations.class, "");

				if (relations == null) {
					MainFrame.getInstance().getExceptionsManager().errorMissingRelations();
					return "";
				}

				packetObject.put("relations", relationsToJson(relations));
				packetsArray.put(packetObject);
			}
		}

		resourceObject.put("packets", packetsArray);
		baseObject.put("resource", resourceObject);

		return baseObject.toString(2);
	}

	public static JSONArray entitiesToJson(InfViewNode root) {
		if (root == null)
			return null;

		JSONArray entitiesArray = new JSONArray();

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Entity entity = (Entity) children.nextElement();

				JSONObject entityObject = new JSONObject();
				entityObject.put("name", entity.getName());
				entityObject.put("location", entity.getLocation());
				entityObject.put("file", entity.getFile());
				entityObject.put("attributes", attributesToJson(entity));

				entitiesArray.put(entityObject);
			}
		}

		return entitiesArray;
	}

	public static JSONArray attributesToJson(InfViewNode root) {
		if (root == null)
			return null;

		JSONArray attributesArray = new JSONArray();

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Attribute attribute = (Attribute) children.nextElement();

				JSONObject attributeObject = new JSONObject();
				attributeObject.put("name", attribute.getName());
				attributeObject.put("dataType", attribute.getDataType());
				attributeObject.put("isPrimaryKey", attribute.isPrimaryKey());
				attributeObject.put("isForeignKey", attribute.isForeignKey());
				attributeObject.put("isMandatory", attribute.isMandatory());
				attributeObject.put("defaultValue", attribute.getDefaultValue());
				attributeObject.put("length", attribute.getLength());

				attributesArray.put(attributeObject);
			}
		}

		return attributesArray;
	}

	public static JSONArray relationsToJson(InfViewNode root) {
		if (root == null)
			return null;

		JSONArray relationsArray = new JSONArray();

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) root.children();
		Entities entities = (Entities) NodeHelper.findNode(
				((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot()), Entities.class,
				"");

		if (entities == null) {
			System.out.println("Entites = null");
			return null;
		}

		if (children != null) {
			while (children.hasMoreElements()) {
				Relation relation = (Relation) children.nextElement();

				if (relation.getEntity1() == null || relation.getEntity2() == null) {
					System.out.println("Relation: " + relation.getName() + " - missing - e1: " + relation.getEntity1()
							+ " - e2: " + relation.getEntity2());
					continue;
				}

				Entity entity1 = (Entity) NodeHelper.findNode(entities, Entity.class, relation.getEntity1().getName());
				Entity entity2 = (Entity) NodeHelper.findNode(entities, Entity.class, relation.getEntity2().getName());

				if (entity1 == null || entity2 == null) {
					System.out.println("Relation: " + relation.getName() + " - NULL Entity - entity1: " + entity1
							+ " - entity2: " + entity2);
					continue;
				}

				if (relation.getKey1() == null || relation.getKey2() == null) {
					System.out.println("Relation: " + relation.getName() + " - MISSING KEY - key1: "
							+ relation.getKey1() + " - key2: " + relation.getKey2());
					continue;
				}

				Attribute attribute1 = (Attribute) NodeHelper.findNode(entity1, Attribute.class,
						relation.getKey1().getName());
				Attribute attribute2 = (Attribute) NodeHelper.findNode(entity1, Attribute.class,
						relation.getKey2().getName());

				if (attribute1 == null || attribute2 == null) {
					System.out.println("Relation: " + relation.getName() + " - NULL KEY - key1: " + attribute1
							+ " - key2: " + attribute2);
					continue;
				}

				JSONObject relationObject = new JSONObject();

				relationObject.put("entity1", entity1.getName());
				relationObject.put("entity2", entity2.getName());
				relationObject.put("foreign_key_1", attribute1.getName());
				relationObject.put("foreign_key_2", attribute2.getName());

				relationsArray.put(relationObject);
			}
		}

		return relationsArray;
	}

	public static ArrayList<Entity> getEntityRelations(Entity entity) {
		if (entity == null)
			return null;

		ArrayList<Entity> relatedEntities = new ArrayList<Entity>();

		Relations relations = (Relations) NodeHelper.findNode(
				((Workspace) ((WorkspaceModel) MainFrame.getInstance().getTree().getModel()).getRoot()),
				Relations.class, "");

		if (relations == null)
			return null;

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) ((InfViewNode) relations).children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Relation relation = (Relation) children.nextElement();

				if (relation == null || relation.getEntity1() == null || relation.getEntity2() == null)
					continue;

				if (relation.getEntity1().getName().equals(entity.getName()))
					relatedEntities.add(relation.getEntity2());
				else if (relation.getEntity2().getName().equals(entity.getName()))
					relatedEntities.add(relation.getEntity1());
			}
		}

		return (ArrayList<Entity>) relatedEntities.stream().distinct().collect(Collectors.toList());
	}

	public static ArrayList<Attribute> getEntityAttributes(Entity entity, ArrayList<String> allowedTypes) {
		if (entity == null)
			return null;

		ArrayList<Attribute> attributes = new ArrayList<Attribute>();

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) ((InfViewNode) entity).children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Attribute attribute = (Attribute) children.nextElement();

				if (allowedTypes != null) {
					if (allowedTypes.contains(attribute.getDataType().toLowerCase()))
						attributes.add(attribute);
				} else
					attributes.add(attribute);
			}
		}

		return attributes;
	}
}