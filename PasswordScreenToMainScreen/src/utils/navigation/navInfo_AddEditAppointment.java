package utils.navigation;

public class navInfo_AddEditAppointment extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Appointment.fxml";
    }
    @Override String getScreenTitle() {
        return rb.getString("Add.Edit.Appointment"); //  "Add / Edit Appointment";
    }
}
