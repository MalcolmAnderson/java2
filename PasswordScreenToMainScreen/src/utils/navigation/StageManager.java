package utils.navigation;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Used to change scenes.  below is the entire snipit from the main screen for loading the reports screen.
 *
 * It was decided to use polymorphism rather than a switch statement to manage the moving pieces.
 * Commented out below the ChangeScene method was the original switch version
 *
 *     public void onClick_Reports(ActionEvent event) {
 *         StageManager.ChangeScene(event, new navInfo_Reports());
 *     }
 */
public class StageManager {


    /**
     * Takes the default action item from a button, and a navInfo object to open a new screen
     * @param event - Java FX infrastructure object passed by buttons and other controls.
     * @param scene - navInfo object
     */
    public static void ChangeScene(ActionEvent event, navInfo scene ) {
        SceneChanger sc = new SceneChanger();
        try {
            sc.changeScenes(event, scene.getFxmlFileName(), scene.getScreenTitle());
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

//    public static void ChangeScene(ActionEvent event, screens val ) {
//        String fxmlFileName = "invalidFXMLFileName";
//        String screenTitle = "invalidScreenTitle";
//        switch(val){
//            case Data_1:
//                fxmlFileName = "Data_1.fxml";
//                screenTitle = "Data_1_Screen";
//                break;
//            case Submenu_2:
//                fxmlFileName = "Submenu_2.fxml";
//                screenTitle = "Submenu 2 Screen";
//                break;
//            case Main:
//                fxmlFileName = "sample.fxml";
//                screenTitle = "Hello World";
//                break;
//            default:
//                System.out.println("FATAL error, unrecognized val");
//                Platform.exit();
//        }
//        try {
//            sc.changeScenes(event, fxmlFileName, screenTitle);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Platform.exit();
//        }
//    }
}

