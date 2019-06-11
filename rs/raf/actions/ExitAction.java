package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class ExitAction extends InfViewAbstractAction {
	public ExitAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('c'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_EXIT));
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit");
	}

	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().dispose();
	}
}