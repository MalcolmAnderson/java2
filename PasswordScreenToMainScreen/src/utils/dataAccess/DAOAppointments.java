package utils.dataAccess;

import models.Appointment;
import models.Appointments;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;

public class DAOAppointments {

    private DBQueryManager dbQM = new DBQueryManager();

    public Appointments selectAllAppointments(){
        Appointments appointments = new Appointments();
        try{
            String sql = "SELECT * FROM appointments;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appointment current = CreateAppointmentFromResultSetRow(rs);
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
        Utils utils = new Utils();
        int appointmentId = rs.getInt("Appointment_Id");
        String title = rs.getString("Title");
        String description = rs.getString("Description");
        String location = rs.getString("Location");
        String type = rs.getString("Type");
        LocalDateTime start = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Start"));
        LocalDateTime end = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("End"));
        int customerId = rs.getInt("Customer_ID");
        int contactId = rs.getInt("Contact_ID");
        Appointment current = new Appointment(appointmentId, title, description, location, type, start, end, customerId, contactId);
        return current;
    }

    public void insertAppointment(Appointment current) {
        String insertStatement = String.format(
                "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, "
                        + "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, "
                        + "Customer_ID, User_ID, Contact_ID) "
                        + " VALUES ("
                        + "'%s', '%s', '%s', '%s','%s', "
                        + "'%s', '%s', '%s','%s', '%s', "
                        + "'%s', '%s, '%s', '%s');",
                current.getId(), current.getTitle(), current.getDescription(),
                current.getLocation(), current.getType(), current.getStart(), current.getEnd(),
                LocalDateTime.now(), "Test", LocalDateTime.now(), "Test", current.getCustomer_Id(),
                1, 4);
        System.out.println("Insert Statement");
        System.out.println(insertStatement);
        dbQM.RunSQLString(insertStatement);
    }

    public void deleteAppointmentsByCustomerId(int customer_id) {
        String deleteStatement = String.format(
                "DELETE FROM appointments WHERE Customer_ID = '%s'", customer_id);
        System.out.println("DAOAppointments - deleteAppointmentsByCustomerId - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    public String createStatement_InsertAppointment(Appointment a) {
        return "not implemented";
    }

    public String createStatement_UpdateAppointment(Appointment a) {
        return "not implemented";
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

    public boolean AppointmentIdExists(int i) {
        return false;
    }
}
