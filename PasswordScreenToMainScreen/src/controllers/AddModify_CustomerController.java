package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Contact;
import models.Customer;
import utils.Utils;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_ManageCustomers;

import java.net.URL;
import java.util.ResourceBundle;

//import utils.navigation.StageManager;

public class AddModify_CustomerController implements Initializable {

    // setters
    public static Customer customer;
    public static String addEdit;

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
    Utils utils = new Utils();
    private DAOCustomers dao = new DAOCustomers();


    private void HandleInboundContactObject() {
        if(customer == null){
            if(addEdit == "EDIT"){
                System.out.println("Edit should not be able to submit a null Customer");
                System.exit(-1);
            }
            customer = new Customer();
            customer.setCustomer_ID(utils.getNextIdNumber());
        } else if (customer.getCustomer_ID() == -1){
            customer.setCustomer_ID(utils.getNextIdNumber());
        }
        if(addEdit == "ADD"){
            lblScreenIdentifier.setText("Add Contact");
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText("Edit Customer");
        }
    }


//    public void loadInventory(Inventory inv){
//        System.out.println("AddModify_PartController setInv called");
//        this.inv = inv;
//    }

//    public void InitializeNewItem(){
//        id.setText(Integer.toString(IdNumber.getNextIdNumber()));
//        name.setText("part name");
//        level.setText("0");
//        price.setText("200.0");
//        min.setText("0");
//        max.setText("0");
//        if(radioInHouse.isSelected()){
//            source.setText("2001");
//        } else {
//            source.setText("Permberly Video and Parts");
//        }
//        onChangeSource(null);
//
//    }

//    public void SetItemToModify(Part itemToModify){
//        partBeingModified = itemToModify;
//        System.out.println("In Set Item To Modify");
//        id.setText(Integer.toString(IdNumber.getNextIdNumber()));
//        name.setText(itemToModify.getName());
//        level.setText(Integer.toString(itemToModify.getStock()));
//        price.setText(Double.toString(itemToModify.getPrice()));
//        min.setText(Integer.toString(itemToModify.getMin()));
//        max.setText(Integer.toString(itemToModify.getMax()));
//        if(itemToModify instanceof Part_InHouse){
//            source.setText(Integer.toString(((Part_InHouse) itemToModify).getMachineId()));
//            radioInHouse.setSelected(true);
//        } else {
//            source.setText( ((Part_Outsourced) itemToModify).getCompanyName());
//            radioOutsourced.setSelected(true);
//        }
//        onChangeSource(null);
//    }

    @FXML
    void onCancelAction(ActionEvent event) {
        System.out.println("Cancel Clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to cancel this action?  Information will not be saved.", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            StageManager.ChangeScene(event, new navInfo_ManageCustomers());
        }
    }

    @FXML
    void onSaveAction(ActionEvent event) {
        System.out.println("Save Clicked");
//        int iLevel = Integer.parseInt(level.getText());
//        int iMin = Integer.parseInt(min.getText());
//        int iMax = Integer.parseInt(max.getText());
//        if(iMin <= iLevel && iLevel <= iMax) {
//            Part newPart;
//            if (radioInHouse.isSelected()) {
//                newPart = new Part_InHouse(
//                        Integer.parseInt(id.getText()),
//                        name.getText(),
//                        Double.parseDouble(price.getText()),
//                        iLevel, iMin, iMax,
//                        Integer.parseInt(source.getText()));
//            } else {
//                newPart = new Part_Outsourced(
//                        Integer.parseInt(id.getText()),
//                        name.getText(),
//                        Double.parseDouble(price.getText()),
//                        iLevel, iMin, iMax,
//                        source.getText());
//                inv.getAllParts().remove(partBeingModified);
//            }
//            inv.addPart(newPart);
//            IdNumber.commitIdNumber();
//
        StageManager.ChangeScene(event, new navInfo_ManageCustomers());
//    } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory level can not be greater than max or less than min", ButtonType.OK);
//            alert.showAndWait();
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddModify_PartController initialize called");
        lblIdValue.setText(customer.getCustomer_ID());
//        id.setText(Integer.toString(IdNumber.getNextIdNumber()));
//        radioInHouse.setSelected(true);
//        onChangeSource(null);
    }

    public void SetAddModifyLabel(String transactionType){
        System.out.println("Trying to set value of lblScreenIdentifier");
//        lblScreenIdentifier.setText(transactionType + " Customer");
    }
}
