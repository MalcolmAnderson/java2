package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Appointments;
import main.Globals;
import utils.dataAccess.DBConnection;

import javax.naming.spi.DirectoryManager;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

public class Main extends Application {

    // TODO - Change login_activity.txt to point to current directory
    // TODO - Change password checker to check the database for passwords


    public static void main(String[] args) {
        // set environment
        Globals.setHasLoginBeenAttempted(false);
        Globals.setDetectedSystemLocalLanguage(Locale.getDefault().toString());
        File myFile = new File("utils.localization/Nat");
        System.out.println("My file exists: " + myFile.exists());
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


    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle rb = Globals.getResourceBundle();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        loader.setResources(rb);
        Parent loginScreenParent = loader.load();
        Scene loginScreenScene = new Scene(loginScreenParent);

        primaryStage.setTitle(rb.getString(rb.getString("Acme.Appointment.Setter.version.0.0.1")));
        primaryStage.setScene(loginScreenScene);
        primaryStage.show();
    }
}
