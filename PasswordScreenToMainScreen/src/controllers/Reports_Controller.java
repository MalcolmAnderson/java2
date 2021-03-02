package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import utils_Navigation.StageManager;
import utils_Navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class Reports_Controller implements Initializable {
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onClick_Cancel(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_Appointments());
    }

}
