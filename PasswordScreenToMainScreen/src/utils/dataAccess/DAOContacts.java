package utils.dataAccess;

import models.Contact;
import models.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO Object to manage contacts
 */
public class DAOContacts {

    private DBQueryManager dbQM = new DBQueryManager();
    private Contacts contacts = new Contacts();

    /**
     * Sets the DBQueryManager for collection
     * @param dbQM - Input dbQM used for project
     */
    public DAOContacts(DBQueryManager dbQM) {
        this.dbQM = dbQM;
        selectAllContacts();
    }

    /**
     * Contact check to confirm is a specific contact exists in the database.
     * Key value is the Contact ID
     * @param current - Contact object to be searched for.
     * @return boolean - returns true if an object with the same contact ID of current exists in the database
     */
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

    /**
     * Gets all contacts from the database, populates Contacts object in DAO
     */
    public void selectAllContacts() {

        contacts = new Contacts();
        String sql = "SELECT * FROM contacts;";
        System.out.println(sql);

        try {
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
                System.out.println(current.contact_ID + " - " + current.toString());
                contacts.addContact(current);
            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
//        return contacts;
    }

    /**
     * Returns the Contacts collection in the DAO
     * @return Contacts - The Contacts object for the DAO
     */
    public Contacts getAllContacts() {
        return contacts;
    }

    /**
     * Examines current for Id, and creates an update string if found, and an insert statement if not.
     * statement is then passed to the dbQM to be run.
     * @param current - Object to either be inserted or updated
     */
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

    /**
     * Deletes a record based on the id.  Does not check for existence first.
     * @param id - ID of Contact to search for
     */
    public void deleteContactByID(int id) {
        String deleteStatement = String.format(
                "DELETE FROM contacts WHERE Contact_ID = '%s'", id);
        System.out.println("DAOContacts - deleteContactByID - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    /**
     * Delete contact by name
     * @param name - Name to search and delete by
     */
    public void deleteContactByName(String name) {
        String deleteStatement = String.format(
                "DELETE FROM contacts WHERE Contact_Name = '%s'", name);
        System.out.println("DAOContacts - deleteContactByName - Delete Statement");
        System.out.println(deleteStatement);
        dbQM.RunSQLString(deleteStatement);
    }

    /**
     * Checks the size of the Contacts object and returns true if there are 1 or more records
     * @return boolean - true or false - have contacts been loaded.
     */
    public boolean ContactsHaveBeenLoaded() {
        return contacts.getContacts().size() > 0;
    }

    /**
     * Get contact object based on Contact ID
     * @param contact_id - value to search for
     * @return Contact - If contact is not found, application will shut down.
     */
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
