package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public Label lblScreenIdentifier;
    public Label lblAppointmentNum;
    public TextField txtTitle;
    public TextField txtDescription;
    public TextField txtLocation;
    public TextField txtType;
    public DatePicker dpStart;
    public ChoiceBox cbStartHours;
    public ChoiceBox cbStartMinutes;
    public DatePicker dpEnd;
    public ChoiceBox cbEndHours;
    public ChoiceBox cbEndMinutes;
    public ChoiceBox cbCustomerId;
    public ChoiceBox cbCustomerName;
    public Button btnSave;
    public Button btnCancel;

    private DAOAppointments dao = new DAOAppointments();
    private ResourceBundle rb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ManageCustomers_Controller - initialize");
        rb = Globals.getResourceBundle();

        HandleInboundCustomerObject();
        SetSimpleScreenValues();
        SetButtonColors();
        LocalizeTextOnControlsAndHeaders();
        LoadHours(cbStartHours);
        LoadHours(cbEndHours);
        LoadMinutes(cbStartMinutes);
        LoadMinutes(cbEndMinutes);

    }

    private void SetSimpleScreenValues() {
        System.out.println("AddModify_AppointmentController SetSimpleScreenValues called");
        lblAppointmentNum.setText(String.valueOf(appointment.getId()));
        txtTitle.setText(appointment.getTitle());
        txtDescription.setText(appointment.getDescription());
        txtLocation.setText(appointment.getLocation());
        txtType.setText(appointment.getType());

    }

    private void HandleInboundCustomerObject() {
    }

    private void LoadMinutes(ChoiceBox cbStartMinutes) {
    }

    private void LoadHours(ChoiceBox cbStartHours) {
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
