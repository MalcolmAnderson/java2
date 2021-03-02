package utils_Navigation;

public class navInfo_AddAppointments extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "AddAppointments.fxml";
    }
    @Override String getScreenTitle() {
        return "Add Appointments";
    }
}
