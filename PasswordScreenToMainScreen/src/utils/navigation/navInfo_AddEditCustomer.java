package utils.navigation;

public class navInfo_AddEditCustomer extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Customer.fxml";
    }
    @Override String getScreenTitle() {
        return "Add / Edit Customer";
    }
}
