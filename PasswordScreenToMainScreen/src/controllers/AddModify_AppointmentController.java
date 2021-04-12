package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Globals;
import models.*;
import utils.Utils;
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
    public Label lblCustNumValue;
    public ChoiceBox cbCustomers;
    public Button btnSave;
    public Button btnCancel;

    private DAOAppointments dao = new DAOAppointments();
    private ResourceBundle rb;
    private Utils utils = new Utils();
    private Customers customers = Globals.getMasterCustomers();
    private Contacts contacts = Globals.getMasterContacts();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AddModify_AppointmentController - initialize");
        rb = Globals.getResourceBundle();

        // Completed subroutines
        HandleInboundCustomerObject();

        // TODO handle
        PopulateChoiceBoxes();
        SetSimpleScreenValues();
        SetButtonColors();
        LocalizeTextOnControlsAndHeaders();

    }

    private void PopulateChoiceBoxes() {
        LoadContactList();
        LoadCustomerList();
        LoadHours(cbStartHours);
        LoadHours(cbEndHours);
        LoadMinutes(cbStartMinutes);
        LoadMinutes(cbEndMinutes);
    }

    private void LoadCustomerList() {
        System.out.println("AddModify_AppointmentController - initialize");
        int customerCount = customers.getCustomers().size();
        Customer current;
        String customerIdAndName;
        for(int i = 0; i < customerCount; i++){
            current = customers.getCustomers().get(i);
            customerIdAndName = String.valueOf(current.getCustomer_ID()) + " - " + current.getCustomer_Name();
            cbCustomers.getItems().add(customerIdAndName);
        }
    }

    private void LoadContactList() {
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
        System.out.println("AddModify_AppointmentController HandleInboundCustomerObject called");

        if(appointment == null){
            if(addEdit == "EDIT"){
                System.out.println("Edit should not be able to submit a null Appointment");
                System.exit(-1);
            }
            appointment = new Appointment();
            appointment.setId(utils.getNextIdNumber());
        } else if (appointment.getId() == -1){
            appointment.setId(utils.getNextIdNumber());
        }
        if(addEdit == "ADD"){
            lblScreenIdentifier.setText("Add Appointment");
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText("Edit Appointment");
        }
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
