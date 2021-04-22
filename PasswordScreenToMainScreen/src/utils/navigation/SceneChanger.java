package utils.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Used by stage manager to change screens
 * See stage manager for usage example
 */
public class SceneChanger {
    // This method will accept:
    //      the title of the new scene
    //      the .fxml file for the view
    //      and the ActionEvent that triggered the change

    /**
     * Changes scenes based on input parameters
     * @param event - Event passed in by controls like buttons
     * @param viewName - File name of view to open
     * @param title - String to set title to
     * @throws IOException
     */
    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));

        System.out.println(viewName);
        System.out.println(loader.getLocation());
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        // get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }


}
