package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Appointments;
import models.ManageTestData;

import java.io.IOException;

public class FxmlNavigationTools {

    public Appointments appointments = ManageTestData.BuildPlaceHolderData();


    Stage stage;
    Parent scene;

    public void openMainScreenWhileGettingAppointments(
            ActionEvent event,
            String viewNameAndPath) {
        // Assumes that the event variable is a button object
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewNameAndPath));
            Parent mainScreenParent = loader.load();
            Scene mainScreenScene = new Scene(mainScreenParent);

            // get controller and load data
            Appointments_Controller mainScreenController = loader.getController();
//            mainScreenController.loadInventory(inv);
            mainScreenController.LoadAppointments();
            stage.setTitle("Appointments");

            stage.setScene(mainScreenScene);
            stage.show();
        } catch (IOException ioe) {
            // I don't care
            System.out.println("viewNameAndPath probably not found - viewNameAndPath: " + viewNameAndPath);
        }
    }

}
