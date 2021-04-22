package utils.navigation;

public class navInfo_AddEditCustomer extends navInfo {
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Customer.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        return rb.getString("Add.Edit.Customer"); // "Add / Edit Customer";
    }
}
