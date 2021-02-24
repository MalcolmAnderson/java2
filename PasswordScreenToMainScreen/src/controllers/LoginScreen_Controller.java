package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import main.Utils;

public class LoginScreen_Controller implements Initializable {
    public PasswordField labelPassword;
    public TextField labelUserName;
    public Label labelPrompt; //  = "Enter credentials and then click Log In button"
    Utils utils = new Utils();
    FxmlNavigationTools navTools = new FxmlNavigationTools();

    public void onClick_LogIn(ActionEvent event) {
        System.out.println("Clicked Log In button");
        if(utils.CheckPassword(labelUserName.getText(), labelPassword.getText())){
            System.out.println("Successful Login");
            String styleString = "-fx-text-fill: #00FF00 ;";
            labelPrompt.setStyle(styleString);
            labelPrompt.setText("Login Successful.");
            navTools.openMainScreenWhileGettingInventory(event, "/views/MainScreen.fxml");
        } else {
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
