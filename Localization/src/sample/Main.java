package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        loader.setResources(ResourceBundle.getBundle("sample/Nat", Locale.getDefault()));
        Parent root = loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Locale french = new Locale("fr", "FR");
        Locale spanish = new Locale("es", "ES");
        Locale german = Locale.GERMAN;
        Locale english = Locale.US;
        System.out.println(Locale.getDefault());

//        Scanner keyboard = new Scanner(System.in);
//
//        System.out.print("Enter a language (de, es, fr): ");
//        String languageCode = keyboard.nextLine();
//
//        if(languageCode.equals("fr")){
//            Locale.setDefault(french);
//        }else if (languageCode.equals("es")){
//            Locale.setDefault(spanish);
//        }else if (languageCode.equals("de")){
//            Locale.setDefault(german);
//        } else {
//            System.out.println("Language not supported");
//            System.exit(0);
//        }
        Locale.setDefault(french);
        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
        System.out.println(rb.getString("hello") + " " + rb.getString("world"));

        launch(args);


        System.exit(0);

    }

    public static void firstHello(){
        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
//        if(Locale.getDefault().getLanguage().equals("de") ||
//                Locale.getDefault().getLanguage().equals("es") ||
//                Locale.getDefault().getLanguage().equals("fr")){
//            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
//        }
        //System.out.println("hello world");
    }
}
