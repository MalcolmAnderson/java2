package main;

import controllers.LoginScreen_Controller;
import controllers.MainScreen_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("../views/LoginScreen.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        Parent loginScreenParent = loader.load();
        Scene loginScreenScene = new Scene(loginScreenParent);

        // get controller and load data
        //LoginScreen_Controller loginScreen_controller = loader.getController();
        //loginScreen_controller.LoadInventory(inv);

        primaryStage.setTitle("Acme Appointment Setter version 0.0.1");
        primaryStage.setScene(loginScreenScene);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
