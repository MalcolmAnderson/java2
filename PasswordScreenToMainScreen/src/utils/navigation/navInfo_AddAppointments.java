package utils.navigation;

public class navInfo_AddAppointments extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddModify_Appointment.fxml";
    }
    @Override String getScreenTitle() {
        return "Add Appointments";
    }
}
