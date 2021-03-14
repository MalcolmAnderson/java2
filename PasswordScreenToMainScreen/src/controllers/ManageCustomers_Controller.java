package controllers;

import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import models.*;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageCustomers_Controller implements Initializable {
    public TableColumn tcId;
    public TableColumn tcName;
    public TableColumn tcAddress;
    public TableColumn tcEmail;
    public TableView tvCustomerView;
    public Button btnAddCustomer;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
    public Button btnExit;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        btnAddCustomer.setBackground(bg_Red);
        btnAddCustomer.setTextFill(Color.WHITE);
        btnEditCustomer.setBackground(bg_Red);
        btnEditCustomer.setTextFill(Color.WHITE);
        btnDeleteCustomer.setBackground(bg_Red);
        btnDeleteCustomer.setTextFill(Color.WHITE);

        Customers foo = ManageTestData.BuildPlaceHolder_Customers();
        allCustomers.setAll(foo.getOL_Customers());
//        ArrayList<Customer> arrayOfCustomers = foo.getCustomers();

        tvCustomerView.setItems(allCustomers);

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }


    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_EditContact(ActionEvent actionEvent) {
    }
}
