package rs.raf.states;

import rs.raf.view.panels.RecycleBinView;
import rs.raf.view.panels.StatePanel;

public class RecycleBinState extends State {
	StatePanel mediator;

	public RecycleBinState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		mediator.setMainPanel(new RecycleBinView());
	}
}
