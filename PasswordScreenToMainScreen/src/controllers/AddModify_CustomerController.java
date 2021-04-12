package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Globals;
import models.Country;
import models.Customer;
import models.Geography;
import utils.Utils;
import utils.dataAccess.DAOCustomers;
import utils.dataAccess.DAOGeography;
import utils.navigation.StageManager;
import utils.navigation.navInfo_ManageCustomers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddModify_CustomerController implements Initializable {

    // setters
    public static Customer customer;
    public static String addEdit;
    private String currentCountry;
    private ArrayList<Country> countries;
    private ArrayList<Geography> currentDivisions;

    // screen elements
    public Label lblID;
    public Label lblCustomerName;
    public Label lblStreetAddress;
    public Label lblPostalCode;
    public Label lblState;
    public Label lblCountry;
    public TextField txtCustomerName;
    public TextField txtStreetAddress;
    public TextField txtPostalCode;
    public TextField txtState;
    public Label lblIdValue;
    public Label lblDivision;
    public ComboBox cmbDivision;
    public ComboBox cmbCountries;
    public Label lblScreenIdentifier;
    public Label lblPhoneNumber;
    public TextField txtPhoneNumber;

    private Utils utils = new Utils();
    private DAOCustomers dao = new DAOCustomers();
    private ResourceBundle rb;


    private void HandleInboundCustomerObject() {
        System.out.println("AddModify_CustomerController HandleInboundCustomerObject called");

        if(customer == null){
            if(addEdit == "EDIT"){
                System.out.println("Edit should not be able to submit a null Customer");
                System.exit(-1);
            }
            customer = new Customer();
            customer.setGeography(new Geography());
            customer.setCustomer_ID(utils.getNextIdNumber());
        } else if (customer.getCustomer_ID() == -1){
            customer.setCustomer_ID(utils.getNextIdNumber());
        }
        if(addEdit == "ADD"){
            lblScreenIdentifier.setText("Add Contact");
//            customer.setGeography(new Geography());
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText("Edit Customer");
        }
    }

    @FXML void onCancelAction(ActionEvent event) {
        System.out.println("Cancel Clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to cancel this action?  Information will not be saved.", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            StageManager.ChangeScene(event, new navInfo_ManageCustomers());
        }
    }

    @FXML void onSaveAction(ActionEvent event) {
        System.out.println("Save Clicked");
        customer.setCustomer_Name(txtCustomerName.getText());
        customer.setPhone(txtPhoneNumber.getText());
        customer.setAddress(txtStreetAddress.getText());
        customer.setPostal_Code(txtPostalCode.getText());
        String divisionName = getCurrentDivisionNameFromDivisionComboBox();
        Geography currentGeo = getDivNameFromDivId(divisionName);
        customer.setDivision_ID(currentGeo.getDivisionId());

        dao.insertOrUpdateCustomer(customer);
        if(addEdit == "ADD"){
            utils.commitNextIdNumber();
        }
        StageManager.ChangeScene(event, new navInfo_ManageCustomers());
    }

    private Geography getDivNameFromDivId(String divisionName) {
        System.out.println("AddModify_CustomerController getDivNameFromDivId called");
        Geography retVal = null;
        for(int i = 0; i < currentDivisions.size(); i++){
            if(divisionName.equals(currentDivisions.get(i).getDivisionName())){
                retVal = currentDivisions.get(i);
                break;
            }
        }
        if(retVal == null){
            System.out.println("Division not found: " + divisionName);
            System.exit(-1);
        }
        return retVal;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        rb = resources;
        rb = Globals.getResourceBundle();
        System.out.println("AddModify_CustomerController initialize called");
        HandleInboundCustomerObject();

        countries = DAOGeography.getCountries();

        SetSimpleScreenValues();

//        System.out.println("AddModify_CustomerController about to getCountryName");
        String country = customer.getCountryName();
//        System.out.println("AddModify_CustomerController country == " + country);
        int divId = customer.getDivision_ID();

        String divCur = customer.getDivisionName();

        int countryId = setCmbCountryValue(countries, country);

//        System.out.println("Country Index: " + cmbCountries.getSelectionModel().getSelectedIndex());

        int divisionCount = SetDivisionsByCountryId(countryId);
        for(int i = 0; i < divisionCount; i++){
            if(cmbDivision.getItems().get(i).toString().equals(divCur)){
                cmbDivision.getSelectionModel().select(i);
            }
        }
        System.out.println("Division Index: " + cmbDivision.getSelectionModel().getSelectedIndex());
    }

    private int SetDivisionsByCountryId(int countryId) {
        System.out.println("AddModify_CustomerController SetDivisionsByCountryId called");
        System.out.println(countryId);
        currentDivisions = Geography.getDivisionsForCountryID(countryId);
        int divisionCount = currentDivisions.size();
        cmbDivision.getItems().clear();
        for(int i = 0; i < currentDivisions.size(); i++){
            cmbDivision.getItems().add(currentDivisions.get(i).getDivisionName());
        }
        cmbDivision.getSelectionModel().select(0);
        return divisionCount;
    }

    private int setCmbCountryValue(ArrayList<Country> countries, String country) {
        System.out.println("AddModify_CustomerController setCmbCountryValue called");
        int countryId = -1;
        for(int i = 0; i < countries.size(); i++){
            String current = countries.get(i).getCountryName();
            cmbCountries.getItems().add(current);
            if(current.equals(country)){
                currentCountry = current;
                countryId = countries.get(i).getCountryId();
                cmbCountries.getSelectionModel().select(i);
            }
        }
        if(countryId == -1){
            if(addEdit.toUpperCase().equals("EDIT")){
                System.out.println("Country not found: " + country);
                System.exit(-1);
            } else {
                cmbCountries.getSelectionModel().select(0);
            }
        }
        return countryId;
    }

    private void SetSimpleScreenValues() {
        System.out.println("AddModify_CustomerController SetSimpleScreenValues called");
        lblIdValue.setText(String.valueOf(customer.getCustomer_ID()));
        txtCustomerName.setText(customer.getCustomer_Name());
        txtPhoneNumber.setText(customer.getPhone());
        txtStreetAddress.setText(customer.getAddress());
        txtPostalCode.setText(customer.getPostal_Code());
    }

    public void onActionCountry(ActionEvent actionEvent) {
        String selectedCountry = getCurrentCountryNameFromCountryComboBox();
        int countryId = getCountryIdFromCountryName(selectedCountry);
        SetDivisionsByCountryId(countryId);
    }

    private String getCurrentCountryNameFromCountryComboBox() {
        int i = cmbCountries.getSelectionModel().getSelectedIndex();
        return cmbCountries.getItems().get(i).toString();
    }

    private String getCurrentDivisionNameFromDivisionComboBox() {
        int i = cmbDivision.getSelectionModel().getSelectedIndex();
        return cmbDivision.getItems().get(i).toString();
    }

    private int getCountryIdFromCountryName(String selectedCountry) {
        int countryId = -1;
        for(int i = 0; i < countries.size(); i++){
            String current = countries.get(i).getCountryName();
            if(current.equals(selectedCountry)){
                countryId = countries.get(i).getCountryId();
            }
        }
        return countryId;
    }
}
