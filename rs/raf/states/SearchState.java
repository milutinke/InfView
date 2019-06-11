package rs.raf.states;

import rs.raf.model.UIAbstractFile;
import rs.raf.view.frames.DefineRowView;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;
import rs.raf.view.panels.StatePanel;

public class SearchState extends State {
	private StatePanel mediator;

	public SearchState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		UIAbstractFile ui = fv.getFile();
		
		mediator.setMainPanel(new DefineRowView("Find record", ui.getFields()));
	}
}
