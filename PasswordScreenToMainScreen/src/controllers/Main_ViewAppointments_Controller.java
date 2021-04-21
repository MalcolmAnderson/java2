package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import main.Globals;
import models.*;
import utils.Utils;
import utils.dataAccess.DAOAppointments;
import utils.navigation.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller class for main Appointment view.
 */
public class Main_ViewAppointments_Controller implements Initializable {
    public TableView tvAppointments;
    public Button btnManageCustomers;
    public Button btnManageContacts;
    public Button btnReportsScreen;
    public Button btnAddAppointment;
    public Button btnEditAppointment;
    public Button btnDeleteAppointment;
    public Button btnBackOneUnit;
    public Button btnForwardOneUnit;
    public TableColumn tcId;
    public TableColumn tcTitle;
    public TableColumn tcDescription;
    public TableColumn tcLocation;
    public TableColumn tcContact;
    public TableColumn tcType;
    public TableColumn tcStart;
    public TableColumn tcEnd;
    public TableColumn tcCustomerId;
    public RadioButton rbByWeek;
    public RadioButton rbByMonth;
    public ToggleGroup tgShowBy;

    DAOAppointments dao = new DAOAppointments();
    Utils utils = new Utils();
    ResourceBundle rb = Globals.getResourceBundle();
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Entry point to the main appointment screen controller.
     * This routine sets up the screen for use, wiring controls to data sources and localizing control values.
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        SetButtonColors();
        if(Globals.getSelectedRadioButton().equals("rbByWeek")){
            rbByWeek.setSelected(true);
        } else {
            rbByMonth.setSelected(true);
        }
        LocalizeTextOnControlsAndHeaders();
        RadioButton selected = (RadioButton) tgShowBy.getSelectedToggle();
        System.out.println(selected.getId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = utils.getLastSunday(now);
        LocalDateTime end = utils.getNextSunday(now);
        Appointments appointments = dao.selectAppointmentsInDateRange(start, end);
        HandleNextFifteenMinuteAlert(now, appointments);
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
        if(!Globals.getUserName().equals("admin")){
            btnManageContacts.setManaged(false);
            btnManageContacts.setVisible(false);
        }

        DefineTableElements();

    }

    private void LocalizeTextOnControlsAndHeaders() {
        btnManageCustomers.setText(rb.getString("Manage.Customers"));
        btnManageContacts.setText(rb.getString("Manage.Contacts"));
        btnReportsScreen.setText(rb.getString("Reports.Screen"));
        btnAddAppointment.setText(rb.getString("Add.Appointment"));
        btnEditAppointment.setText(rb.getString("Edit.Appointment"));
        btnDeleteAppointment.setText(rb.getString("Delete.Appointment"));
//        btnBackOneUnit.setText(rb.getString("")); // not localized
//        btnForwardOneUnit.setText(rb.getString("")); not localized
        tcId.setText(rb.getString("ID"));
        tcTitle.setText(rb.getString("Title"));
        tcDescription.setText(rb.getString("Description"));
        tcLocation.setText(rb.getString("Location"));
        tcContact.setText(rb.getString("Contact"));
        tcType.setText(rb.getString("Type"));
        tcStart.setText(rb.getString("Start"));
        tcEnd.setText(rb.getString("End"));
        tcCustomerId.setText(rb.getString("Cust.ID"));
        rbByWeek.setText(rb.getString("Show.List.By.Week"));
        rbByMonth.setText(rb.getString("Show.List.By.Month"));
    }

    private void HandleNextFifteenMinuteAlert(LocalDateTime now, Appointments appointments) {
        if(Globals.isStillFirstLogin()) {
            Globals.setStillFirstLogin(false);
            boolean noAppointmentsExistInNext15Minutes = true;
            LocalDateTime fifteenFromNow = now.plusMinutes(15);
            for (Appointment appointment : appointments.getAllAppointments()) {
                LocalDateTime start = appointment.getStart();
                if (start.isAfter(now) && start.isBefore(fifteenFromNow)) {
                    String message = String.format(rb.getString("Appointment.num.is.scheduled.at"),
                            appointment.getId(), appointment.getStartDisplay());
                    Alert alert = new Alert(
                            Alert.AlertType.WARNING,
                            message,
                            ButtonType.OK);
                    alert.showAndWait();
                    noAppointmentsExistInNext15Minutes = false;
                }
            }
            if (noAppointmentsExistInNext15Minutes) {
                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        rb.getString("No.appointments.in.next.15.minutes"),
                        ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    private void DefineTableElements() {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tcContact.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStart.setCellValueFactory(new PropertyValueFactory<>("startDisplay"));
        tcEnd.setCellValueFactory(new PropertyValueFactory<>("endDisplay"));
        tcCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
//        btnManageCustomers.setBackground(bg_Yellow);
//        btnManageContacts.setBackground(bg_Yellow);
//        btnAddAppointment.setBackground(bg_Yellow);
//        btnAddAppointment.setTextFill(Color.WHITE);
//        btnEditAppointment.setBackground(bg_Yellow);
//        btnEditAppointment.setTextFill(Color.WHITE);
//        btnDeleteAppointment.setBackground(bg_Yellow);
//        btnDeleteAppointment.setTextFill(Color.WHITE);
//        btnReportsScreen.setBackground(bg_Yellow);
//        btnReportsScreen.setTextFill(Color.WHITE);
    }

    /**
     * Handles navigation to move to the Manage Customers Screen.
     * @param event
     */
    public void onClick_ManageCustomers(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_ManageCustomers());
    }

    /**
     * Handles navigation to move to the Manage Customers Screen.
     * Only used for the admin login.
     * @param event
     */
    public void onClick_ManageContacts(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_ManageContacts());
    }

    /**
     * Handles navigation to move to the Reports Screen
     * @param event
     */
    public void onClick_Reports(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Reports());
    }

