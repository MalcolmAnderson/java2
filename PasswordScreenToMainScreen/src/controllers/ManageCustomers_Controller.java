package controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import models.*;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomers_Controller implements Initializable {
    public TableColumn tcId;
    public TableColumn tcName;
    public TableColumn tcAddress;
    public TableView tvCustomers;
    public Button btnAddCustomer;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
    public Button btnExit;
    public TableColumn tcPhone;
    public TableColumn tcCountry;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private DAOCustomers dao = new DAOCustomers();


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        SetButtonColors();


        Customers customers = new Customers();
        customers = dao.selectAllCustomers();
        allCustomers.setAll(customers.getCustomers());

        tvCustomers.setItems(allCustomers);

        tcId.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
        btnAddCustomer.setBackground(bg_Red);
        btnAddCustomer.setTextFill(Color.WHITE);
        btnEditCustomer.setBackground(bg_Red);
        btnEditCustomer.setTextFill(Color.WHITE);
        btnDeleteCustomer.setBackground(bg_Yellow);
        // btnDeleteCustomer.setTextFill(Color.WHITE);
    }


    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_EditCustomer(ActionEvent actionEvent) {
    }

    public void onClickDAddCustomer(ActionEvent actionEvent) {
    }

    public void onClickDeleteCustomer(ActionEvent actionEvent) {
        int selectedCustomerIndex = tvCustomers.getSelectionModel().getSelectedIndex();
        Customer selectedCustomer = (Customer) tvCustomers.getSelectionModel().getSelectedItem();
        System.out.println("Selected Customer Index = " + selectedCustomerIndex);
        if (selectedCustomerIndex != -1){
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Deleting a customer deletes all customer appointments.\nDelete this customer?",
                    ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                tvCustomers.getItems().remove(selectedCustomerIndex);

                //TODO Delete the customer from the database
                // inv.deletePart(selectedCustomer);
            }
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    "Please select a customer to delete",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
}
