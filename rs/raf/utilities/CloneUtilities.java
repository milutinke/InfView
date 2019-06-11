package rs.raf.utilities;

import java.util.Enumeration;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;

public class CloneUtilities {

	private static Entities cloneEntities;

	public CloneUtilities() {
	}

	public static Packet get(Packet packet) throws CloneNotSupportedException {
		Packet clonePacket = new Packet(packet.getName(), packet.getLocation());
		Entities entities = (Entities) NodeHelper.findNode(packet, Entities.class, "Entities");

		cloneEntities = new Entities(entities.getName());

		getEntityClones(cloneEntities, entities);

		Relations relations = (Relations) NodeHelper.findNode(packet, Relations.class, "Relations");
		Relations cloneRelations = new Relations(relations.getName());
		getRelationClones(cloneRelations, relations);

		clonePacket.addChild(cloneEntities);
		clonePacket.addChild(cloneRelations);

		return clonePacket;

	}

	public static void getEntityClones(Entities cloneEntities, Entities entities) throws CloneNotSupportedException {
		Enumeration<Entity> children = (Enumeration<Entity>) entities.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Entity node = (Entity) children.nextElement();
				cloneEntities.addChild(cloneEntity(node));
			}
		}

	}

	public static void getRelationClones(Relations cloneRelations, Relations relations)
			throws CloneNotSupportedException {
		Enumeration<Relation> children = (Enumeration<Relation>) relations.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Relation node = (Relation) children.nextElement();
				Relation clone = cloneRelation(node);
				cloneRelations.addChild(clone);
			}
		}

	}

	public static Relation cloneRelation(Relation node) throws CloneNotSupportedException {
		String e1Name = node.getEntity1().getName();
		String e2Name = node.getEntity2().getName();

		Entity e1 = (Entity) NodeHelper.findNode(cloneEntities, Entity.class, e1Name);
		Entity e2 = (Entity) NodeHelper.findNode(cloneEntities, Entity.class, e2Name);

		String key1Name = node.getKey1().getName();
		String key2Name = node.getKey2().getName();

		Attribute key1 = (Attribute) NodeHelper.findNode(e1, Attribute.class, key1Name);
		Attribute key2 = (Attribute) NodeHelper.findNode(e2, Attribute.class, key2Name);

		Relation clone = new Relation(e1, e2, key1, key2);
		clone.setName(node.getName());
		return clone;
	}

	public static Entity cloneEntity(Entity entity1) throws CloneNotSupportedException {
		Entity clone = new Entity(entity1.getName(), entity1.getLocation(), entity1.getFile());
		Enumeration<Attribute> children = (Enumeration<Attribute>) entity1.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Attribute node = (Attribute) children.nextElement();
				clone.addChild((Attribute) node.clone());
			}
		}

		return clone;
	}

}
