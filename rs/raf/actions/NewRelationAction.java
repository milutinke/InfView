package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.model.Packet;
import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.view.frames.AddRelationView;
import rs.raf.view.frames.EditorFrame;

@SuppressWarnings("serial")
public class NewRelationAction extends InfViewAbstractAction {
	public NewRelationAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('r'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_NEW));
		putValue(NAME, "New relation");
		putValue(SHORT_DESCRIPTION, "New relation");
	}

	public void actionPerformed(ActionEvent arg0) {
		Packet packet = EditorFrame.getClone();
		new AddRelationView(packet);
	}
}