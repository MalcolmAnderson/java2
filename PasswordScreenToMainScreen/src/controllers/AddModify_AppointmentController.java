package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Globals;
import models.*;
import utils.TimeConversion;
import utils.Utils;
import utils.dataAccess.DAOAppointments;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * Used to add and edit appointment records.
 * Called from the main screen this screen is both the add screen and the edit screen.
 * Certain fields are loaded by the calling screen using static variables in this file.
 */
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
    public Label lblAppointmentID;
    public Label lblTitle;
    public Label lblDescription;
    public Label lblLocation;
    public Label lblContact;
    public Label lblType;
    public Label lblStartDateAndTime;
    public Label lblEndDateAndTime;
    public Label lblCustomerID;

    private DAOAppointments dao = new DAOAppointments();
    private ResourceBundle rb = Globals.getResourceBundle();
    private Utils utils = new Utils();
    private Customers customers = Globals.getMasterCustomers();
    private Contacts contacts = Globals.getMasterContacts();
    private TimeConversion tc = new TimeConversion();

    /**
     * This is the required JavaFX initialise method.
     * This is the routine that sets up the screen.
     *
     * @param url - required parameter for initialize
     * @param resourceBundle - required parameter for initialize
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AddModify_AppointmentController - initialize");
//        rb = Globals.getResourceBundle();
        System.out.println("Current Appointment : " + appointment.toString());

        // Completed subroutines
        PopulateChoiceBoxes();
        HandleInboundCustomerObject();

        // TODO handle
        SetSimpleScreenValues();
        LocalizeTextOnControlsAndHeaders();
    }

    private void LocalizeTextOnControlsAndHeaders() {
        lblAppointmentID.setText(rb.getString("Appointment.ID"));
        lblTitle.setText(rb.getString("Title"));
        lblDescription.setText(rb.getString("Description"));
        lblLocation.setText(rb.getString("Location"));
        lblType.setText(rb.getString("Type"));
        lblStartDateAndTime.setText(rb.getString("Start.Date.and.Time"));
        lblEndDateAndTime.setText(rb.getString("End.Date.and.Time"));
        lblCustomerID.setText(rb.getString("Customer.Id"));
        btnSave.setText(rb.getString("Save"));
        btnCancel.setText(rb.getString("Cancel"));

    }

    private void PopulateChoiceBoxes() {
        LoadContactList();
        LoadCustomerList();
        LoadIntegersIntoChoiceBox(cbStartHours, 0, 23, 1);
        LoadIntegersIntoChoiceBox(cbEndHours, 0, 23, 1);
        LoadIntegersIntoChoiceBox(cbStartMinutes, 0, 11, 5);
        LoadIntegersIntoChoiceBox(cbEndMinutes, 0, 11, 5);
//        LoadHours(cbStartHours);
//        LoadHours(cbEndHours);
//        LoadMinutes(cbStartMinutes);
//        LoadMinutes(cbEndMinutes);
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
        lblContactNumValue.setText(String.valueOf(appointment.getContact_Id()));
        System.out.println("start: " + appointment.getStart());
        System.out.println("end: " +
                appointment.getEnd());
        SetDateAndTime(appointment.getStart(), dpStart, cbStartHours, cbStartMinutes);
        SetDateAndTime(appointment.getEnd(), dpEnd, cbEndHours, cbEndMinutes);
    }

    private void SetDateAndTime(LocalDateTime ldt, DatePicker datePicker, ChoiceBox cbHours, ChoiceBox cbMinutes) {
        if(ldt == null){
            // initialize data for screen
            datePicker.setValue(LocalDateTime.now().toLocalDate());
            cbHours.getSelectionModel().select(0);
            cbMinutes.getSelectionModel().select(0);
        } else {
            datePicker.setValue(ldt.toLocalDate());
            int hour = ldt.getHour();
            cbHours.getSelectionModel().select(hour);
            int minute = ldt.getMinute();
            for (int i = 0; i < 12; i++) {
                int curMin = Integer.parseInt((String) cbMinutes.getItems().get(i));
                if (curMin == minute) {
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
            lblScreenIdentifier.setText(rb.getString("Add.Appointment"));
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText(rb.getString("Edit.Appointment"));
        }
    }


    // TODO document Lambda in javadocs

    // replaced LoadMinutes and LoadHours with LoadIntegersIntoChoiceBox

    /** This is the first of my lambda expressions.
     * This method loads the choice boxes with hours and minutes used in appointment setting
     *
     * This method replaced 2 different routines.
     * The commented lines below this show the original methods.
     * //    private void LoadMinutes(ChoiceBox cbMinutes) {
     * //        for(int i = 0; i < 12; i++){
     * //            cbMinutes.getItems().add(String.format("%02d", i * 5));
     * //        }
     * //    }
     * //    private void LoadHours(ChoiceBox cbHours) {
     * //        for(int i = 0; i < 24; i++){
     * //            cbHours.getItems().add(String.format("%02d", i));
     * //        }
     * //    }
     * @param cb
     * @param start
     * @param end
     * @param multiplier
     */
    private void LoadIntegersIntoChoiceBox(ChoiceBox cb, int start, int end, int multiplier){
        IntStream hours = IntStream.rangeClosed(start, end);
        hours.forEach(i -> cb.getItems().add(String.format("%02d", i * multiplier)));
    }
