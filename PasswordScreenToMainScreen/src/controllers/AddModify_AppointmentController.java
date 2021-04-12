package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Globals;
import models.*;
import utils.Utils;
import utils.dataAccess.DAOAppointments;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;
import utils.navigation.navInfo_ManageCustomers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public ChoiceBox cbCustomers = new ChoiceBox();
    public ChoiceBox cbContacts = new ChoiceBox();
    public Button btnSave;
    public Button btnCancel;
    public Label lblContactNumValue;

    private DAOAppointments dao = new DAOAppointments();
    private ResourceBundle rb;
    private Utils utils = new Utils();
    private Customers customers = Globals.getMasterCustomers();
    private Contacts contacts = Globals.getMasterContacts();
    private int currentCustomerNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AddModify_AppointmentController - initialize");
        rb = Globals.getResourceBundle();
        System.out.println("Current Appointment : " + appointment.toString());

        // Completed subroutines
        HandleInboundCustomerObject();

        // in progress
        PopulateChoiceBoxes();

        // TODO handle
        SetSimpleScreenValues();
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
        System.out.println("Entering AddModify_AppointmentController - LoadCustomerList");
        int customerCount = customers.getCustomers().size();
        Customer current;
        int chosenCustomerNumber = appointment.getCustomer_Id();
        int currentCustomerNumber = -1;
        for(int i = 0; i < customerCount; i++){
            current = customers.getCustomers().get(i);
            currentCustomerNumber = current.getCustomer_ID();
            cbCustomers.getItems().add(current);
            if(currentCustomerNumber == chosenCustomerNumber){
                chosenCustomerNumber = i;
            }
        }
        cbCustomers.getSelectionModel().select(chosenCustomerNumber);
    }

    private void LoadContactList() {
        // TODO get contacts working
        System.out.println("Entering AddModify_AppointmentController - LoadContactList");
        int contactCount = contacts.getContacts().size();
        Contact current;
        int chosenContactNumber = appointment.getContact_Id();
        int currentContactNumber = -1;
        for(int i = 0; i < contactCount; i++){
            current = contacts.getContacts().get(i);
            currentContactNumber = current.getContact_ID();
            cbContacts.getItems().add(current);
            if(currentContactNumber == chosenContactNumber){
                chosenContactNumber = i;
            }
        }
        cbContacts.getSelectionModel().select(chosenContactNumber);
    }

    private void SetSimpleScreenValues() {
        System.out.println("AddModify_AppointmentController SetSimpleScreenValues called");
        lblAppointmentNum.setText(String.valueOf(appointment.getId()));
        txtTitle.setText(appointment.getTitle());
        txtDescription.setText(appointment.getDescription());
        txtLocation.setText(appointment.getLocation());
        txtType.setText(appointment.getType());
        lblCustNumValue.setText(String.valueOf(appointment.getCustomer_Id()));
        lblContactNumValue.setText(String.valueOf(appointment.getCustomer_Id()));
        System.out.println("start: " + appointment.getStart());
        System.out.println("end: " +
                appointment.getEnd());
        SetDateAndTime(appointment.getStart(), dpStart, cbStartHours, cbStartMinutes);
        SetDateAndTime(appointment.getEnd(), dpEnd, cbEndHours, cbEndMinutes);
    }

    private void SetDateAndTime(LocalDateTime ldt, DatePicker datePicker, ChoiceBox cbHours, ChoiceBox cbMinutes) {
        if(ldt == null){
            datePicker.setValue(LocalDateTime.now().toLocalDate());
            cbHours.getSelectionModel().select(0);
            cbMinutes.getSelectionModel().select(0);
        } else {
            datePicker.setValue(ldt.toLocalDate());
            int startHour = ldt.getHour();
            cbHours.getSelectionModel().select(startHour);
            int startMinute = ldt.getMinute();
            for (int i = 0; i < 12; i++) {
                int curMin = Integer.parseInt((String) cbMinutes.getItems().get(i));
                if (curMin == startMinute) {
                    cbMinutes.getSelectionModel().select(i);
                }
            }
        }
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

    private void LoadMinutes(ChoiceBox cbMinutes) {
        for(int i = 0; i < 12; i++){
            cbMinutes.getItems().add(String.format("%02d", i * 5));
        }
    }

    private void LoadHours(ChoiceBox cbHours) {
        for(int i = 0; i < 23; i++){
            cbHours.getItems().add(String.format("%02d", i));
        }
    }

    private void LocalizeTextOnControlsAndHeaders() {
    }

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_Save(ActionEvent actionEvent) {
        // TODO - This is the key one to having basic functionality done.
        appointment.setTitle(txtTitle.getText());
        appointment.setDescription(txtDescription.getText());
        appointment.setLocation(txtLocation.getText());
        appointment.setContact_Id(Integer.parseInt(lblContactNumValue.getText()));
        int custNumVal = ((Customer)cbCustomers.getSelectionModel().getSelectedItem()).getCustomer_ID();
        appointment.setCustomer_Id(custNumVal);
        appointment.setStart(GetLDTFrom(dpStart, cbStartHours, cbStartMinutes));
        appointment.setStart(GetLDTFrom(dpEnd, cbEndHours, cbEndMinutes));

        dao.insertOrUpdateAppointment(appointment);
        if(addEdit == "ADD"){
            utils.commitNextIdNumber();
        }
        StageManager.ChangeScene(actionEvent, new navInfo_Appointments());

    }

    private LocalDateTime GetLDTFrom(DatePicker dpDate, ChoiceBox cbHours, ChoiceBox cbMinutes) {
        LocalDate ldStart = dpDate.getValue();
        int startHour = Integer.parseInt(cbHours.getSelectionModel().getSelectedItem().toString());
        int startMinute = Integer.parseInt(cbMinutes.getSelectionModel().getSelectedItem().toString());
        LocalTime ltStart = LocalTime.of(startHour, startMinute);
        LocalDateTime ldt = LocalDateTime.of(ldStart, ltStart);
        return ldt;
    }

    public void updateCustomerNumber(ActionEvent actionEvent) {
        Customer newCustomer = (Customer) cbCustomers.getSelectionModel().getSelectedItem();
        lblCustNumValue.setText(String.valueOf(newCustomer.getCustomer_ID()));
    }

    public void updateContactNumber(ActionEvent actionEvent) {
        Contact newContact = (Contact) cbContacts.getSelectionModel().getSelectedItem();
        lblContactNumValue.setText(String.valueOf(newContact.getContact_ID()));
        System.out.println("newContact.getContact_ID(): " + newContact.getContact_ID());
        System.out.println("lblContactNumValue: " + lblContactNumValue.getText());

    }
}
