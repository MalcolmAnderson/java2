package utils_Navigation;

public class navInfo_Appointments extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "Appointments.fxml";
    }
    @Override String getScreenTitle() {
        return "Appointments";
    }
}
