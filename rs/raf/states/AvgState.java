package rs.raf.states;

import rs.raf.model.UIAbstractFile;
import rs.raf.view.frames.AvgView;
import rs.raf.view.frames.MainFrame;
import rs.raf.view.panels.FileView;
import rs.raf.view.panels.StatePanel;

public class AvgState extends State {
	private StatePanel mediator;

	public AvgState(StatePanel mediator) {
		this.mediator = mediator;
	}

	@Override
	public void show() {
		FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
		UIAbstractFile ui = fv.getFile();

		if (ui.toString().contains(".db"))
			mediator.setMainPanel(new AvgView("AVG", ui.getFields()));
	}
}
