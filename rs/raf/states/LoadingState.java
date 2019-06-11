package rs.raf.states;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rs.raf.actions.InfViewAbstractAction;
import rs.raf.settings.Settings;
import rs.raf.view.panels.StatePanel;

public class LoadingState extends State {
	private StatePanel mediator;

	public LoadingState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, SwingUtilities.CENTER));

		panel.add(Box.createHorizontalGlue());

		JLabel loadingGif = new JLabel(InfViewAbstractAction.getIcon(Settings.ICON_LOADING));
		loadingGif.setBounds(668, 43, 46, 14);
		panel.add(loadingGif);

		JLabel label = new JLabel("Loading...");
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
		panel.add(label);

		panel.add(Box.createHorizontalGlue());

		mediator.setMainPanel(panel);
	}

}
