package sample;

import javafx.event.ActionEvent;

public class Controller  {
    public void onClickData_1_Manage(ActionEvent event) {
        System.out.println("Hello Button 1");
        StageManager.ChangeScene(event, new navInfo_Data_1());
    }


}
