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
import models.Geography;
import utils.dataAccess.DAOGeography;
import utils.localization.Locales;
import utils.Utils;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
    Utils utils = new Utils();
    ResourceBundle rb;
    boolean hasLoginAttempted;
    boolean wasLoginSuccessful;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        btnLogIn.setText(rb.getString("Log.In"));
        btnEnglish.setText(rb.getString("English"));
        btnFrench.setText(rb.getString("French"));
        lblDescription.setText(rb.getString("Acme.Appointment.Setter.version.0.0.1"));
        lblDetectedLocalLanguage.setText(rb.getString("Detected.Local.Language"));
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
        lblTimeZone.setText(TimeZone.getDefault().getDisplayName());


    }


    public void onClick_LogIn(ActionEvent event) {
        Globals.setHasLoginBeenAttempted(true);
        System.out.println("Clicked Log In button");
        String userName = textUserName.getText();
        if(utils.CheckPassword(userName, labelPassword.getText())){
            System.out.println("Successful Login");
            String styleString = "-fx-text-fill: #00FF00 ;";
            lblLoginPrompt.setStyle(styleString);
            Globals.setWasLoginSuccessful(true);
            //lblLoginPrompt.setText(rb.getString("Login.Successful"));
            utils.WriteLoginAttempt(userName, true);
            Globals.setUserName(userName);
            Geography.setKnownWorld(DAOGeography.loadKnownWorld());
            if(Geography.isKnownWorldLoaded()) {
                StageManager.ChangeScene(event, new navInfo_Appointments());
            } else {
                System.out.println("KnownWorld was not succesfully loaded, exiting program");
                System.exit(-1);
            }

        } else {
            utils.WriteLoginAttempt(userName, false);
            System.out.println("Failed Login");
            String styleString = "-fx-text-fill: " + utils.getRandomColor() +";";
            lblLoginPrompt.setStyle(styleString);
            lblLoginPrompt.setText(rb.getString("Credentials.not.recognized.please.try.again"));
        }
    }

        public void onClickSetEnglish(ActionEvent actionEvent) {
            if(Locale.getDefault().toString().equals("fr_FR") ){
                Locale.setDefault(Locales.English());
                LoadView();
            } else {
                System.out.println("\""+Locale.getDefault().toString()+"\"");
            }

        }

        public void onClickSetFrench(ActionEvent actionEvent) {
            if(Locale.getDefault().toString().equals("en_US") ){
                Locale.setDefault(Locales.French());
                LoadView();
            } else {
                System.out.println("\""+Locale.getDefault().toString()+"\"");
            }
        }

    public void LoadView()  {
        ResourceBundle rb = ResourceBundle.getBundle("utils/localization/Nat", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LoginScreen.fxml"));
        loader.setResources(rb);
        try {
            Parent root = loader.load();
            Stage s = (Stage) btnLogIn.getScene().getWindow();
            s.setTitle(rb.getString("Acme.Appointment.Setter.version.0.0.1"));
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
