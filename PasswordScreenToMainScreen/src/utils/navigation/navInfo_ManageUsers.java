package utils.navigation;

public class navInfo_ManageUsers extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "ManageUsers.fxml";
    }
    @Override String getScreenTitle() {
        return "Manage Users";
    }
}
