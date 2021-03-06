package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Appointments;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    // TODO - Change login_activity.txt to point to current directory
    // TODO - Change password checker to check the database for passwords
    ResourceBundle rb = ResourceBundle.getBundle("localization/Nat", Locale.getDefault());

    Appointments appointments;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        loader.setResources(rb);
        Parent loginScreenParent = loader.load();
        Scene loginScreenScene = new Scene(loginScreenParent);


        primaryStage.setTitle(rb.getString("Acme.Appointment.Setter.version.0.0.1"));
        primaryStage.setScene(loginScreenScene);
        primaryStage.show();



    }


    public static void main(String[] args) {
        Globals.setHasLoginBeenAttempted(false);
        Globals.setLocalLanguage(Locale.getDefault().toString());
        // make data connection

        launch(args);
        System.exit(0);
    }



    }
