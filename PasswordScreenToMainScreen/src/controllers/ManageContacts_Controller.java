package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import main.Globals;
import models.Contact;
import models.Contacts;
import models.Customers;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DBQueryManager;
import utils.navigation.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Manage Contacts screen.
 * Only accessible by the admin user.
 */
public class ManageContacts_Controller implements Initializable {

    public Button btnAddContact;
    public Button btnEditContact;
    public Button btnDeleteContact;
    public TableView tvContacts;
    public TableColumn tcContactID;
    public TableColumn tcContactName;
    public TableColumn tcContactEmail;
    public Label lblScreenPurpose;
    public Button btnReturnToAppointmentScreen;
    ResourceBundle rb = Globals.getResourceBundle();

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private DAOContacts dao = new DAOContacts(new DBQueryManager());
    private navInfo_ManageContacts navInfo = new navInfo_ManageContacts();

    /**
     * Entry point for the Manage Contacts screen.
     * @param url - JavaFx infrastructure parameter
     * @param resourceBundle - JavaFx infrastructure parameter
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
//        SetButtonColors();
        LocalizeTextOnControlsAndHeaders();

        SetColumnPropertyValues();
        RefreshScreenFromDataBase();
    }

    private void LocalizeTextOnControlsAndHeaders() {
        btnAddContact.setText(rb.getString("Add.Contact"));
        btnEditContact.setText(rb.getString("Edit.Contact"));
        btnDeleteContact.setText(rb.getString("Delete.Contact"));
        tcContactID.setText(rb.getString("Contact.Id"));
        tcContactName.setText(rb.getString("Name"));
        tcContactEmail.setText(rb.getString("Email.Address"));
        lblScreenPurpose.setText(rb.getString("Manage.Contact.Purpose"));
        btnReturnToAppointmentScreen.setText(rb.getString("Return.To.Appointment.Screen"));
    }

    private void RefreshScreenFromDataBase() {
        dao.selectAllContacts();
        Contacts contacts = dao.getAllContacts();
        allContacts.setAll(contacts.getContacts());
        tvContacts.setItems(allContacts);
    }

    private void SetColumnPropertyValues() {
        tcContactID.setCellValueFactory(new PropertyValueFactory<>("contact_ID"));
        tcContactName.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        tcContactEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));
        btnAddContact.setBackground(bg_Red);
        btnAddContact.setTextFill(Color.WHITE);
        btnEditContact.setBackground(bg_Red);
        btnEditContact.setTextFill(Color.WHITE);
//        btnDeleteContact.setBackground(bg_Red);
//        btnDeleteContact.setTextFill(Color.WHITE);
    }

    /**
     * Handles the user choosing to cancel back to the appointment screen.
     * @param event - JavaFx infrastructure parameter
     */
    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    /**
     * Handles the deletion of a contact.
     * Ask user to confirm the deletion of the currently selected contact
     * @param actionEvent - JavaFx infrastructure parameter
     */
    public void onClick_DeleteContact(ActionEvent actionEvent) {
        int selectedContactIndex = tvContacts.getSelectionModel().getSelectedIndex();
        Contact selectedContact = (Contact) tvContacts.getSelectionModel().getSelectedItem();
        System.out.println("Selected Contact Index = " + selectedContactIndex);
        if (selectedContactIndex != -1){
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    rb.getString("Delete.Contact.Question"),
                    ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                tvContacts.getItems().remove(selectedContactIndex);
                dao.deleteContactByID(selectedContact.contact_ID);

            }
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    rb.getString("Please.select.a.contact.to.delete"),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Creates a new contact object and passes it and control to the add modify contact screen.
     * @param actionEvent - JavaFx infrastructure parameter
     */
    public void onClick_AddContact(ActionEvent actionEvent) {
        System.out.println("ProductAdd Clicked");
        AddModify_ContactController.addEdit = "ADD";
        AddModify_ContactController.contact = new Contact();
        StageManager.ChangeScene(actionEvent, new navInfo_AddEditContact());
    }

    /**
     * Takes the selected contact and passes it and control to the add modify contact screen
     * @param actionEvent - JavaFx infrastructure parameter
     */
    public void onClick_EditContacts(ActionEvent actionEvent) {
        int selectedContactIndex = tvContacts.getSelectionModel().getSelectedIndex();
        Contact selectedContact = (Contact) tvContacts.getSelectionModel().getSelectedItem();
        System.out.println("Selected Contact Index = " + selectedContactIndex);
        if (selectedContactIndex != -1){
            AddModify_ContactController.contact = selectedContact;
            AddModify_ContactController.addEdit = "EDIT";
            StageManager.ChangeScene(actionEvent, new navInfo_AddEditContact());
        }else {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    rb.getString("Please.select.a.contact.to.edit"),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

//    public void onClickAddEric(ActionEvent actionEvent) {
//        Contact eric = new Contact(4, "Eric Estrada", "Eric@Estrada.com");
//        dao.insertOrUpdateContact(eric);
//        RefreshScreenFromDataBase();
//    }
}
