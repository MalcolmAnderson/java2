package sample;

import javafx.event.ActionEvent;

public class Submenu2_Controller {
    public void onClickBackToMain(ActionEvent event) {
        StageManager.ChangeScene(event, new navInfo_MainScreen());
    }
}
