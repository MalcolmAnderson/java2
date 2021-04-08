package utils.navigation;

public class navInfo_LogIn extends navInfo{
    @Override String getFxmlFileName() {
        return pathToViews + "LoginScreen.fxml";
    }
    @Override String getScreenTitle() {
        String retVal = rb.getString("Acme.Appointment.Setter.version.1.0.0");
        return retVal;
    }
}
