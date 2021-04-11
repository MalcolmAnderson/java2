package utils.dataAccess;

import models.Contact;
import models.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOContacts {

    private DBQueryManager dbQM = new DBQueryManager();
    private static Contacts contacts = new Contacts();

    public DAOContacts(DBQueryManager dbQM) {
        this.dbQM = dbQM;
        selectAllContacts();
    }

    public boolean recordExists(Contact current){
        ResultSet rs = null;
        try {
            String sql = String.format(
                    "SELECT * FROM contacts WHERE Contact_ID = '%s'", current.contact_ID);
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        int size = 0;
        if(rs != null){
            try {
                rs.last();
            } catch (SQLException throwables) {
                System.out.println("DAOContacts - recordExists - rs.last threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }
            try {
                size = rs.getRow();
            } catch (SQLException throwables) {
                System.out.println("DAOContacts - recordExists - rs.getRow threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }

        }
        System.out.println("DAOContacts - recordExists - size = " + size);
        return size > 0;
    }

    // dumps existing contacts list and refreshes from database
    // use the dao.getAllContacts to make changes
    public void selectAllContacts() {

        contacts = new Contacts();

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
            System.exit(-1);
        }
//        return contacts;
    }

    public Contacts getAllContacts() {
        return contacts;
    }

    public void insertOrUpdateContact(Contact current) {
        String sqlStatement = "";
        if(recordExists(current)){
            sqlStatement = String.format(
                    "UPDATE contacts"
                    + " SET Contact_ID = '%s', Contact_Name = '%s', Email = '%s'"
                    + " WHERE Contact_ID = %s;", current.contact_ID, current.contact_Name, current.email, current.contact_ID);
            System.out.println("DAOContacts - insertOrUpdateContact - Update Statement");
        } else {
            sqlStatement = String.format(
                    "INSERT INTO contacts (Contact_ID, Contact_Name, Email) "
                            + " VALUES ("
                            + "'%s', '%s', '%s');",
                    current.contact_ID, current.contact_Name, current.email);
            System.out.println("DAOContacts - insertOrUpdateContact - Insert Statement");
        }
        System.out.println(sqlStatement);
        dbQM.RunSQLString(sqlStatement);
    }
    public void deleteContactByID(int id) {
        String deleteStatement = String.format(
                "DELETE FROM contacts WHERE Contact_ID = '%s'", id);
        System.out.println("DAOContacts - deleteContactByID - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    public void deleteContactByName(String name) {
        String deleteStatement = String.format(
                "DELETE FROM contacts WHERE Contact_Name = '%s'", name);
        System.out.println("DAOContacts - deleteContactByName - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    public boolean ContactsHaveBeenLoaded() {
        return contacts.getContacts().size() > 0;
    }

    public Contact getContactByContactId(int contact_id) {
        if(contact_id > -1) {
            for (Contact contact : contacts.getContacts()) {
                if (contact.contact_ID == contact_id) {
                    return contact;
                }
            }
        }
        System.out.println("Contact_ID " + contact_id + " does not exist.");
        System.exit(-1);
        return null;
    }
}
