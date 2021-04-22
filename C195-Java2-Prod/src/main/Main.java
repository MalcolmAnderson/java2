package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.dataAccess.DBConnection;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    /** Sets global variables and then starts the login screen.
     * Opens and closes the database connection.
     * @param args - input parameters */
    public static void main(String[] args) {
        // set environment
        Globals.setHasLoginBeenAttempted(false);
        Globals.setDetectedSystemLocalLanguage(Locale.getDefault().toString());
        String rbPath = "utils.localization/Nat";
        ResourceBundle rb = ResourceBundle.getBundle(rbPath, Locale.getDefault());
        Globals.setResourceBundle(rb);

        // make data connection and start app
        DBConnection.startConnection();
        launch(args);

        // shut down data connection
        DBConnection.endConnection();
        System.exit(0);
    }

    /** Entry point to the login screen. */
    @Override public void start(Stage primaryStage) throws Exception{
        ResourceBundle rb = Globals.getResourceBundle();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        loader.setResources(rb);
        Parent loginScreenParent = loader.load();
        Scene loginScreenScene = new Scene(loginScreenParent);

//        primaryStage.setTitle(rb.getString(rb.getString("Acme.Appointment.Setter.version.1.0.0")));
        primaryStage.setScene(loginScreenScene);
        primaryStage.show();
    }
}
