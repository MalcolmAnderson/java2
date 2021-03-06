package utils.navigation;

public class navInfo_ManageContacts extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "ManageContacts.fxml";
    }
    @Override String getScreenTitle() {
        return "Manage Contacts";
    }
}