// Leave these comments to explain the Lamda change
//    private void LoadMinutes(ChoiceBox cbMinutes) {
//        for(int i = 0; i < 12; i++){
//            cbMinutes.getItems().add(String.format("%02d", i * 5));
//        }
//    }
//    private void LoadHours(ChoiceBox cbHours) {
//        for(int i = 0; i < 24; i++){
//            cbHours.getItems().add(String.format("%02d", i));
//        }
//    }

    /**
     * Handles the click on the cancel button.
     * Confirms with user that they are OK with deleting the appointment.
     * @param event - required parameter for button click
     */
    public void onClick_Cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, rb.getString("Cancel.Question"), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            StageManager.ChangeScene(event, new navInfo_Appointments());
        }
    }

    /**
     * Handles the click on the save button.
     * Before saving the following validations are performed in this method:
     *      Validate Both Contact and Customer are Selected.
     *      Validate that this customers appointment doesn't overlap any other appointments for that customer.
     *      Validate that the appointment time is chronologically before the end time.
     * Then it saves the appointment and closes the screen.
     * @param actionEvent - required parameter for button click
     */
    public void onClick_Save(ActionEvent actionEvent) {
        boolean okToSave = true;
        String ErrorMessage = "";

        // confirm that contact and customer are selected
        if( ! validateBothContactAndCustomerAreSelected()){ return; }


        // confirm that start and end times fall within business hours
        LocalDateTime start = GetLDTFrom(dpStart, cbStartHours, cbStartMinutes);
        LocalDateTime end = GetLDTFrom(dpEnd, cbEndHours, cbEndMinutes);
        appointment.setStart(start);
        appointment.setEnd(end);
        if (! validateAppointmentDoesNotOverlapWithExistingAppointmentForCustomer()) { return; }
        boolean isAppointmentWithinPolicy = tc.isAppointmentWithinPolicy(start, end);
        ErrorMessage = rb.getString("ApptEasternStartEndPolicyMessage");
        okToSave = ErrorNotificationDialog(isAppointmentWithinPolicy, ErrorMessage);
        if( ! okToSave){
            return;
        }

        ErrorMessage = rb.getString("ApptStartBeforeEndMessage");
        okToSave = ErrorNotificationDialog(start.isBefore(end), ErrorMessage);
        if( ! okToSave){ return; }



        if(okToSave) {
            appointment.setContact_Id(Integer.parseInt(lblContactNumValue.getText()));
            int customerNumberValue = ((Customer)cbCustomers.getSelectionModel().getSelectedItem()).getCustomer_ID();
            appointment.setCustomer_Id(customerNumberValue);
            appointment.setTitle(txtTitle.getText());
            appointment.setDescription(txtDescription.getText());
            appointment.setLocation(txtLocation.getText());
            appointment.setType(txtType.getText());

            dao.insertOrUpdateAppointment(appointment);
            if (addEdit == "ADD") {
                utils.commitNextIdNumber();
            }
            StageManager.ChangeScene(actionEvent, new navInfo_Appointments());
        }
    }

    private boolean validateAppointmentDoesNotOverlapWithExistingAppointmentForCustomer(){
        String ErrorMessage;
        boolean okToSave;
        int customerID = Integer.parseInt(lblCustNumValue.getText());
        Appointments customerAppointments = dao.selectExistingNearCustomerAppointments(customerID, appointment);
        int appointmentIdOfConflict = isAppointmentFreeFromOverlap(appointment, customerAppointments);
//        ErrorMessage = "This appointment for customer " + customerID + " (appointment # "
//                + appointment.getId() + ")\noverlaps with appointment # " + appointmentIdOfConflict;
        ErrorMessage = rb.getString("OverlapError_1") + " "
                + customerID + " (" + rb.getString("OverlapError_2") +" # " + appointment.getId() + ")\n"
                + rb.getString("OverlapError_3") + " # " + appointmentIdOfConflict;
        okToSave = ErrorNotificationDialog(appointmentIdOfConflict == -1, ErrorMessage);
        return okToSave;
    }

    private int isAppointmentFreeFromOverlap(Appointment thisAppointment, Appointments nearAppointmentsForCustomer) {
        // returns the appointment Id of the first overlapping appointment
        int overlappingAppointmentId = -1;
        if(nearAppointmentsForCustomer.getAllAppointments().size() != 0){
            LocalDateTime thisStart = thisAppointment.getStart();
            LocalDateTime thisEnd = thisAppointment.getEnd();
            for (Appointment a : nearAppointmentsForCustomer.getAllAppointments()) {
                boolean startConflicts = isTimeBetween(thisStart, a);
                boolean endConflicts = isTimeBetween(thisEnd, a);
                int thisId = thisAppointment.getId();
                int thatId = a.getId();
                boolean comparingCurrentAppointmentAgainstThisAppointment = (thisId == thatId);
                if( ! comparingCurrentAppointmentAgainstThisAppointment){
                    if( startConflicts || endConflicts ) { // then still good
                        overlappingAppointmentId = a.getId();
                        break;
                    }
                }
            }
        }
        return overlappingAppointmentId;
    }

    private boolean isEqualOr(LocalDateTime runner, String compare, LocalDateTime timeToCompareAgainst) {
        boolean retVal = false;
        if(compare.equals("BEFORE")){
            retVal = (runner.isBefore(timeToCompareAgainst) || runner.equals(timeToCompareAgainst));
        } else {
            retVal = (runner.isAfter(timeToCompareAgainst) || runner.equals(timeToCompareAgainst));
        }
        return retVal;
    }

    private boolean isTimeBetween(LocalDateTime ldt, Appointment a) {
        boolean isBetween = ldt.isAfter(a.getStart()) && ldt.isBefore(a.getEnd());
        return isBetween;
    }

    private boolean validateBothContactAndCustomerAreSelected() {
        String ErrorMessage;
        boolean okToSave;
        appointment.setContact_Id(Integer.parseInt(lblContactNumValue.getText()));
        int customerID = Integer.parseInt(lblCustNumValue.getText());
        boolean contactSelected = (appointment.getContact_Id() != -1);
        boolean customerSelected = (customerID != -1);
        boolean bothCustomerAndContactSelected = contactSelected && customerSelected;
        ErrorMessage = rb.getString("Both.Contact.and.Customer.must.be.selected");
        okToSave = ErrorNotificationDialog(bothCustomerAndContactSelected, ErrorMessage);
        return okToSave;
    }

    private boolean ErrorNotificationDialog(boolean trueToContinue, String errorMessage) {
        boolean okToSave = true;
        if( ! trueToContinue){
            okToSave = false;

            String displayLine = errorMessage;
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    displayLine,
                    ButtonType.OK);
            alert.showAndWait();
        }
        return okToSave;
    }

    private LocalDateTime GetLDTFrom(DatePicker dpDate, ChoiceBox cbHours, ChoiceBox cbMinutes) {
        LocalDate ldStart = dpDate.getValue();
//        System.out.println("ldDate: " + ldStart.toString());
        int timeHour = Integer.parseInt(cbHours.getSelectionModel().getSelectedItem().toString());
//        System.out.println(timeHour);
        int timeMinute = Integer.parseInt(cbMinutes.getSelectionModel().getSelectedItem().toString());
//        System.out.println(timeMinute);
        LocalTime ltStart = LocalTime.of(timeHour, timeMinute);
        LocalDateTime ldt = LocalDateTime.of(ldStart, ltStart);
//        System.out.println(ldt);
        return ldt;
    }

    /**
     * Update Customer ID Value label.
     * Method updates the label containing the Customer ID value based on what is selected in the choice box.
     * @param actionEvent - JavaFx infrastructure parameter
     */
    public void updateCustomerNumber(ActionEvent actionEvent) {
        Customer newCustomer = (Customer) cbCustomers.getSelectionModel().getSelectedItem();
        lblCustNumValue.setText(String.valueOf(newCustomer.getCustomer_ID()));
    }

    /**
     * Update Contact ID Value label
     * Method updates the label containing the Contact ID value based on what is selected in the choice box.
     * @param actionEvent - JavaFx infrastructure parameter
     */
    public void updateContactNumber(ActionEvent actionEvent) {
        Contact newContact = (Contact) cbContacts.getSelectionModel().getSelectedItem();
        lblContactNumValue.setText(String.valueOf(newContact.getContact_ID()));
        System.out.println("newContact.getContact_ID(): " + newContact.getContact_ID());
        System.out.println("lblContactNumValue: " + lblContactNumValue.getText());
    }
}
