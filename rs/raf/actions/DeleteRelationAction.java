package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import rs.raf.model.InfViewNode;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.settings.Settings;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditorFrame;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class DeleteRelationAction extends InfViewAbstractAction {
	public DeleteRelationAction() {
		putValue(SMALL_ICON, getIcon(Settings.ICON_DELETE));
		putValue(NAME, "Delete Relation");
		putValue(SHORT_DESCRIPTION, "Delete Relation");
	}

	public void actionPerformed(ActionEvent arg0) {

		String name = EditorFrame.getListOfrelations().getSelectedItem().toString();
		InfViewNode relations = NodeHelper.findNode(EditorFrame.getClone(), Relations.class, "Relations");
		InfViewNode selectedNode = NodeHelper.findNode(relations, Relation.class, name);

		Relation relation = (Relation) NodeHelper.findNode(selectedNode, Relation.class, name);

		if (relation == null)
			return;

		String nodeName = selectedNode.getName();

		selectedNode.removeFromParent();

		// remove from editor

		EditorFrame.getListOfrelations().removeItem(selectedNode.getName());

		MainFrame.getInstance().getTree().setSelectionPath(null);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
		MainFrame.getInstance().setSaved(false);

		MainFrame.getInstance().getExceptionsManager().successfullyDeletedRelation(nodeName);

		// Close all opened tabs
		NodeHelper.closeDeletedNodesTabs(nodeName);
	}
}