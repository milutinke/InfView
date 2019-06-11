package rs.raf.view.panels;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	public MenuBar() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		fileMenu.add(MainFrame.getInstance().getActionManager().getLoadMetaSchemeAction());

		fileMenu.addSeparator();

		fileMenu.add(MainFrame.getInstance().getActionManager().getOpenEditorAction());
		fileMenu.addSeparator();

		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveMetaSchemeAction());

		fileMenu.addSeparator();

		fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

		add(fileMenu);

		JMenu about = new JMenu("About");
		about.add(MainFrame.getInstance().getActionManager().getAboutActon());

		add(Box.createHorizontalGlue());
		add(about);
	}
}