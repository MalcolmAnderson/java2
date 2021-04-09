package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import models.Appointment;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModify_AppointmentController implements Initializable {

    public static String addEdit;
    public static Appointment appointment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }
}
