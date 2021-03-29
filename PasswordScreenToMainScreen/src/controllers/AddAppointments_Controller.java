package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import models.Contact;
import utils.Utils;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointments_Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }
}
