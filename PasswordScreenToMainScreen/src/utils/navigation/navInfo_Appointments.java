package utils.navigation;

public class navInfo_Appointments extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "Main_ViewAppointments.fxml";
    }
    @Override String getScreenTitle() {
        return "Appointments";
    }
}
