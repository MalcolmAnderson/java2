package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import models.Appointment;
import models.Appointments;
import utils.dataAccess.DAOAppointments;
import utils.dataAccess.DAOCustomers;
import utils.navigation.StageManager;
import utils.navigation.navInfo_Appointments;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.*;

public class Reports_Controller implements Initializable {
    public TextArea taReport;
    public Button btnReturnToAppointmentScreen;
    public Button btnAppointmentTypes;
    public Button btnAppointmentsByContact;
    public Button btnAppointmentsByCustomer;

    DAOAppointments dao = new DAOAppointments();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        taReport.editableProperty().setValue(false);
//        SetButtonColors();
    }

    private void SetButtonColors() {
        Background bg_Red = new Background(new BackgroundFill(Color.RED, null, null));
        Background bg_Yellow = new Background(new BackgroundFill(Color.YELLOW, null, null));


        btnAppointmentsByCustomer.setBackground(bg_Yellow);
//        btnAppointmentsByCustomer.setTextFill(Color.WHITE);
//        btnAppointmentsByContact.setBackground(bg_Yellow);
//        btnAppointmentsByCustomer.setTextFill(Color.WHITE);
    }

    public void onClick_btnReturnToAppointmentScreen(ActionEvent actionEvent) {
        StageManager.ChangeScene(actionEvent, new navInfo_Appointments());
    }

    private void addLine(String line){
        taReport.appendText(line + "\n");
    }

    public void onClick_AppointmentByTypeAndByMonth(ActionEvent actionEvent) {
        String sqlStatement = "SELECT * FROM appointments order by start, type;";
        Appointments appointments = dao.selectAppointmentsFromSQLStatement(sqlStatement);
        taReport.setText("");
        addLine("Appointments by Type and by Month");
        HashMap<String, Integer> types = new HashMap<String, Integer>();

        int currentYear = -1;
        int currentMonth = -1;
        int thisYear = -1;
        int thisMonth = -1;

        boolean newMonth;
        for (Appointment appointment : appointments.getAllAppointments()) {
            String curType = appointment.getType();
            thisYear = appointment.getStart().getYear();
            thisMonth = appointment.getStart().getMonthValue();
            newMonth = ((thisYear != currentYear) || (thisMonth != currentMonth));
            if(newMonth){
                printAppointmentsByTypeAndByMonth(types, currentYear, currentMonth);
                currentYear = thisYear;
                currentMonth = thisMonth;
                types.clear();
            }
            if(types.get(curType) == null){
                types.put(curType, 1);
            } else {
                int count = types.get(curType) + 1;
                types.put(curType, count);
            }
        }
        printAppointmentsByTypeAndByMonth(types, currentYear, currentMonth);
    }

    private void printAppointmentsByTypeAndByMonth(HashMap<String, Integer> types, int currentYear, int currentMonth) {
        if(types.size() > 0){
            addLine("");
            addLine("Type counts for  " + currentYear + " - " + new DateFormatSymbols().getMonths()[currentMonth - 1]);
            for(String type : types.keySet()){
                addLine(type + " - " + types.get(type));
            }
        }
        types.clear();
    }

    public void onClick_AppointmentsByContact(ActionEvent actionEvent) {
        String sqlStatement = "SELECT * FROM appointments order by Contact_ID, Start;";
        Appointments appointments = dao.selectAppointmentsFromSQLStatement(sqlStatement);
        taReport.setText("");
        addLine("Schedule of Appointments by Contact");
//        addLine("");

        int currentContact = -1;
        int thisContact = -1;

        boolean newContact;
        for (Appointment a : appointments.getAllAppointments()) {
            thisContact = a.getContact_Id();
            newContact = (thisContact != currentContact);
            if(newContact){
                currentContact = thisContact;
                String contactName = a.getContactName();
                addLine("");
                addLine("Appointments for Contact " + contactName);
            }

            String line = String.format("%s - %s - %s - %s -  %s - %s - %s",
                    String.valueOf(a.getId()), a.getTitle(), a.getType(), a.getDescription(), a.getStartDisplay(),
                    a.getEndDisplay(), String.valueOf(a.getCustomer_Id()));
            addLine(line);
        }
    }

    public void onClick_AppointmentsByCustomer(ActionEvent actionEvent) {
        DAOCustomers daoCustomers = new DAOCustomers();
        String sqlStatement = "SELECT * FROM appointments order by Customer_ID, Start;";
        Appointments appointments = dao.selectAppointmentsFromSQLStatement(sqlStatement);
        taReport.setText("");
        addLine("Appointments by Customer");
//        addLine("");

        int currentCustomer = -1;
        int thisCustomer = -1;

        boolean newCustomer;
        for (Appointment a : appointments.getAllAppointments()) {
            thisCustomer = a.getCustomer_Id();
            newCustomer = (thisCustomer != currentCustomer);
            if(newCustomer){
                currentCustomer = thisCustomer;
//                String customerName = a.getContactName();
                addLine("");
                addLine("Appointments for Customer " + currentCustomer);
            }

            String line = String.format("%s - %s - %s - %s -  %s - %s - %s",
                    String.valueOf(a.getId()), a.getTitle(), a.getType(), a.getDescription(), a.getStartDisplay(),
                    a.getEndDisplay(), String.valueOf(a.getContactName()));
            addLine(line);
        }


    }
}
