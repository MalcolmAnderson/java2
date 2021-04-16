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
    public TableColumn columnId;
    public TableColumn columnTitle;
    public TableColumn columnDescription;
    public TableColumn columnLocation;
    public TableColumn columnContact;
    public TableColumn columnType;
    public TableColumn columnStart;
    public TableColumn columnEnd;
    public TableColumn columnCustomerId;
    public RadioButton rbByWeek;
    public RadioButton rbByMonth;
    public ToggleGroup tgShowBy;
    DAOAppointments dao = new DAOAppointments();
    Utils utils = new Utils();

    @FXML private TableView<Appointment> appointmentsTable;
//    public TableColumn columnId;
//    public TableColumn title;
//    public TableColumn description;
//    public TableColumn location;
//    public TableColumn contact;
//    public TableColumn type;
//    public TableColumn start;
//    public TableColumn end;
//    public TableColumn custId;

    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        SetButtonColors();
        if(Globals.getSelectedRadioButton().equals("rbByWeek")){
            rbByWeek.setSelected(true);
        } else {
            rbByMonth.setSelected(true);
        }
        RadioButton selected = (RadioButton) tgShowBy.getSelectedToggle();
        System.out.println(selected.getId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = utils.getLastSunday(now);
        LocalDateTime end = utils.getNextSunday(now);
        Appointments appointments = dao.selectAppointmentsInDateRange(start, end);
        HandleNextFifteenMinuteAlert(now, appointments);
//        Appointments appointments = dao.selectAllAppointments();
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
        if(!Globals.getUserName().equals("admin")){
            btnManageContacts.setManaged(false);
            btnManageContacts.setVisible(false);
        }

        DefineTableElements();
        LocalizeTextOnControlsAndHeaders();

    }

    private void HandleNextFifteenMinuteAlert(LocalDateTime now, Appointments appointments) {
        if(Globals.isStillFirstLogin()) {
            Globals.setStillFirstLogin(false);
            boolean noAppointmentsExistInNext15Minutes = true;
            LocalDateTime fifteenFromNow = now.plusMinutes(15);
            for (Appointment appointment : appointments.getAllAppointments()) {
                LocalDateTime start = appointment.getStart();
                if (start.isAfter(now) && start.isBefore(fifteenFromNow)) {
                    String message = String.format("Appointment %s is scheduled at %s", appointment.getId(), appointment.getStartDisplay());
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
                        "There are no appointments in the next 15 minutes.",
                        ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    private void LocalizeTextOnControlsAndHeaders() {
    }

    private void DefineTableElements() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnContact.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("startDisplay"));
        columnEnd.setCellValueFactory(new PropertyValueFactory<>("endDisplay"));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
//        btnAddAppointment.setBackground(bg_Yellow);
//        btnAddAppointment.setTextFill(Color.WHITE);
//        btnEditAppointment.setBackground(bg_Yellow);
//        btnEditAppointment.setTextFill(Color.WHITE);
//        btnDeleteAppointment.setBackground(bg_Yellow);
//        btnDeleteAppointment.setTextFill(Color.WHITE);
        btnReportsScreen.setBackground(bg_Yellow);
//        btnReportsScreen.setTextFill(Color.WHITE);
    }

    public void LoadAppointments() {

        System.out.println("LoadAppointments Doesn't Do Anything Yet.");
    }

    public void onClick_ManageCustomers(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_ManageCustomers());
    }

    public void onClick_ManageContacts(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_ManageContacts());
    }

    public void onClick_Reports(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Reports());
    }

    public void onClickAddAppointment(ActionEvent actionEvent) {
        System.out.println("Add Appointment Clicked");
        AddModify_AppointmentController.addEdit = "ADD";
        Appointment newAppointment = new Appointment();
        AddModify_AppointmentController.appointment = newAppointment;

        StageManager.ChangeScene(actionEvent, new navInfo_AddEditAppointment());
    }

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
                    "Please select an appointment to edit",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void onClickDeleteAppointment(ActionEvent actionEvent) {
        int selectedAppointmentIndex = tvAppointments.getSelectionModel().getSelectedIndex();
        Appointment selected = (Appointment) tvAppointments.getSelectionModel().getSelectedItem();
        System.out.println("Selected Appointment Index = " + selectedAppointmentIndex);
        if (selectedAppointmentIndex != -1){
            String line1 = "Warning: This action will delete appointment Id: ";
            String line2 = "\nAppointment type: ";
            String line3 = "\nAre you sure you want to delete this appointment?";
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
                    "Please select an appointment to delete",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

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
