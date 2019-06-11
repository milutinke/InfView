package rs.raf.states;

import rs.raf.model.Entity;
import rs.raf.model.UIAbstractFile;
import rs.raf.view.panels.StatePanel;

public class StateManager {
	@SuppressWarnings("unused")
	private State currentState = null;
	private State defaultState;
	private StatePanel mediator;

	private State searchState;
	private State sortState;
	private State binarySearchState;
	private State recycleBinState;
	private State relationViewState;
	private State addRecordState;
	private State updateRecordState;
	private State avgState;
	private State filterState;
	private State loadingState;

	public StateManager(StatePanel mediator) {
		this.mediator = mediator;
		this.defaultState = new DefaultState(mediator);
		this.searchState = new SearchState(mediator);
		this.sortState = new SortState(mediator);
		this.addRecordState = new AddRecordState(mediator);
		this.updateRecordState = new UpdateRecordState(mediator);
		this.avgState = new AvgState(mediator);
		this.loadingState = new LoadingState(mediator);
	}

	public void setDefaultState() {
		this.currentState = defaultState;
		defaultState.show();
	}

	public void setSearchState() {
		this.currentState = searchState;
		searchState.show();
	}

	public void setBinarySearchSate() {
		this.binarySearchState = new BinarySearchState(mediator);
		binarySearchState.show();
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void setSortState() {
		this.currentState = sortState;
		sortState.show();
	}

	public void setRecycleBinState() {
		this.recycleBinState = new RecycleBinState(mediator);
		recycleBinState.show();
	}

	public void setRelationViewState(Entity entity) {
		this.relationViewState = new RelationViewState(mediator, entity);
		relationViewState.show();
	}

	public void setAddRecordState() {
		this.currentState = addRecordState;
		addRecordState.show();
	}

	public void setUpdateRecordState() {
		this.currentState = updateRecordState;
		updateRecordState.show();
	}

	public void setAvgState() {
		this.currentState = avgState;
		avgState.show();
	}

	public void setFilterState(UIAbstractFile uiFile, Entity entity) {
		this.filterState = new FilterState(mediator, uiFile, entity);
		filterState.show();
	}

	public void setLoadingState() {
		this.currentState = loadingState;
		loadingState.show();
	}
}