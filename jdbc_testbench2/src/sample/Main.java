package sample;

import dbAccess.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Chicken");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        System.out.println("Egg");
        try {
            DBQuery.setStatement(DBConnection.startConnection());
            Statement statement = DBQuery.getStatement();
            // Raw SQL statement
            String insertStatement = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) ";
            insertStatement += " VALUES ('USSR', '2021-02-23 00:00:00', 'admin', 'admin' )";

            // Execute Statement
            Boolean isASelectStatement = statement.execute(insertStatement);
            System.out.println("isASelectStatement = " + isASelectStatement.toString());
            if( ! isASelectStatement){
                System.out.println("Impacted records = " + statement.getUpdateCount());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //DBCountries.checkDateConversion();


        launch(args);
        DBConnection.endConnection();
    }
}
