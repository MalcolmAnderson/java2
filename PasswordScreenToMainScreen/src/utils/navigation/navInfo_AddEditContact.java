package utils.navigation;

public class navInfo_AddEditContact extends navInfo {
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Contact.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        return rb.getString("Add.Edit.Contact"); // "Add / Edit Contact";
    }
}
