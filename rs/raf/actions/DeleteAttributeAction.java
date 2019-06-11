package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;

import rs.raf.model.Attribute;
import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.settings.Settings;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditorFrame;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class DeleteAttributeAction extends InfViewAbstractAction {
	public DeleteAttributeAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_DELETE));
		putValue(NAME, "Delete Attribute");
		putValue(SHORT_DESCRIPTION, "Delete Attribute");
	}

	public void actionPerformed(ActionEvent arg0) {
		String nameOfEntity = EditorFrame.getListOfentities().getSelectedItem().toString();
		InfViewNode entities = NodeHelper.findNode(EditorFrame.getClone(), Entities.class, "Entities");
		InfViewNode entity = NodeHelper.findNode(entities, Entity.class, nameOfEntity);

		String nameOfAttribute = EditorFrame.getListOfattributes().getSelectedItem().toString();
		InfViewNode selectedNode = NodeHelper.findNode(entity, Attribute.class, nameOfAttribute);

		if (selectedNode == null)
			return;

		if (!(selectedNode instanceof Attribute)) {
			MainFrame.getInstance().getExceptionsManager().errorMustSelectAttributeToDelete();
			return;
		}

		String nodeName = selectedNode.getName();

		ArrayList<Relation> relations = getRelationsWhichUseAttribute((Attribute) selectedNode);

		if (relations != null && relations.size() > 0) {
			if (MainFrame.getInstance().getExceptionsManager().doYouWantToDeleteAttributesWarning() == 1)
				return;
		}

		// Delete all relations containing this attribute and parent entity
		relations.forEach(relation -> relation.removeFromParent());

		for (Relation r : relations)
			EditorFrame.getListOfrelations().removeItem(r.getName());

		// Delete the attribute it self
		EditorFrame.getListOfattributes().removeItem(selectedNode.getName());
		selectedNode.removeFromParent();

		MainFrame.getInstance().getTree().setSelectionPath(null);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
		MainFrame.getInstance().setSaved(false);

		MainFrame.getInstance().getExceptionsManager().successfullyDeletedAttribute(nodeName);

		// Close all opened tabs
		NodeHelper.closeDeletedNodesTabs(nodeName);
	}

	private ArrayList<Relation> getRelationsWhichUseAttribute(Attribute attribute) {
		if (attribute == null)
			return null;

		ArrayList<Relation> relationsForDeletion = new ArrayList<Relation>();

		Relations relations = (Relations) NodeHelper.findNode(EditorFrame.getClone(), Relations.class, "Relations");

		if (relations == null)
			return null;

		@SuppressWarnings("unchecked")
		Enumeration<InfViewNode> children = (Enumeration<InfViewNode>) ((InfViewNode) relations).children();

		if (children != null) {
			while (children.hasMoreElements()) {
				Relation relation = (Relation) children.nextElement();

				if (relation == null || relation.getEntity1() == null || relation.getEntity2() == null)
					continue;

				Entity entity = (Entity) attribute.getParent();

				if (entity == null)
					continue;

				if (relation.getEntity1().getName().equals(entity.getName())) {
					if (relation.getKey1().getName().equals(attribute.getName())
							|| relation.getKey2().getName().equals(attribute.getName()))
						relationsForDeletion.add(relation);
				} else if (relation.getEntity2().getName().equals(entity.getName()))
					if (relation.getKey1().getName().equals(attribute.getName())
							|| relation.getKey2().getName().equals(attribute.getName()))
						relationsForDeletion.add(relation);
			}
		}

		return relationsForDeletion;
	}
}