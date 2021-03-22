package utils.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Contact;

import java.io.IOException;

public class navInfo_ManageContacts extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "ManageContacts.fxml";
    }
    @Override String getScreenTitle() {
        return "Manage Contacts";
    }

    public void open_AddModify_Contact_WhilePassingCurrentContact(
            ActionEvent event,
            String viewNameAndPath,
            String transactionType,
            Contact currentContact) {
        // Assumes that the event variable is a button object
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewNameAndPath));
            Parent mainScreenParent = loader.load();
            Scene mainScreenScene = new Scene(mainScreenParent);

            //ConfigurePartController(loader, inv, transactionType, currentContact);
            //ConfigureAndShowStage(stage, mainScreenScene, transactionType, "Part");
        } catch (IOException ioe) {
            System.out.println("viewNameAndPath probably not found - viewNameAndPath: " + viewNameAndPath);
        }
    }
    private void ConfigureAndShowStage(
            Stage stage, Scene mainScreenScene,
            String transactionType){
        stage.setTitle("Manage Contacts - " + transactionType + " "  + " Screen");
        stage.setScene(mainScreenScene);
        stage.show();
    }

}
