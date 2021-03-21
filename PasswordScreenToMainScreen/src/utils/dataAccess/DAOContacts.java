package utils.dataAccess;

import models.Contact;
import models.Contacts;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOContacts {


    public static Contacts selectAllContacts(){
        Utils utils = new Utils();
        Contacts contacts = new Contacts();

        // TODO Replace all this appointment code with CONTACT code.

        try{
            String sql = "SELECT * FROM contacts;";

            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("       getting Column names for customer");
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
            System.out.println( throwables.getMessage());
            throwables.printStackTrace();
        }
        return contacts;
    }

    public ArrayList<Contact> getAllCustomers() {
        return new ArrayList<Contact>();
    }
}
