package rs.raf.actions;

import java.awt.event.ActionEvent;

import rs.raf.model.InfViewNode;
import rs.raf.model.Packet;
import rs.raf.settings.Settings;
import rs.raf.utilities.AcceleratorHelper;
import rs.raf.utilities.NodeHelper;
import rs.raf.view.frames.EditorFrame;
import rs.raf.view.frames.MainFrame;

@SuppressWarnings("serial")
public class OpenEditorAction extends InfViewAbstractAction {

	public OpenEditorAction() {
		putValue(ACCELERATOR_KEY, AcceleratorHelper.getFormatedAccelerator('r'));
		putValue(SMALL_ICON, getIcon(Settings.ICON_OPEN));
		putValue(NAME, "Open editor");
		putValue(SHORT_DESCRIPTION, "Open editor");
	}

	public void actionPerformed(ActionEvent arg0) {
		InfViewNode packet = NodeHelper.getLastSelectedComponentSafe();

		if (packet == null)
			return;

		if (!(packet instanceof Packet)) {
			MainFrame.getInstance().getExceptionsManager().errorMustSelectResourceToEdit();
			return;
		}

		MainFrame.getInstance().getRightPanel().getStatePanel().setDefaultState();
		for (int openedTabs = 0; openedTabs < MainFrame.getInstance().getRightPanel().getTopPanel()
				.getTabCount(); openedTabs++)
			MainFrame.getInstance().getRightPanel().getTopPanel().removeTabAt(openedTabs);

		new EditorFrame((Packet) packet);
	}
}
