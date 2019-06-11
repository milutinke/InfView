package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;

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
public class DeleteEntityAction extends InfViewAbstractAction {
	public DeleteEntityAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_DELETE));
		putValue(NAME, "Delete Entity");
		putValue(SHORT_DESCRIPTION, "Delete Entity");
	}

	public void actionPerformed(ActionEvent arg0) {

		String name = EditorFrame.getListOfentities().getSelectedItem().toString();
		InfViewNode entities = NodeHelper.findNode(EditorFrame.getClone(), Entities.class, "Entities");

		InfViewNode selectedNode = NodeHelper.findNode(entities, Entity.class, name);

		if (selectedNode == null)
			return;

		if (!(selectedNode instanceof Entity)) {
			MainFrame.getInstance().getExceptionsManager().errorMustSelectEntityToDelete();
			return;
		}

		ArrayList<Relation> relations = getRelations((Entity) selectedNode);

		if (relations != null && relations.size() > 0) {
			if (MainFrame.getInstance().getExceptionsManager().doYouWantToDeleteEntitesWarning() == 1)
				return;
		}

		String nodeName = selectedNode.getName();

		// Delete all relations containing this entity
		relations.forEach(relation -> relation.removeFromParent());

		// update in editor
		for (Relation r : relations)
			EditorFrame.getListOfrelations().removeItem(r.getName());

		// Delete the entity itself and update in editor
		EditorFrame.getListOfentities().removeItem(selectedNode.getName());
		EditorFrame.getListOfattributes().removeAllItems(); // removing attributes of entity
		selectedNode.removeFromParent();

		// Update tree
		MainFrame.getInstance().getTree().setSelectionPath(null);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
		MainFrame.getInstance().setSaved(false);

		MainFrame.getInstance().getExceptionsManager().successfullyDeletedEntity(nodeName);

		// Close all opened tabs
		NodeHelper.closeDeletedNodesTabs(nodeName);
	}

	private ArrayList<Relation> getRelations(Entity entity) {
		if (entity == null)
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

				if (relation.getEntity1().getName().equals(entity.getName()))
					relationsForDeletion.add(relation);
				if (relation.getEntity2().getName().equals(entity.getName()))
					relationsForDeletion.add(relation);
			}
		}

		return relationsForDeletion;
	}
}