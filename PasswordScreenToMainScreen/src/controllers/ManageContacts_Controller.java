package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Contact;
import models.Customer;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageContacts_Controller implements Initializable {
    public TableColumn tcContactID;
    public TableColumn tcContactName;
    public TableColumn tcContactEmail;
    public Button btnAddContact;
    public Button btnEditContact;
    public Button btnDeleteContact;
    public TableView tvContacts;

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private DAOContacts dao = new DAOContacts();


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }


    public void onClick_ManageContacts(ActionEvent event) {
    }
}
