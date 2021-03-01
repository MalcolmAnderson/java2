package controllers;

import utils_Navigation.StageManager;
import utils_Navigation.navInfo_Submenu_2;
import javafx.event.ActionEvent;

public class Data1_Controller {
    public void onClick_SubMenu_2(ActionEvent event) {
        System.out.println("Hello SubMenu_2");
        StageManager.ChangeScene(event, new navInfo_Submenu_2());
    }
}
