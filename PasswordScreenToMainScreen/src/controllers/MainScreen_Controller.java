package controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Appointment;
import models.Appointments;
import models.ManageTestData;

public class MainScreen_Controller {

    FxmlNavigationTools navTools = new FxmlNavigationTools();

    private Appointments appointments;

    @FXML private TableView appointmentsTableView;
    @FXML private TableColumn placeHolder;


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

    public void OnButtonClicked_Exit(){
        Platform.exit();
    }

}
