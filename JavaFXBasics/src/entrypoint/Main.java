package entrypoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init(){
        System.out.println("init() method");
    }

    @Override
    public void stop(){
        System.out.println("stop() method");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start() method");
        Parent root = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("main() method");
        launch(args);
    }
}
