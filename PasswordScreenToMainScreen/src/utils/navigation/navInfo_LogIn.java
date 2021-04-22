package utils.navigation;

public class navInfo_LogIn extends navInfo{
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "LoginScreen.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        String retVal = rb.getString("Acme.Appointment.Setter.version.1.0.0");
        return retVal;
    }
}
