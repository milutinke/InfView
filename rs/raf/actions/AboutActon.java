package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.view.frames.AboutUsFrame;

@SuppressWarnings("serial")
public class AboutActon extends InfViewAbstractAction {
	public AboutActon() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('?'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_ABOUT));
		putValue(NAME, "About program");
		putValue(SHORT_DESCRIPTION, "About program");
	}

	public void actionPerformed(ActionEvent arg0) {
		AboutUsFrame frame = new AboutUsFrame();
		frame.setVisible(true);
		frame.toFront();
		frame.repaint();
	}
}