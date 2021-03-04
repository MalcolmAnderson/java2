package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;
import java.util.TimeZone;

import main.Utils;
import utils_Navigation.StageManager;
import utils_Navigation.navInfo_Appointments;

import javax.print.attribute.standard.DateTimeAtProcessing;

public class LoginScreen_Controller implements Initializable {
    public PasswordField labelPassword;
    public TextField textUserName;
    public Label labelPrompt; //  = "Enter credentials and then click Log In button"
    public Button btnEnglish;
    public Button btnFrench;
    public Label txtLoginPrompt;
    Utils utils = new Utils();

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
//            navTools.openMainScreenWhileGettingAppointments(event, "/views/Appointments.fxml");

        } else {
            utils.WriteLoginAttempt(userName, false);
            System.out.println("Failed Login");
            String styleString = "-fx-text-fill: " + utils.getRandomColor() +";";
            labelPrompt.setStyle(styleString);
            labelPrompt.setText("Credentials not recognized, please try again.");
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
