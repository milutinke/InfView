package rs.raf.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import rs.raf.settings.Settings;

@SuppressWarnings("serial")
public abstract class InfViewAbstractAction extends AbstractAction {
	public static ImageIcon getIcon(String icon) {
		String fullPath = Settings.ICON_FOLDER_PATH + icon;

		if (!(new File(fullPath).exists())) {
			System.out.println("Warning: Icon " + fullPath + " does not exists!");
			return null;
		}

		return new ImageIcon(fullPath);
	}

	public void actionPerformed(ActionEvent arg0) {
	}
}