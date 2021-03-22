package utils.dataAccess;

import models.Appointment;
import models.Contact;
import models.Contacts;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOContacts {

    private DBQueryManager dbQM = new DBQueryManager();

    public Contacts selectAllContacts() {
        Utils utils = new Utils();
        Contacts contacts = new Contacts();

        // TODO Replace all this appointment code with CONTACT code.

        try {
            String sql = "SELECT * FROM contacts;";

            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contact_ID = rs.getInt("Contact_ID");
                String contact_Name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact current = new Contact(
                        contact_ID,
                        contact_Name,
                        email);
                contacts.addContact(current);
            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
        return contacts;
    }

    public ArrayList<Contact> getAllCustomers() {
        return new ArrayList<Contact>();
    }

    public void insertContact(Contact current) {
        String insertStatement = String.format(
                "INSERT INTO contacts (Contact_ID, Contact_Name, Email) "
                        + " VALUES ("
                        + "'%s', '%s', '%s');",
                current.contact_ID, current.contact_Name, current.email);
        System.out.println("Insert Statement");
        System.out.println(insertStatement);
        dbQM.RunSQLString(insertStatement);
    }
    public void deleteContactByID(int id) {
        String deleteStatement = String.format(
                "DELETE FROM contacts WHERE Contact_ID = '%s'", id);
        System.out.println("Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

}
