package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Players_Controller implements Initializable {
    public TableView allTable;
    public TableColumn allJerseyCol;
    public TableColumn allNameCol;
    public TableColumn allPointsCol;
    public TableColumn allTeamColumn;
    public TableView fewTable;
    public TableColumn fewJerseyCol;
    public TableColumn fewNameCol;
    public TableColumn fewPointsCol;
    public TableColumn fewTeamColumn;
    public Button addB;
    public Button removeB;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        int x = 0;
        x++;
    }

    public void onAddB(ActionEvent actionEvent) {
        System.out.println("On Add Click");
    }

    public void onRemoveB(ActionEvent actionEvent) {
        System.out.println("On Remove Click");
    }
}
