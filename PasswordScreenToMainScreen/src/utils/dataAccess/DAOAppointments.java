package utils.dataAccess;

import main.Globals;
import models.Appointment;
import models.Appointments;
import org.junit.jupiter.api.Disabled;
import utils.Utils;
import java.sql.*;
import java.time.LocalDateTime;

public class DAOAppointments {

    private DBQueryManager dbQM = new DBQueryManager();
    private DAOContacts daoContacts = new DAOContacts(dbQM);
    private Utils utils = new Utils();

    public Appointments selectAllAppointments(){
        if(!daoContacts.ContactsHaveBeenLoaded()){
            System.out.println("Contacts must be loaded before running DAOAppointments.selectAllAppointments()");
            System.exit(-1);
        }
        String sql = "SELECT * FROM appointments;";
        Appointments appointments = selectAppointmentsFromSQLStatement(sql);
        return appointments;
    }

    public Appointments selectAppointmentsInDateRange(LocalDateTime start, LocalDateTime end){
        if(!daoContacts.ContactsHaveBeenLoaded()){
            System.out.println("Contacts must be loaded before running DAOAppointments.selectAllAppointments()");
            System.exit(-1);
        }
        String sql = getBetweenSQLStatement(start, end);
        System.out.println(sql);
        Appointments appointments = selectAppointmentsFromSQLStatement(sql);
        return appointments;
    }

    public String getBetweenSQLStatement(LocalDateTime start, LocalDateTime end) {
        start = utils.Local_ToUTC(start);
        end = utils.Local_ToUTC(end);
        Timestamp tsStart = Timestamp.valueOf(start);
        Timestamp tsEnd = Timestamp.valueOf(end);
        return String.format("SELECT * FROM appointments where Start >= '%s' AND End <= '%s';",
                    tsStart.toString(), tsEnd.toString());
    }

    public Appointments selectAppointmentsFromSQLStatement(String sql) {
        Appointments appointments = new Appointments();
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appointment current = CreateAppointmentFromResultSetRow(rs);
                current.setContact(daoContacts.getContactByContactId(current.getContact_Id()));
                appointments.addAppointment(current);
            }
        } catch (SQLException throwables) {
            System.out.println( throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        return appointments;
    }

    private Appointment CreateAppointmentFromResultSetRow(ResultSet rs) throws SQLException {
        // TODO document how this function sets the appointment date and time to local time
        Utils utils = new Utils();
        int appointmentId = rs.getInt("Appointment_Id");
        String title = rs.getString("Title");
        String description = rs.getString("Description");
        String location = rs.getString("Location");
        String type = rs.getString("Type");
        LocalDateTime start = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Start"));
        start = utils.UTC_ToLocal(start);
        LocalDateTime end = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("End"));
        end = utils.UTC_ToLocal(end);
        int customerId = rs.getInt("Customer_ID");
        int contactId = rs.getInt("Contact_ID");
        Appointment current = new Appointment(appointmentId, title, description, location, type, start, end, customerId, contactId);
        return current;
    }

    public void insertOrUpdateAppointment(Appointment current) {
        String sqlStatement = "";
        tableInfo a = new tableInfo_Appointments();
        if(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), current.getId())){
            sqlStatement = createStatement_UpdateAppointment(current);
            System.out.println("DAOAppointment - insertOrUpdateAppointment - Update Statement");
        } else {
            sqlStatement = createStatement_InsertAppointment(current);
            System.out.println("DAOAppointment - insertOrUpdateAppointment - Insert Statement");
        }
        System.out.println(sqlStatement);
        dbQM.RunSQLString(sqlStatement);
    }

    public void deleteAppointmentsByCustomerId(int customer_id) {
        String deleteStatement = String.format(
                "DELETE FROM appointments WHERE Customer_ID = %s", customer_id);
        System.out.println("DAOAppointments - deleteAppointmentsByCustomerId - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    public void deleteAppointmentsByAppointmentId(int appointment_id) {
        String deleteStatement = String.format(
                "DELETE FROM appointments WHERE Appointment_ID = %s", appointment_id);
        System.out.println("DAOAppointments - deleteAppointmentsByAppointmentId - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    public String createStatement_InsertAppointment(Appointment current) {
        LocalDateTime now = utils.now();
        String insertStatement = String.format(
                "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, "
                        + "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, "
                        + "Customer_ID, User_ID, Contact_ID) "
                        + "VALUES ("
                        + "'%s', '%s', '%s', '%s', '%s', "
                        + "'%s', '%s', '%s', '%s', '%s', "
                        + "'%s', %s, %s, %s);",
                current.getId(), current.getTitle(), current.getDescription(),
                current.getLocation(), current.getType(), utils.Local_ToUTC(current.getStart()),
                utils.Local_ToUTC(current.getEnd()), utils.Local_ToUTC(now), Globals.getUserId(),
                utils.Local_ToUTC(now), Globals.getUserId(), current.getCustomer_Id(),
                Globals.getUserId(),  current.getContact_Id());
        System.out.println("Insert Statement");
        System.out.println(insertStatement);
        return insertStatement;
    }

    @Disabled public String createStatement_UpdateAppointment(Appointment current) {
        LocalDateTime now = utils.now();
        String updateStatement = String.format(
                "UPDATE appointments SET"
                        + " Title = '%s', Description = '%s',"
                        + " Location = '%s', Type = '%s', Start = '%s', End  = '%s',"
                        + " Last_Update = '%s',"
                        + " Last_Updated_By = '%s', Customer_ID = %s, User_ID = %s,"
                        + " Contact_ID = %s WHERE Appointment_ID = %s",
                current.getTitle(), current.getDescription(),
                current.getLocation(), current.getType(), utils.Local_ToUTC(current.getStart()),
                utils.Local_ToUTC(current.getEnd()), utils.Local_ToUTC(now), Globals.getUserId(),
                current.getCustomer_Id(), Globals.getUserId(),  current.getContact_Id(), current.getId());
        System.out.println("update Statement");
        System.out.println(updateStatement);
        return updateStatement;
    }

    public String createStatement_DeleteAppointmentByAppointmentId(int appointmentId) {
        System.out.println("DAOAppointments - createStatement_DeleteAppointmentByAppointmentId");
        String deleteStatement = String.format(
                "DELETE FROM appointments WHERE Appointment_ID = %s", appointmentId);
        System.out.println(deleteStatement);
        return deleteStatement;
//        dbQM.RunSQLString(deleteStatement);
    }

    public String createStatement_DeleteAppointmentByCustomerId(int customerId) {
        System.out.println("DAOAppointments - createStatement_DeleteAppointmentByCustomerId");
        String deleteStatement = String.format(
                "DELETE FROM appointments WHERE Customer_ID = %s", customerId);
        System.out.println(deleteStatement);
        return deleteStatement;
//        dbQM.RunSQLString(deleteStatement);
    }

    public Appointments selectExistingNearCustomerAppointments(int customerID, Appointment a) {
        LocalDateTime oneDayBefore = a.getStart().minusDays(1);
        LocalDateTime oneDayAfter = a.getEnd().plusDays(1);
        String sql = String.format(
                "SELECT * FROM appointments where Customer_ID = %s AND Start >= '%s' AND End <= '%s' ;",
                customerID, utils.Local_ToUTC(oneDayBefore), utils.Local_ToUTC(oneDayAfter));
        System.out.println(sql);
        Appointments nearAppointmentsForCustomer = selectAppointmentsFromSQLStatement(sql);
        return  nearAppointmentsForCustomer;
    }
}
