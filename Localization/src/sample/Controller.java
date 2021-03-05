package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button btnManageContacts;
    public Label txtLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnManageContacts.setText(rb.getString("ManageContacts"));
        txtLabel.setText(rb.getString("Thisscreenisusedtomanagecontacts"));
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        loader.setResources(ResourceBundle.getBundle("sample/Nat", Locale.getDefault()));
        try {
            Parent root = loader.load();
            Stage s = (Stage) txtLabel.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}


// ResourceBundleName_languageCode_optionalCountryCode.properties