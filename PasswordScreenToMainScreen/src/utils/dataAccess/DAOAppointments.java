package utils.dataAccess;

import models.Appointment;
import models.Appointments;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOAppointments {
    public Appointments selectAllAppointments(){
        Appointments appointments = new Appointments();
        Utils utils = new Utils();
        try{
            String sql = "SELECT * FROM appointments;";

            String shouldWork = "SELECT Appointment_Id, Title, Description, Location, "
                + "contacts.Contact_Name, Type, Start, End, customers.Customer_ID FROM appointments "
                + "left join customers on "
                + "appointments.Customer_ID = customers.Customer_ID "
                + "left join contacts on "
                + "appointments.Contact_ID = contacts.Contact_ID;";
            sql = shouldWork;
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentId = rs.getInt("Appointment_Id");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDateTime start = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Start"));
                LocalDateTime end = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("End"));
                int customerId = rs.getInt("Customer_ID");
                appointments.addAppointment(new Appointment(appointmentId, title,description, location, contactName, type, start, end, customerId));
//                System.out.println(appointmentId + ", " + title + ", " + description + ", " + customerId  + ", " + contactName);
//                System.out.println(appointments.getAllAppointments().size());
            }

        } catch (SQLException throwables) {
            System.out.println( throwables.getMessage());
            throwables.printStackTrace();
        }
        return appointments;
    }
}
