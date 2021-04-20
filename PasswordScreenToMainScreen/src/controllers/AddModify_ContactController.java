package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Globals;
import models.Contact;
import utils.Utils;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DBQueryManager;
import utils.navigation.StageManager;
import utils.navigation.navInfo_ManageContacts;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Only used by admin users to edit contacts.
 */
public class AddModify_ContactController implements Initializable {
    public Label lblScreenIdentifier;
    public Label lblContactID;
    public Label lblName;
    public Label lblEmailAddress;
    public TextField txtName;
    public TextField txtEmailAddress;
    public Label lblIdValue;
    public Button btnSave;
    public Button btnCancel;

    public static Contact contact;
    public static String addEdit;
    Utils utils = new Utils();
    private DAOContacts dao = new DAOContacts(new DBQueryManager());
    private ResourceBundle rb = Globals.getResourceBundle();



    /**
     * Handles the click on the cancel button.
     * Confirms with user that they are OK with deleting the contact.
     * @param event
     */
    @FXML public void onCancelAction(ActionEvent event) {
        System.out.println("Cancel Clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING,
                rb.getString("AreYouSureYouWantToCancel_Long"), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            StageManager.ChangeScene(event, new navInfo_ManageContacts());
        }
    }

    /**
     * Handles the click on the Save button.
     *
     * @param event
     */
    @FXML public void onSaveAction(ActionEvent event) {
        System.out.println("Save Clicked");
        contact.contact_Name = txtName.getText();
        contact.email = txtEmailAddress.getText();
        dao.insertOrUpdateContact(contact);
        if(addEdit == "ADD"){
            utils.commitNextIdNumber();
        }
        StageManager.ChangeScene(event, new navInfo_ManageContacts());
    }

    /**
     * Main entry point of controller.
     * Handles inbound Contact object and localizes the screen controls.
     * @param location
     * @param resources
     */
    @Override public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddModify_ContactsController initialize called");
        HandleInboundContactObject();
        lblIdValue.setText(String.valueOf(contact.contact_ID));
        txtName.setText(contact.contact_Name);
        txtEmailAddress.setText(contact.email);
        LocalizeTextOnControlsAndHeaders();
    }

    private void LocalizeTextOnControlsAndHeaders() {
        lblName.setText(rb.getString("Name"));
        lblEmailAddress.setText(rb.getString("Email.Address"));
        btnSave.setText(rb.getString("Save"));
        btnCancel.setText(rb.getString("Cancel"));
        lblContactID.setText(rb.getString("Contact.Id"));

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
            lblScreenIdentifier.setText(rb.getString("Add.Contact"));
        } else {
            if (addEdit != "EDIT"){
                System.out.println("the value of addEdit is " + addEdit);
                System.out.println("the value of addEdit should only ever be ADD or EDIT");
                System.exit(-1);
            }
            lblScreenIdentifier.setText(rb.getString("Edit.Contact"));
        }
    }
}
