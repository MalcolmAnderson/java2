package utils.navigation;

/**
 * Nav info for Add Edit Appointment screen
 */
public class navInfo_AddEditAppointment extends navInfo {
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Appointment.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        return rb.getString("Add.Edit.Appointment"); //  "Add / Edit Appointment";
    }
}
