package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Globals;
import models.Contacts;
import models.Customers;
import models.Geography;
import utils.TimeConversion;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DAOCustomers;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBQueryManager;
import utils.localization.Locales;
import utils.Utils;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Called from Main, manages the login and if successful, sets up initial state.
 */
public class LoginScreen_Controller implements Initializable {
    public PasswordField labelPassword;
    public TextField textUserName;
    public Button btnEnglish;
    public Button btnFrench;
    public Label lblLoginPrompt;
    public Button btnLogIn;
    public Label lblDescription;
    public Label lblChangeAppLanguage;
    public Label lblDetectedLocalLanguage;
    public Label localLanguage;
    public Label lblUserName;
    public Label lblPassword;
    public Label lblTimeZone;
    public Label lblDetectedTimeZone;
    Utils utils = new Utils();
    ResourceBundle rb = Globals.getResourceBundle();
    boolean hasLoginAttempted;
    boolean wasLoginSuccessful;
    private DAOCustomers daoCustomers = new DAOCustomers();
    private Customers masterCustomers;
    private DAOContacts daoContacts = new DAOContacts(new DBQueryManager());
    private Contacts masterContacts;


    /**
     * Entry point for the controller.
     * Sets some state into the Globals store and gets the local time zone Id.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
//        this.rb = Globals.getResourceBundle();
        Globals.setStillFirstLogin(true);
        LocalizeTextOnControlsAndHeaders();
        TimeConversion tc = new TimeConversion();
        lblTimeZone.setText(tc.getLocalZoneID().toString());
    }

    private void LocalizeTextOnControlsAndHeaders() {
        btnLogIn.setText(rb.getString("Log.In"));
        btnEnglish.setText(rb.getString("English"));
        btnFrench.setText(rb.getString("French"));
        lblDescription.setText(rb.getString("Acme.Appointment.Setter.version.1.0.0"));
        lblDetectedLocalLanguage.setText(rb.getString("Detected.Local.Language"));
        lblDetectedTimeZone.setText(rb.getString("Detected.Time.Zone"));
        if(Globals.getLocalLanguage().equals("fr_FR") ){
            localLanguage.setText(rb.getString("French"));
        } else {
            localLanguage.setText(rb.getString("English"));
        }
        wasLoginSuccessful = Globals.getWasLoginSuccessful();
        hasLoginAttempted = Globals.getHasLoginBeenAttempted();
        if(hasLoginAttempted){
            if(wasLoginSuccessful){
                lblLoginPrompt.setText(rb.getString("Login.Successful"));
            } else {
                lblLoginPrompt.setText(rb.getString("Credentials.not.recognized.please.try.again"));
            }
        } else {
            lblLoginPrompt.setText(rb.getString("Enter.credentials.and.then.click.Log.In.button"));
        }
        lblUserName.setText(rb.getString("User.Name"));
        lblPassword.setText(rb.getString("Password"));
    }

    /**
     * Performs log in checks.
     * Checks the user name and password, and then passes control to the success or failure methods.
     * @param event
     */
    public void onClick_LogIn(ActionEvent event) {
        Globals.setHasLoginBeenAttempted(true);
        System.out.println("Clicked Log In button");
        String userName = textUserName.getText();
        if(utils.CheckPassword(userName, labelPassword.getText())){
            System.out.println("Successful Login");
            processSuccessfulLoginAndOpenMainScreen(event, userName);
        } else {
            processFailedLoginAndSetErrorMessage(userName);
        }
        System.out.println("Leaving onClick_LogIn");
    }

    private void processFailedLoginAndSetErrorMessage(String userName) {
        utils.WriteLoginAttempt(userName, false);
        System.out.println("Failed Login");
        String styleString = "-fx-text-fill: " + utils.getRandomColor() +";";
        lblLoginPrompt.setStyle(styleString);
        lblLoginPrompt.setText(rb.getString("Credentials.not.recognized.please.try.again"));
    }

    private void processSuccessfulLoginAndOpenMainScreen(ActionEvent event, String userName) {
        System.out.println("Entering processSuccessfulLoginAndOpenMainScreen");
        Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        Globals.setWasLoginSuccessful(true);
        utils.WriteLoginAttempt(userName, true);
        Globals.setUserName(userName);
        Globals.setMasterCustomers(daoCustomers.selectAllCustomers());
        daoContacts.selectAllContacts();
        Globals.setMasterContacts(daoContacts.getAllContacts());
        Globals.setSelectedRadioButtonName("rbByWeek");
        Globals.setCurrentReferenceDate(LocalDateTime.now());

        String styleString = "-fx-text-fill: #00FF00 ;";
        lblLoginPrompt.setStyle(styleString);
        if(Geography.isKnownWorldLoaded()) {
            StageManager.ChangeScene(event, new navInfo_Appointments());
        } else {
            System.out.println("KnownWorld was not succesfully loaded, exiting program");
            System.exit(-1);
        }
        System.out.println("Leaving processSuccessfulLoginAndOpenMainScreen");
    }

    /**
     * Handles setting all language to English.
     * This routine will not be in the production version.
     * @param actionEvent
     */
    public void onClickSetEnglish(ActionEvent actionEvent) {
        if(Locale.getDefault().toString().equals("fr_FR") ){
            Locale.setDefault(Locales.English());
            LoadView();
        } else {
            System.out.println("\""+Locale.getDefault().toString()+"\"");
        }
    }


    /**
     * Handles setting all language to French.
     * This routine will not be in the production version.
     * @param actionEvent
     */
    public void onClickSetFrench(ActionEvent actionEvent) {
        if(Locale.getDefault().toString().equals("en_US") ){
            Locale.setDefault(Locales.French());
            LoadView();
        } else {
            System.out.println("\""+Locale.getDefault().toString()+"\"");
        }
    }

    /**
     * The initial screen loader.
     * This handcrafted navigation tool should probably be replaced by one of my navigation library tools.
     */
    public void LoadView()  {
        ResourceBundle rb = ResourceBundle.getBundle("utils/localization/Nat", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        loader.setResources(rb);
        LocalizeTextOnControlsAndHeaders();
        try {
            Parent root = loader.load();
            Stage s = (Stage) btnLogIn.getScene().getWindow();
            s.setTitle(rb.getString("Acme.Appointment.Setter.version.1.0.0"));
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