    /**
     * Handles creating a new appointment object and passing control to the Add Appointment screen.
     * @param actionEvent
     */
    public void onClickAddAppointment(ActionEvent actionEvent) {
        System.out.println("Add Appointment Clicked");
        AddModify_AppointmentController.addEdit = "ADD";
        Appointment newAppointment = new Appointment();
        AddModify_AppointmentController.appointment = newAppointment;

        StageManager.ChangeScene(actionEvent, new navInfo_AddEditAppointment());
    }

    /**
     * Handles creating a new appointment object and passing control to the Modify Appointment screen.
     * @param actionEvent
     */
    public void onClickEditAppointment(ActionEvent actionEvent) {
        int selectedAppointmentIndex = tvAppointments.getSelectionModel().getSelectedIndex();
        Appointment selectedAppointment = (Appointment) tvAppointments.getSelectionModel().getSelectedItem();
        System.out.println("Selected Appointment Index = " + selectedAppointmentIndex);
        if (selectedAppointmentIndex != -1){
            AddModify_AppointmentController.appointment = selectedAppointment;
            AddModify_AppointmentController.addEdit = "EDIT";
            StageManager.ChangeScene(actionEvent, new navInfo_AddEditAppointment());
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    rb.getString("Please.select.an.appointment.to.edit"),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Handles deleting an existing appointment from the screen and the database..
     * @param actionEvent
     */
    public void onClickDeleteAppointment(ActionEvent actionEvent) {
        int selectedAppointmentIndex = tvAppointments.getSelectionModel().getSelectedIndex();
        Appointment selected = (Appointment) tvAppointments.getSelectionModel().getSelectedItem();
        System.out.println("Selected Appointment Index = " + selectedAppointmentIndex);
        if (selectedAppointmentIndex != -1){
            String line1 = rb.getString("ApptDeleteWarn_1");
            String line2 = rb.getString("ApptDeleteWarn_2");
            String line3 = rb.getString("ApptDeleteWarn_3");
            String displayLine = line1 + selected.getId() + line2 + selected.getType() + line3;
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    displayLine,
                    ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                tvAppointments.getItems().remove(selectedAppointmentIndex);
                dao.deleteAppointmentsByAppointmentId(selected.getId());
            }
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    rb.getString("Please.select.an.appointment.to.delete"),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Navigation method for back button.
     * In combination with the selection radio buttons, goes "backwards" one "time unit".
     * @param actionEvent
     */
    public void onClickBackOne(ActionEvent actionEvent) {
        LocalDateTime currentNow = Globals.getCurrentReferenceDate();
        if(rbByWeek.isSelected()){
            currentNow = currentNow.minusWeeks(1);
            Globals.setCurrentReferenceDate(currentNow);
            refreshAppointmentByWeek();
        } else {
            currentNow = currentNow.minusMonths(1);
            Globals.setCurrentReferenceDate(currentNow);
            refreshAppointmentByMonth();
        }
    }

    /**
     * Navigation method for forward button.
     * In combination with the selection radio buttons, goes "forwards" one "time unit".
     * @param actionEvent
     */
    public void onClickForwardOne(ActionEvent actionEvent) {
        LocalDateTime currentNow = Globals.getCurrentReferenceDate();
        if(rbByWeek.isSelected()){
            currentNow = currentNow.plusWeeks(1);
            Globals.setCurrentReferenceDate(currentNow);
            refreshAppointmentByWeek();
        } else {
            currentNow = currentNow.plusMonths(1);
            Globals.setCurrentReferenceDate(currentNow);
            refreshAppointmentByMonth();
        }
    }

    /**
     * Changes view from "by month" to "by week".
     * @param actionEvent
     */
    public void onClickShowByWeek(ActionEvent actionEvent) {
        System.out.println("begin onClickShowByWeek");
        Globals.setSelectedRadioButtonName("rbByWeek");

        refreshAppointmentByWeek();
    }

    private void refreshAppointmentByWeek() {
        LocalDateTime start = utils.getLastSunday(Globals.getCurrentReferenceDate());
        LocalDateTime end = utils.getNextSunday(Globals.getCurrentReferenceDate());
        Appointments appointments = dao.selectAppointmentsInDateRange(start, end);
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
    }

    /**
     * Changes view from "by week" to "by month".
     * @param actionEvent
     */
    public void onClickShowByMonth(ActionEvent actionEvent) {
        System.out.println("begin onClickShowByWeek");
        Globals.setSelectedRadioButtonName("rbByMonth");

        refreshAppointmentByMonth();
    }

    private void refreshAppointmentByMonth() {
        LocalDateTime start = utils.getFirstOfTheMonth(Globals.getCurrentReferenceDate());
        LocalDateTime end = utils.getFirstOfNextMonth(Globals.getCurrentReferenceDate());
        Appointments appointments = dao.selectAppointmentsInDateRange(start, end);
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
    }
}
