package utils.navigation;

public class navInfo_AddEditContacts extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Contacts.fxml";
    }
    @Override String getScreenTitle() {
        return "Add / Edit Contacts";
    }
}
