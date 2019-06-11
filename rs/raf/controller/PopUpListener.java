package rs.raf.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import rs.raf.model.Resource;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.MainFrame;

public class PopUpListener extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e) && NodeHelper.getLastSelectedComponentSafe() != null) {
			if (!(NodeHelper.getLastSelectedComponentSafe() instanceof Resource))
				return;

			JPopupMenu menu = new JPopupMenu();
			menu.add(MainFrame.getInstance().getActionManager().getDeleResourceAction());
			menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}