package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import models.Appointments;
import utils.dataAccess.DAOAppointments;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.util.ResourceBundle;

public class Reports_Controller implements Initializable {
    public TextArea taReport;
    public Button btnReturnToAppointmentScreen;
    public Button btnAppointmentTypes;
    public Button btnAppointmentsByContact;
    public Button btnAppointmentsByCustomer;

    DAOAppointments dao = new DAOAppointments();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        taReport.editableProperty().setValue(false);

    }



    public void onClick_btnReturnToAppointmentScreen(ActionEvent actionEvent) {
        StageManager.ChangeScene(actionEvent, new navInfo_Appointments());
    }

    private void addLine(String line){
        taReport.appendText(line + "\n");
    }

    public void onClick_AppointmentByTypeAndByMonth(ActionEvent actionEvent) {
        String sqlStatement = "SELECT * FROM appointments order by type, start;";
        Appointments appointments = dao.selectAppointmentsFromSQLStatement(sqlStatement);
        taReport.setText("");
        addLine("Appointments by Type and by Month");
        addLine("");
        addLine("Report starts here");
        // TODO replace "Report start here" with some kind of loop, put in a header for each type and month.

    }

    public void onClick_AppointmentsByContact(ActionEvent actionEvent) {
        taReport.setText("");
        addLine("Appointments by Contact");
        addLine("");
        addLine("Report starts here");
    }

    public void onClick_AppointmentsByCustomer(ActionEvent actionEvent) {
        taReport.setText("");
        addLine("Appointments by Customer");
        addLine("");
        addLine("Report starts here");
    }
}
