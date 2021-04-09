package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import main.Globals;
import models.Appointment;
import utils.dataAccess.DAOAppointments;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModify_AppointmentController implements Initializable {

    public static String addEdit;
    public static Appointment appointment;

    private DAOAppointments dao = new DAOAppointments();
    private ResourceBundle rb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ManageCustomers_Controller - initialize");
        rb = Globals.getResourceBundle();

        SetButtonColors();
        LocalizeTextOnControlsAndHeaders();
        BindDataToTableView();

    }

    private void BindDataToTableView() {
    }

    private void LocalizeTextOnControlsAndHeaders() {
    }

    private void SetButtonColors() {
    }

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_Save(ActionEvent actionEvent) {
    }
}
