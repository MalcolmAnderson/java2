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
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomers_Controller implements Initializable {
    public TableColumn tcId;
    public TableColumn tcName;
    public TableColumn tcAddress;
    public TableView tvCustomerView;
    public Button btnAddCustomer;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
    public Button btnExit;
    public TableColumn tcPhone;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
        btnAddCustomer.setBackground(bg_Red);
        btnAddCustomer.setTextFill(Color.WHITE);
        btnEditCustomer.setBackground(bg_Red);
        btnEditCustomer.setTextFill(Color.WHITE);
        btnDeleteCustomer.setBackground(bg_Yellow);
        // btnDeleteCustomer.setTextFill(Color.WHITE);

        Customers foo = _ManageTestData.BuildPlaceHolder_Customers();
        allCustomers.setAll(foo.getOL_Customers());
//        ArrayList<Customer> arrayOfCustomers = foo.getCustomers();

        tvCustomerView.setItems(allCustomers);

        tcId.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }


    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_EditCustomer(ActionEvent actionEvent) {
    }

    public void onClickDAddCustomer(ActionEvent actionEvent) {
    }

    public void onClickDeleteCustomer(ActionEvent actionEvent) {
        int selectedCustomerIndex = tvCustomerView.getSelectionModel().getSelectedIndex();
        Customer selectedCustomer = (Customer)tvCustomerView.getSelectionModel().getSelectedItem();
        System.out.println("Selected Customer Index = " + selectedCustomerIndex);
        if (selectedCustomerIndex != -1){
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Deleting a customer deletes all customer appointments.\nDelete this customer?",
                    ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                tvCustomerView.getItems().remove(selectedCustomerIndex);

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
