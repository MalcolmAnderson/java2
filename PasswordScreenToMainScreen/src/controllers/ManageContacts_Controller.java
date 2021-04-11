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
import models.Contact;
import models.Contacts;
import models.Customers;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DBQueryManager;
import utils.navigation.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageContacts_Controller implements Initializable {

    public Button btnAddContact;
    public Button btnEditContact;
    public Button btnDeleteContact;
    public TableView tvContacts;
    public TableColumn tcContactID;
    public TableColumn tcContactName;
    public TableColumn tcContactEmail;

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private DAOContacts dao = new DAOContacts(new DBQueryManager());
    private navInfo_ManageContacts navInfo = new navInfo_ManageContacts();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
//        SetButtonColors();
        SetColumnPropertyValues();
        RefreshScreenFromDataBase();
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

    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

    public void onClick_DeleteContact(ActionEvent actionEvent) {
        int selectedContactIndex = tvContacts.getSelectionModel().getSelectedIndex();
        Contact selectedContact = (Contact) tvContacts.getSelectionModel().getSelectedItem();
        System.out.println("Selected Contact Index = " + selectedContactIndex);
        if (selectedContactIndex != -1){
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete this contact?",
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
                    "Please select a contact to delete",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void onClick_AddContact(ActionEvent actionEvent) {
        System.out.println("ProductAdd Clicked");
        AddModify_ContactController.addEdit = "ADD";
        AddModify_ContactController.contact = new Contact();
        StageManager.ChangeScene(actionEvent, new navInfo_AddEditContact());
    }

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
                    "Please select a contact to edit",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void onClickAddEric(ActionEvent actionEvent) {
        Contact eric = new Contact(4, "Eric Estrada", "Eric@Estrada.com");
        dao.insertOrUpdateContact(eric);
        RefreshScreenFromDataBase();
    }
}
