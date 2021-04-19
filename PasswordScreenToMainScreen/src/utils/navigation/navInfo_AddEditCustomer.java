package utils.navigation;

public class navInfo_AddEditCustomer extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Customer.fxml";
    }
    @Override String getScreenTitle() {
        return rb.getString("Add.Edit.Customer"); // "Add / Edit Customer";
    }
}
