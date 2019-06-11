package rs.raf.actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import rs.raf.model.Entities;
import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.AddEntityView;
import rs.raf.view.frames.EditorFrame;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class NewEntityAction extends InfViewAbstractAction {
	public NewEntityAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('e'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, "New entity");
		putValue(SHORT_DESCRIPTION, "New entity");
	}

	public void actionPerformed(ActionEvent arg0) {
		Packet packet = EditorFrame.getClone();
		InfViewNode entities = NodeHelper.findNode(packet, Entities.class, "Entities");
		new AddEntityView(entities);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

	}
}