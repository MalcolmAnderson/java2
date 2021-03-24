package utils.navigation;

public class navInfo_AddEditContact extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Contact.fxml";
    }
    @Override String getScreenTitle() {
        return "Add / Edit Contact";
    }
}
