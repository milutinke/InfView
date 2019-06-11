package rs.raf.view.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import rs.raf.model.Entity;
import rs.raf.model.UIAbstractFile;
import rs.raf.states.StateManager;

@SuppressWarnings("serial")
public class StatePanel extends JPanel {
	private StateManager stateManager = new StateManager(this);
	private JPanel mainPanel = new JPanel();

	public StatePanel() {
		this.setLayout(new BorderLayout());
		this.add(mainPanel);
		this.setVisible(true);
		this.validate();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.remove(this.mainPanel);
		this.mainPanel = mainPanel;
		this.add(mainPanel);
		this.validate();
	}

	public void setDefaultState() {
		stateManager.setDefaultState();
	}

	public void setSearchState() {
		stateManager.setSearchState();
	}

	public void setSortState() {
		stateManager.setSortState();
	}

	public void setBinarySearchState() {
		stateManager.setBinarySearchSate();
	}

	public void setRelationViewState(Entity entity) {
		stateManager.setRelationViewState(entity);
	}

	public void setRecyleBinState() {
		stateManager.setRecycleBinState();
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public void setAddRecordState() {
		stateManager.setAddRecordState();

	}

	public void setUpdateRecordState() {
		stateManager.setUpdateRecordState();
	}

	public void setAvgState() {
		stateManager.setAvgState();
	}

	public void setFilterState(UIAbstractFile uiFile, Entity entity) {
		stateManager.setFilterState(uiFile, entity);
	}

	public void setLoadingState() {
		stateManager.setLoadingState();
	}
}
