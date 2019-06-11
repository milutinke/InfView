package rs.raf.states;

import rs.raf.model.UIAbstractFile;
import rs.raf.view.frames.DefineSort;
import rs.raf.view.frames.DefineSortDB;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;
import rs.raf.view.panels.StatePanel;

public class SortState extends State {
	private StatePanel mediator;

	public SortState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		UIAbstractFile ui = fv.getFile();
		
		if (ui.toString().contains(".db"))
			mediator.setMainPanel(new DefineSortDB("Sort", ui.getFields()));
		else
			mediator.setMainPanel(new DefineSort("Sort", ui.getFields()));
	}
}
