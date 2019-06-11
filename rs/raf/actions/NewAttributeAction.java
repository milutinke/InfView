package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.model.Entities;
import rs.raf.model.Entity;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.AddAttributeView;
import rs.raf.view.frames.EditorFrame;

@SuppressWarnings("serial")
public class NewAttributeAction extends InfViewAbstractAction {
	public NewAttributeAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('a'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, "New atribute");
		putValue(SHORT_DESCRIPTION, "New atribute");
	}

	public void actionPerformed(ActionEvent arg0) {
		Packet packet = EditorFrame.getClone(); // base
		InfViewNode entities = NodeHelper.findNode(packet, Entities.class, "Entities");
		String nameofEntity = EditorFrame.getListOfentities().getSelectedItem().toString();
		InfViewNode entity = NodeHelper.findNode(entities, Entity.class, nameofEntity);
		new AddAttributeView((Entity) entity);
	}
}