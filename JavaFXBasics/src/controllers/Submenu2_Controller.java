package controllers;

import utils_Navigation.StageManager;
import utils_Navigation.navInfo_MainScreen;
import javafx.event.ActionEvent;

public class Submenu2_Controller {
    public void onClickBackToMain(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_MainScreen());
    }
}
