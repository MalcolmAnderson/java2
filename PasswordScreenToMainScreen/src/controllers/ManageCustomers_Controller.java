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
import main.Globals;
import models.*;
import utils.dataAccess.DAOAppointments;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_AddEditCustomer;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.Locale;
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
    public TableColumn tcPostalCode;


    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private DAOCustomers dao = new DAOCustomers();
    private ResourceBundle rb;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ManageCustomers_Controller - initialize");
        rb = Globals.getResourceBundle();

        SetButtonColors();
        LocalizeTextOnControlsAndHeaders();
        BindDataToTableView();
    }

    private void BindDataToTableView() {
        Customers customers = dao.selectAllCustomers();
        allCustomers.setAll(customers.getCustomers());

        tvCustomers.setItems(allCustomers);

        tcId.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        tcPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void LocalizeTextOnControlsAndHeaders() {
        tcId.setText(rb.getString("Id"));
        tcName.setText(rb.getString("Name"));
        tcAddress.setText(rb.getString("Address"));
        tcCountry.setText(rb.getString("Country"));
        tcPostalCode.setText(rb.getString("Postal.Code"));
        tcPhone.setText(rb.getString("Phone"));
        btnAddCustomer.setText(rb.getString("Add.Customer"));
        btnEditCustomer.setText(rb.getString("Edit.Customer"));
        btnDeleteCustomer.setText(rb.getString("Delete.Customer"));
        btnExit.setText(rb.getString("Cancel"));
    }

    private void SetButtonColors() {
//        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
//        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
//        btnAddCustomer.setBackground(bg_Red);
//        btnAddCustomer.setTextFill(Color.WHITE);
//        btnEditCustomer.setBackground(bg_Red);
//        btnEditCustomer.setTextFill(Color.WHITE);
//        btnDeleteCustomer.setBackground(bg_Yellow);
        // btnDeleteCustomer.setTextFill(Color.WHITE);
    }

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_EditCustomer(ActionEvent actionEvent) {
        int selectedCustomerIndex = tvCustomers.getSelectionModel().getSelectedIndex();
        Customer selectedCustomer = (Customer) tvCustomers.getSelectionModel().getSelectedItem();
        System.out.println("Selected Customer Index = " + selectedCustomerIndex);
        if (selectedCustomerIndex != -1){
            AddModify_CustomerController.customer = selectedCustomer;
            AddModify_CustomerController.addEdit = "EDIT";
            StageManager.ChangeScene(actionEvent, new navInfo_AddEditCustomer());
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    "Please select a customer to edit",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void onClickAddCustomer(ActionEvent actionEvent) {
        System.out.println("Add Customer Clicked");
        AddModify_CustomerController.addEdit = "ADD";
        Customer newCustomer = new Customer();
        newCustomer.setGeography(Geography.getGeographyByDivisionId(newCustomer.getDivision_ID()));
        AddModify_CustomerController.customer = newCustomer;

        StageManager.ChangeScene(actionEvent, new navInfo_AddEditCustomer());
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
                DAOAppointments daoAppointments = new DAOAppointments();
                daoAppointments.deleteAppointmentsByCustomerId(selectedCustomer.getCustomer_ID());
                dao.deleteCustomerByID(selectedCustomer.getCustomer_ID());
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
