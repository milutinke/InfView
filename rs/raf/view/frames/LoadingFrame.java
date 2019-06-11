package rs.raf.view.frames;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rs.raf.actions.InfViewAbstractAction;
import rs.raf.settings.Settings;

@SuppressWarnings("serial")
public class LoadingFrame extends JFrame {
	public LoadingFrame() {
		super();
		initGui();
	}

	private void initGui() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel(InfViewAbstractAction.getIcon(Settings.ICON_LOADING)));
		panel.setOpaque(false);
		setUndecorated(true);
		setContentPane(panel);
		setBackground(new Color(0, 0, 0, 0));
		setSize(200, 300);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void close() {
		this.dispose();
	}
}
