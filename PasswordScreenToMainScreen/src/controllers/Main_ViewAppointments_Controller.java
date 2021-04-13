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
import utils.dataAccess.DAOAppointments;
import utils.navigation.*;

import java.net.URL;
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
    DAOAppointments dao = new DAOAppointments();

//    @FXML private TableView<Appointment> appointmentsTable;
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        SetButtonColors();

//        Appointments appointments = _ManageTestData.BuildPlaceHolderData_Appointments();
        Appointments appointments = dao.selectAllAppointments();
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
        if(!Globals.getUserName().equals("admin")){
            btnManageContacts.setManaged(false);
            btnManageContacts.setVisible(false);
        }
        rbByWeek.setSelected(true);
        DefineTableElements();
        LocalizeTextOnControlsAndHeaders();

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
        btnReportsScreen.setBackground(bg_Red);
        btnReportsScreen.setTextFill(Color.WHITE);
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
        Appointment selectedAppointment = (Appointment) tvAppointments.getSelectionModel().getSelectedItem();
        System.out.println("Selected Appointment Index = " + selectedAppointmentIndex);
        if (selectedAppointmentIndex != -1){
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete this appointment?",
                    ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                tvAppointments.getItems().remove(selectedAppointmentIndex);
                dao.deleteAppointmentsByAppointmentId(selectedAppointment.getId());
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
        System.out.println("Back One Button clicked");
    }

    public void onClickForwardOne(ActionEvent actionEvent) {
        System.out.println("Forward One Button clicked");
    }

    public void filterByWeek(ActionEvent actionEvent) {
        System.out.println("Radio Button selected - Filter By Week");
    }

    public void filterByMonth(ActionEvent actionEvent) {
        System.out.println("Radio Button selected - Filter By Month");
    }
}
