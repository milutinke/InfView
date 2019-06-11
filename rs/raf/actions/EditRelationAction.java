package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.model.Relation;
import rs.raf.model.Relations;
import rs.raf.settings.Settings;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditRelationView;
import rs.raf.view.frames.EditorFrame;

@SuppressWarnings("serial")
public class EditRelationAction extends InfViewAbstractAction {
	public EditRelationAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, "Edit relation");
		putValue(SHORT_DESCRIPTION, "Edit relation");
	}

	public void actionPerformed(ActionEvent arg0) {
		Packet packet = EditorFrame.getClone();
		InfViewNode relations = NodeHelper.findNode(packet, Relations.class, "Relations");
		String name = EditorFrame.getListOfrelations().getSelectedItem().toString();
		InfViewNode relation = NodeHelper.findNode(relations, Relation.class, name);

		new EditRelationView((Relation) relation);

	}
}