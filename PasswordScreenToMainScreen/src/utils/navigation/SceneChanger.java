package utils.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Globals;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SceneChanger {
    // This method will accept:
    //      the title of the new scene
    //      the .fxml file for the view
    //      and the ActionEvent that triggered the change

    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
//        loader.setResources(Globals.getResourceBundle());

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
