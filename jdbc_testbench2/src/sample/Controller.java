package sample;

import dbAccess.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Country;
import utils.DBConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showMe(ActionEvent actionEvent){
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        for(Country C : countryList){
            System.out.println("Country Id : " + C.getId() + " Name : " + C.getName());
        }
    }

    public void forceCloseConnection(ActionEvent actionEvent) {
        DBConnection.endConnection();
    }
}
