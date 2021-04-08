package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import utils.navigation.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Main_ViewAppointments_Controller implements Initializable {
    public TableView tableViewAppointment;
    public Button btnManageAppointments;
    public Button btnManageCustomers;
    public Button btnManageContacts;
    public Button btnReportsScreen;

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
        tableViewAppointment.setItems(allAppointments);

        DefineTableElements();
    }

    private void DefineTableElements() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        custId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
        btnManageAppointments.setBackground(bg_Yellow);
//        btnManageAppointments.setTextFill(Color.WHITE);
//        btnManageCustomers.setBackground(bg_Yellow);
//        btnManageCustomers.setTextFill(Color.WHITE);
//        btnManageContacts.setBackground(bg_Yellow);
//        btnManageContacts.setTextFill(Color.WHITE);
        btnReportsScreen.setBackground(bg_Red);
        btnReportsScreen.setTextFill(Color.WHITE);
    }

    public void LoadAppointments() {

        System.out.println("LoadAppointments Doesn't Do Anything Yet.");
    }

    public void onClick_AddAppointments(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_AddAppointments());
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
}