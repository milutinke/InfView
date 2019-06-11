package rs.raf.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import rs.raf.view.panels.AboutPanel;

@SuppressWarnings("serial")
public class AboutUsFrame extends JDialog {
	public AboutUsFrame() {
		init();
	}

	private void init() {
		this.setSize(new Dimension(500, 500));
		this.setTitle("About us");

		this.setModal(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		this.add(new AboutPanel());
		this.toFront();
	}
}