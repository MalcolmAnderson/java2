package utils.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        // Directory path method one
//            String cwd = "Working Directory = " + System.getProperty("user.dir");
//            System.out.println(cwd);
        // Directory path method two
//            File file = new File("");
//            String currentPath = file.getAbsolutePath();
//            System.out.println("Current path is:: " + currentPath);
        // Directory path method three
//            Path currentDirectoryPath = Paths.get("").toAbsolutePath();
//            String currentPath = currentDirectoryPath.toString();
//            System.out.println("Current directory path:: " + currentPath);
        // Directory path method four
//        FileSystem fileSystem = FileSystems.getDefault();
//        Path path = fileSystem.getPath("").toAbsolutePath();
//        String currentDirectoryPath = path.toString();
//        System.out.println("Current directory path:: " + currentDirectoryPath);


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        // get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }


}
