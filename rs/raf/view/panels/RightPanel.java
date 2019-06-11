package rs.raf.view.panels;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class RightPanel extends JSplitPane {
	private TopPanel topPanel;
	private StatePanel statePanel;

	public RightPanel() {
		super(JSplitPane.VERTICAL_SPLIT);

		this.topPanel = new TopPanel() {
			public boolean tabAboutToClose(int tabIndex) {
				String tab = topPanel.getTabTitleAt(tabIndex);

				int choice = JOptionPane.showConfirmDialog(null,
						"You are about to close '" + tab + "'\nDo you want to proceed ?", "Warning",
						JOptionPane.INFORMATION_MESSAGE);

				if (choice == 0)
					statePanel.setDefaultState();

				return choice == 0;
			}
		};
		
		this.statePanel = new StatePanel();

		setTopComponent(topPanel);
		setBottomComponent(statePanel);
		setOneTouchExpandable(true);
		setDividerLocation(450);
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}

	public StatePanel getStatePanel() {
		return statePanel;
	}
}
