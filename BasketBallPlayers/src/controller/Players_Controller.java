package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BasketballPlayer;

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

    private ObservableList<BasketballPlayer> allPlayers = FXCollections.observableArrayList();
    private ObservableList<BasketballPlayer> fewPlayers = FXCollections.observableArrayList();


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        allTable.setItems(allPlayers);
        allJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        allNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        allTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        fewTable.setItems(fewPlayers);
        fewJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        fewNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fewPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        fewTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));

        allPlayers.add(new BasketballPlayer(8, "Kobe Bryant", 867, "Lakers"));
        allPlayers.add(new BasketballPlayer(11, "Yao Ming", 1234, "Rockets"));
        allPlayers.add(new BasketballPlayer(22, "Clyde Drexler", 1000, "Rockets"));
        allPlayers.add(new BasketballPlayer(34, "Hakeem Olajuwan", 2, "Rockets"));
        allPlayers.add(new BasketballPlayer(45, "Rudy Tomjanovich", 450, "Rockets"));
        allPlayers.add(new BasketballPlayer(13, "Wilt Chamberlin", 1541, "Lakers"));
        allPlayers.add(new BasketballPlayer(34, "Shaquille O'Neal", 45, "Lakers"));
        allPlayers.add(new BasketballPlayer(32, "Magic Johnson", 9786, "Lakers"));

    }

    public void onAddB(ActionEvent actionEvent) {
        System.out.println("On Add Click");
    }

    public void onRemoveB(ActionEvent actionEvent) {
        System.out.println("On Remove Click");
    }
}
