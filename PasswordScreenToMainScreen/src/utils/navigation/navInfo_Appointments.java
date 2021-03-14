package utils.navigation;

public class navInfo_Appointments extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "ManageAppointments.fxml";
    }
    @Override String getScreenTitle() {
        return "Appointments";
    }
}
