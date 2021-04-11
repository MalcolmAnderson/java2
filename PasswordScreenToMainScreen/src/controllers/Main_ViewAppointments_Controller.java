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

    @FXML private TableView<Appointment> appointmentsTable;
    public TableColumn id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn date;
    public TableColumn start;
    public TableColumn end;
    public TableColumn custId;

    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        SetButtonColors();

        Appointments appointments = _ManageTestData.BuildPlaceHolderData_Appointments();
        allAppointments.setAll(appointments.getAllAppointments());
        tvAppointments.setItems(allAppointments);
        if(!Globals.getUserName().equals("admin")){
            btnManageContacts.setManaged(false);
            btnManageContacts.setVisible(false);
        }

        DefineTableElements();
        LocalizeTextOnControlsAndHeaders();

    }

    private void LocalizeTextOnControlsAndHeaders() {
    }

    private void DefineTableElements() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        custId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
        btnAddAppointment.setBackground(bg_Yellow);
//        btnAddAppointment.setTextFill(Color.WHITE);
        btnEditAppointment.setBackground(bg_Yellow);
//        btnEditAppointment.setTextFill(Color.WHITE);
        btnDeleteAppointment.setBackground(bg_Yellow);
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
//                dao.deleteCustomerByID(selectedCustomer.getCustomer_ID());
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
    }

    public void onClickForwardOne(ActionEvent actionEvent) {
    }
}
