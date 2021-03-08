package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Video from
// https://wgu.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=9969ffe1-8a4e-4d6c-aa1d-abbd01057497
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Players.fxml"));
        primaryStage.setTitle("Basketball All Stars");
        primaryStage.setScene(new Scene(root, 1400, 675));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
