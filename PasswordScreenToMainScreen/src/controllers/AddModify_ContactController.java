package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Contact;
import utils.Utils;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DBQueryManager;
import utils.navigation.StageManager;
import utils.navigation.navInfo_ManageContacts;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModify_ContactController implements Initializable {
    public Label lblScreenIdentifier;
    public Label lblContactID;
    public Label lblName;
    public Label lblEmailAddress;
    public TextField txtName;
    public TextField txtEmailAddress;
    public Label lblIdValue;

    public static Contact contact;
    public static String addEdit;
    Utils utils = new Utils();
    private DAOContacts dao = new DAOContacts(new DBQueryManager());


    @FXML
    void onCancelAction(ActionEvent event) {
        System.out.println("Cancel Clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to cancel this action?  Information will not be saved.", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            StageManager.ChangeScene(event, new navInfo_ManageContacts());
        }
    }

    @FXML
    void onSaveAction(ActionEvent event) {
        System.out.println("Save Clicked");
        contact.contact_Name = txtName.getText();
        contact.email = txtEmailAddress.getText();
        dao.insertOrUpdateContact(contact);
        if(addEdit == "ADD"){
            utils.commitNextIdNumber();
        }
        StageManager.ChangeScene(event, new navInfo_ManageContacts());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddModify_ContactsController initialize called");
        HandleInboundContactObject();
        lblIdValue.setText(String.valueOf(contact.contact_ID));
        txtName.setText(contact.contact_Name);
        txtEmailAddress.setText(contact.email);
    }

    private void HandleInboundContactObject() {
        if(contact == null){
            if(addEdit == "EDIT"){
                System.out.println("Edit should not be able to submit a null Contact");
                System.exit(-1);
            }
            contact = new Contact();
            contact.contact_ID = utils.getNextIdNumber();
        } else if (contact.contact_ID == -1){
            contact.contact_ID = utils.getNextIdNumber();
        }
        if(addEdit == "ADD"){
            lblScreenIdentifier.setText("Add Contact");
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText("Edit Contact");
        }
    }
}
