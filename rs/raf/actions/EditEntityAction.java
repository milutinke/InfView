package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditEntityView;
import rs.raf.view.frames.EditorFrame;

@SuppressWarnings("serial")
public class EditEntityAction extends InfViewAbstractAction {
	public EditEntityAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('u'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, "Edit entity");
		putValue(SHORT_DESCRIPTION, "Edit entity");

	}

	public void actionPerformed(ActionEvent arg0) {
		Packet packet = EditorFrame.getClone();
		InfViewNode entities = NodeHelper.findNode(packet, Entities.class, "Entities");
		String name = EditorFrame.getListOfentities().getSelectedItem().toString();
		InfViewNode entity = NodeHelper.findNode(entities, Entity.class, name);
		new EditEntityView((Entity) entity).setVisible(true);

	}
}
