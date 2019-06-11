package rs.raf.utilities;

import javax.swing.JOptionPane;

import rs.raf.view.frames.MainFrame;

public class ExceptionsManager {
	private MainFrame mainFrame;

	public ExceptionsManager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public int doYouWantToDeleteEntitesWarning() {
		return JOptionPane.showConfirmDialog(mainFrame,
				"Entity is in relation to other entites, by deleting this entity, you will delete all relations which include this entity. Do you want to delete it?",
				"Delete Entity", JOptionPane.YES_NO_OPTION);
	}

	public int doYouWantToDeleteAttributesWarning() {
		return JOptionPane.showConfirmDialog(mainFrame,
				"Attribute is in multiple relation, by deleting this attribute, you will delete all relations which include this atributte. Do you want to delete it?",
				"Delete Attribue", JOptionPane.YES_NO_OPTION);
	}

	public void successfullyDeletedEntity(String name) {
		JOptionPane.showMessageDialog(mainFrame, "You have succesfully deleted entity: " + name, "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void successfullyDeletedRelationMessage(String name) {
		JOptionPane.showMessageDialog(mainFrame, "You have successfuly deleted the relation : " + name, "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void successfullyDeletedAttribute(String name) {
		JOptionPane.showMessageDialog(mainFrame, "You have succesfully deleted attribute: " + name, "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void successfullyDeletedRelation(String name) {
		JOptionPane.showMessageDialog(mainFrame, "You have succesfully deleted relation: " + name, "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorMustSelectEntityToDelete() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an entity in order to delete it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectEntityToEdit() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an entity in order to edit it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectRelationToEdit() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an relation in order to edit it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectAttributeToDelete() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an attribute in order to delete it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectAttributeToEdit() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an attribute in order to edit it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorCannotAddRelation() {
		JOptionPane.showMessageDialog(mainFrame, "Relation cannot be added", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorNoSelectedComponent() {
		JOptionPane.showMessageDialog(mainFrame, "No selected component", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorOnlyAddJsonFiles() {
		JOptionPane.showMessageDialog(mainFrame, "Only json files can be added", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectRelationToDelete() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an relation in order to delete it!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorThereIsNoTree() {
		JOptionPane.showMessageDialog(mainFrame, "There is no tree", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorPictureCantBeLoaded() {
		JOptionPane.showMessageDialog(mainFrame, "Image couldn't be loaded", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorEmptyFile() {
		JOptionPane.showMessageDialog(mainFrame, "The selected file is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMetaSchemaValidation(String name) {
		JOptionPane.showMessageDialog(mainFrame, "The following validation error occured: " + name, "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingEntities() {
		JOptionPane.showMessageDialog(mainFrame, "Missing enteties!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingRelations() {
		JOptionPane.showMessageDialog(mainFrame, "Missing relations!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingType() {
		JOptionPane.showMessageDialog(mainFrame, "Missing type!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingBaseObject() {
		JOptionPane.showMessageDialog(mainFrame, "No base object", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorNothingToSave() {
		JOptionPane.showMessageDialog(mainFrame, "No base object", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorParsingFiles() {
		JOptionPane.showMessageDialog(mainFrame, "Error parsing files", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectEntitiesToAddEntities() {
		JOptionPane.showMessageDialog(mainFrame, "You must select entities to add a new entity", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectRelationsToAddRelation() {
		JOptionPane.showMessageDialog(mainFrame, "You must select relations to add a new relation", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectAnEntityToAddAttributes() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an entity to add a new attribute", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorTreeEmpty() {
		JOptionPane.showMessageDialog(mainFrame, "Tree is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorFileFailToLoad() {
		JOptionPane.showMessageDialog(mainFrame, "File fail to load!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorNoMetascheme() {
		JOptionPane.showMessageDialog(mainFrame, "No metascheme!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorValidFileName() {
		JOptionPane.showMessageDialog(mainFrame, "Please enter a valid file name!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMustSelectResourceToEdit() {
		JOptionPane.showMessageDialog(mainFrame, "You must select an resource to open the editor", "Error!",
				JOptionPane.ERROR_MESSAGE);

	}

	public void errorSaveRecordInFile() {
		JOptionPane.showMessageDialog(mainFrame, "Cannot save records in file", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorAlreadyDeleted() {
		JOptionPane.showMessageDialog(mainFrame, "Already Deleted!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorFailedToDelete() {
		JOptionPane.showMessageDialog(mainFrame, "Failed to delete! - File System Error!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorYouMustSelectElementToDelete() {
		JOptionPane.showMessageDialog(mainFrame, "You must select row to delete!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void successfullyDeletedRow() {
		JOptionPane.showMessageDialog(mainFrame, "Succesfully delted!", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorNoResoults() {
		JOptionPane.showMessageDialog(mainFrame, "No results!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorClonedPackets() {
		JOptionPane.showMessageDialog(mainFrame, "Cloned packets do not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorResourceNotFound() {
		JOptionPane.showMessageDialog(mainFrame, "Parent resource not found!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorPacketNotPresent() {
		JOptionPane.showMessageDialog(mainFrame, "Packet is not present anymore!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorTmpFileCreation() {
		JOptionPane.showMessageDialog(mainFrame, "Failed to write the temporary file", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorClonedPacketNotPresent() {
		JOptionPane.showMessageDialog(mainFrame, "Cloned Packet is not present anymore", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void successMetaschemeSave() {
		JOptionPane.showMessageDialog(mainFrame, "Successfully saved the metascheme!", "Success!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorYouMustSelectElementToRestore() {
		JOptionPane.showMessageDialog(mainFrame, "You must select row to restore!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingLocation() {
		JOptionPane.showMessageDialog(mainFrame, "Missing location!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLHost() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL Host", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLDatabase() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL Database", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLUsername() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL Username", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLPassword() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL Password", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLPort() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL Port", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorSQLFailedConnection() {
		JOptionPane.showMessageDialog(mainFrame, "Could not connect to the SQL Server!", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void SQLSuccessfullyConnected(String host) {
		JOptionPane.showMessageDialog(mainFrame, "Successfully connected to the SQL Server (Host: " + host + ")",
				"Success!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorMissingSQLDriverClass() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL JDBC Driver Class", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLDriverSubprotocol() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL JDBC Driver Sub Protocol", "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorMissingSQLDriver() {
		JOptionPane.showMessageDialog(mainFrame, "Missing SQL JDBC Driver Name", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorNumFormat(String fieldName, String field) {
		JOptionPane.showMessageDialog(mainFrame, "Enter the type number in: " + fieldName + " for the field " + field,
				"Error!", JOptionPane.ERROR_MESSAGE);

	}

	public void errorDateFormat(String string) {
		JOptionPane.showMessageDialog(mainFrame, "Use the following format: " + string, "Error!",
				JOptionPane.ERROR_MESSAGE);

	}

	public void errorPathFormat() {
		JOptionPane.showMessageDialog(mainFrame, "File path invalid!", "Error!", JOptionPane.ERROR_MESSAGE);

	}

	public void invalidValue() {
		JOptionPane.showMessageDialog(mainFrame, "Enter the positive number!", "Error!", JOptionPane.ERROR_MESSAGE);

	}

	public void errorNotNullable(String fieldName) {
		JOptionPane.showMessageDialog(mainFrame, fieldName + " is requiered", "Error!", JOptionPane.ERROR_MESSAGE);

	}

	public void errorImage() {
		JOptionPane.showMessageDialog(mainFrame, "Image was not added to the database!", "Error!",
				JOptionPane.ERROR_MESSAGE);

	}

	public void errorNotDone() {
		JOptionPane.showMessageDialog(mainFrame,
				"The data base can not execute your action!.\nLikely: Primary key is already in use or the input is invalid!",
				"Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorFailedToFetchBlock() {
		JOptionPane.showMessageDialog(mainFrame, "Failed to fetch the block!", "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void errorEmptyFields() {
		JOptionPane.showMessageDialog(mainFrame, "Plese fill in all the fields!", "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void errorFailedToFilter() {
		JOptionPane.showMessageDialog(mainFrame, "Unknown error occured!", "Error!", JOptionPane.ERROR_MESSAGE);
	}
}