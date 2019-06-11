package rs.raf.states;

import javax.swing.JPanel;

import rs.raf.view.panels.StatePanel;

public class DefaultState extends State {
	private StatePanel mediator;

	public DefaultState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		JPanel panel = new JPanel();
		mediator.setMainPanel(panel);
	}
}
