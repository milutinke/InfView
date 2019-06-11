package rs.raf.actions;

public class ActionManager {
	public AboutActon aboutActon;
	public HelpAction helpAction;
	public ExitAction exitAction;
	public AddRecordAction addRecordAction;
	public UpdateRecordAction updateRecordAction;
	public NewEntityAction newEntityAction;
	public DeleteEntityAction deleteEntityAction;
	public NewAttributeAction newAttributeAction;
	public EditAttributeAction editAttributeAction;
	public DeleteAttributeAction deleteAttributeAction;
	public LoadMetaSchemeAction loadMetaSchemeAction;
	public SaveMetaSchemeAction saveMetaSchemeAction;
	public NewRelationAction newRelationAction;
	public DeleteRelationAction deleteRelationAction;
	public EditRelationAction editRelationAction;
	public EditEntityAction editEntityAction;
	public OpenEditorAction openEditorAction;
	public SaveAndValidateAction saveAndValidateAction;
	public DeleteResourceAction deleteResourceAction;

	public ActionManager() {
		this.aboutActon = new AboutActon();
		this.helpAction = new HelpAction();
		this.exitAction = new ExitAction();
		this.addRecordAction = new AddRecordAction();
		this.updateRecordAction = new UpdateRecordAction();
		this.newEntityAction = new NewEntityAction();
		this.deleteEntityAction = new DeleteEntityAction();
		this.newAttributeAction = new NewAttributeAction();
		this.editAttributeAction = new EditAttributeAction();
		this.deleteAttributeAction = new DeleteAttributeAction();
		this.loadMetaSchemeAction = new LoadMetaSchemeAction();
		this.saveMetaSchemeAction = new SaveMetaSchemeAction();
		this.newRelationAction = new NewRelationAction();
		this.editRelationAction = new EditRelationAction();
		this.deleteRelationAction = new DeleteRelationAction();
		this.editEntityAction = new EditEntityAction();
		this.openEditorAction = new OpenEditorAction();
		this.saveAndValidateAction = new SaveAndValidateAction();
		this.deleteResourceAction = new DeleteResourceAction();
	}

	public AboutActon getAboutActon() {
		return aboutActon;
	}

	public HelpAction getHelpAction() {
		return helpAction;
	}

	public ExitAction getExitAction() {
		return exitAction;
	}

	public AddRecordAction getAddRecordAction() {
		return addRecordAction;
	}

	public UpdateRecordAction getUpdateRecordAction() {
		return updateRecordAction;
	}

	public NewEntityAction getNewEntityAction() {
		return newEntityAction;
	}

	public DeleteEntityAction getDeleteEntityAction() {
		return deleteEntityAction;
	}

	public NewAttributeAction getNewAttributeAction() {
		return newAttributeAction;
	}

	public EditAttributeAction getEditAttributeAction() {
		return editAttributeAction;
	}

	public DeleteAttributeAction getDeleteAttributeAction() {
		return deleteAttributeAction;
	}

	public LoadMetaSchemeAction getLoadMetaSchemeAction() {
		return loadMetaSchemeAction;
	}

	public SaveMetaSchemeAction getSaveMetaSchemeAction() {
		return saveMetaSchemeAction;
	}

	public NewRelationAction getNewRelationAction() {
		return newRelationAction;
	}

	public DeleteRelationAction getDeleteRelationAction() {
		return deleteRelationAction;
	}

	public EditRelationAction getEditRelationAction() {
		return editRelationAction;
	}

	public EditEntityAction getEditEntityAction() {
		return editEntityAction;
	}

	public OpenEditorAction getOpenEditorAction() {
		return openEditorAction;
	}

	public SaveAndValidateAction getSaveAndValidateAction() {
		return saveAndValidateAction;
	}

	public DeleteResourceAction getDeleResourceAction() {
		return deleteResourceAction;
	}
}