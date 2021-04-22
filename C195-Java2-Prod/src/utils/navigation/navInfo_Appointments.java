package utils.navigation;

public class navInfo_Appointments extends navInfo {
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "Main_ViewAppointments.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        return rb.getString("Appointments"); // "Appointments";
    }
}
