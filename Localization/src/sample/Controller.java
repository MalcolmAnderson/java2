package sample;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button btnManageContacts;
    public Label txtLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
        btnManageContacts.setText(rb.getString("ManageContacts"));
        txtLabel.setText(rb.getString("Thisscreenisusedtomanagecontacts"));
    }
}


// ResourceBundleName_languageCode_optionalCountryCode.properties