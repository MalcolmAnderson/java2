package controllers;

import com.mysql.jdbc.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.stage.Stage;
import main.Locales;
import main.Utils;
import utils_Navigation.StageManager;
import utils_Navigation.navInfo_Appointments;
import utils_Navigation.navInfo_LogIn;

import javax.print.attribute.standard.DateTimeAtProcessing;

public class LoginScreen_Controller implements Initializable {
    public PasswordField labelPassword;
    public TextField textUserName;
    public Label labelPrompt; //  = "Enter credentials and then click Log In button"
    public Button btnEnglish;
    public Button btnFrench;
    public Label txtLoginPrompt;
    public Button btnLogIn;
    public Label lblDescription;
    Utils utils = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle rc) {
        btnLogIn.setText(rc.getString("Log.In"));
        btnEnglish.setText(rc.getString("English"));
        btnFrench.setText(rc.getString("French"));
        lblDescription.setText(rc.getString("Acme.Appointment.Setter.version.0.0.1"));

    }


    public void onClick_LogIn(ActionEvent event) {
        System.out.println("Clicked Log In button");
        String userName = textUserName.getText();
        if(utils.CheckPassword(userName, labelPassword.getText())){
            System.out.println("Successful Login");
            String styleString = "-fx-text-fill: #00FF00 ;";
            labelPrompt.setStyle(styleString);
            labelPrompt.setText("Login Successful.");
            utils.WriteLoginAttempt(userName, true);
            StageManager.ChangeScene(event, new navInfo_Appointments());

        } else {
            utils.WriteLoginAttempt(userName, false);
            System.out.println("Failed Login");
            String styleString = "-fx-text-fill: " + utils.getRandomColor() +";";
            labelPrompt.setStyle(styleString);
            labelPrompt.setText("Credentials not recognized, please try again.");
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
//                StageManager.ChangeScene(actionEvent, new navInfo_LogIn());
                LoadView();
            } else {
                System.out.println("\""+Locale.getDefault().toString()+"\"");
            }
        }

    public void LoadView()  {
        ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
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

//    public void onClickSetEnglish(ActionEvent actionEvent) {
//    }
//
//    public void onClickSetFrench(ActionEvent actionEvent) {
//    }
}
