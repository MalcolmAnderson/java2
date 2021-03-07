package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Appointment;
import models.Appointments;
import models.ManageTestData;
import utils.dataaccess.DAOAppointments;
import utils.navigation.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Appointments_Controller implements Initializable {
    private Appointments appointments;

    @FXML private TableView appointmentsTableView;
    public TableColumn id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn custId;


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        Appointments foo = ManageTestData.BuildPlaceHolderData();
//        appointmentsTableView.setItems(foo.getOL_Appointments());
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        title.setCellValueFactory(new PropertyValueFactory<>("title"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        location.setCellValueFactory(new PropertyValueFactory<>("location"));
//        contact.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
//        type.setCellValueFactory(new PropertyValueFactory<>("type"));
//        start.setCellValueFactory(new PropertyValueFactory<>("start"));
//        end.setCellValueFactory(new PropertyValueFactory<>("end"));
//        custId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
//        appointmentsTableView.refresh();


    }
//    public void LoadInventory(Inventory inv){
//        this.inv = inv;
//
//        partsTableView.setItems(inv.getAllParts());
//        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        partPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        productsTableView.setItems(inv.getAllProducts());
//        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        productPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//    }

    public void LoadAppointments() {

//        appointments = ManageTestData.BuildPlaceHolderData();
//        ObservableList<Appointment> temp = (ObservableList<Appointment>)appointments.getAllAppointments();
//        System.out.println("Number of rows in temp is " + temp.size());
//        System.out.println("Number of rows in temp is " + temp.);
//
//        appointmentsTableView.setItems(appointments.getAllAppointments());
        //placeHolder.setCellValueFactory(new PropertyValueFactory<>("placeHolder"));

        System.out.println("LoadInventory Doesn't Do Anything Yet.");
    }




    public void OnButtonClicked_Exit(){
        Platform.exit();
    }

    public void onClick_AddAppointments(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_AddAppointments());
    }

    public void onClick_ManageUsers(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_ManageUsers());
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
