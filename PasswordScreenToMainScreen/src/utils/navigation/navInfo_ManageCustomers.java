package utils.navigation;

public class navInfo_ManageCustomers extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "ManageCustomers.fxml";
    }
    @Override String getScreenTitle() {
        return rb.getString("Manage.Customers");
    }
}
