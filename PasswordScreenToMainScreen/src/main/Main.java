package main;

import controllers.FxmlNavigationTools;
import controllers.LoginScreen_Controller;
import controllers.MainScreen_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Appointments;
import models.ManageTestData;

public class Main extends Application {

    Appointments appointments;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        Parent loginScreenParent = loader.load();
        Scene loginScreenScene = new Scene(loginScreenParent);

        primaryStage.setTitle("Acme Appointment Setter version 0.0.1");
        primaryStage.setScene(loginScreenScene);
        primaryStage.show();



    }


    public static void main(String[] args) {

        //FxmlNavigationTools navTools = new FxmlNavigationTools();

        // make data connection

        launch(args);
    }



    }
