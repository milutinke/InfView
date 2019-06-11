package rs.raf.states;

import rs.raf.view.panels.DefineBinarySearchView;
import rs.raf.view.panels.StatePanel;

public class BinarySearchState extends State {
	private StatePanel meditator;

	public BinarySearchState(StatePanel meditator) {
		this.meditator = meditator;
	}

	@Override
	public void show() {
		meditator.setMainPanel(new DefineBinarySearchView());
	}
}
