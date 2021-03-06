package sample;

import dbAccess.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Country;
import utils.DBConnection;
import utils.JunkCountryManager;
import utils.PreparedQueryExample;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField txtCountryName;
    JunkCountryManager jcm = new JunkCountryManager();
    //JunkDrawer jd = new JunkDrawer();

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

    public void addUSSR(ActionEvent actionEvent) {
        PreparedQueryExample pqe = new PreparedQueryExample();
        pqe.AddUSSR();
//        jcm.setCountryName("USSR");
//        System.out.println(jcm.getCountryName());
//        System.out.println(jcm.getInsertStatement());
//        jcm.RunInsert();
    }

    public void deleteAllUSSR(ActionEvent actionEvent) {
        PreparedQueryExample pqe = new PreparedQueryExample();
        pqe.DeleteAllUSSR();
//        jcm.setCountryName("USSR");
//        jcm.RunDelete();
    }

    public void btnAddCountry(ActionEvent actionEvent) {
        jcm.setCountryName(txtCountryName.getText());
        jcm.RunInsert();
    }

    public void btnRemoveCountry(ActionEvent actionEvent) {
        jcm.setCountryName(txtCountryName.getText());
        jcm.RunDelete();
    }

    public void btnUpdateUSSRToJapan(ActionEvent actionEvent) {
        PreparedQueryExample pqe = new PreparedQueryExample();
        pqe.UpdateUSSRToJapan();

    }
}
