package sample;

import dbAccess.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Chicken");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args)  {
        String innerTest = "test";
        String testing = String.format("This is a %s", innerTest);
        System.out.println(testing);

        DBConnection.startConnection();




        //JunkDrawer jd = new JunkDrawer();
        JunkCountryManager jcm = new JunkCountryManager();
        jcm.setCountryName("USSR");
        jcm.RunInsert();

        System.out.println("Egg");

//        DBCountries.getCountryCount();
//        DBCountries.getCountryColumnCount();

        //jd.addUSSRToDB();

        launch(args);
        DBConnection.endConnection();
    }
}
