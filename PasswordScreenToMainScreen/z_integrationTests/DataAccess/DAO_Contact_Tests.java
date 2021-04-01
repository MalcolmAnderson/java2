package DataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Contacts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import models.Contact;

import static org.junit.jupiter.api.Assertions.*;

//import java.sql.PreparedStatement;
//import java.sql.*;


public class DAO_Contact_Tests {
    DAOContacts dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOContacts();
    }

    @AfterEach
    void tearDown() {
        DBConnection.endConnection();
    }

    @Test void shouldGetContacts() throws SQLException {
        String sql = "SELECT * FROM contacts limit 1;";

        System.out.println("DAO_Contact_Tests - shouldGetContacts - sql statement");
        System.out.println(sql);
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            System.out.println("DAO_Contact_Tests - shouldGetContacts - getting Column names for contacts");
            int size = rsmd.getColumnCount();
            System.out.println("DAO_Contact_Tests - shouldGetContacts - ResultSet column count: " + size);
            // Note: columns is apparently 1 based
            for (int i = 1; i <= size; i++) {
                System.out.println(rsmd.getColumnName(i));
            }
        }
    }

    @Test void shouldRefreshContacts() {
        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size(), "selectAllContacts Failed");
        ObservableList<Contact> olContacts = FXCollections.observableArrayList();
        assertEquals(0, olContacts.size());
        olContacts.setAll(contacts.getContacts());
        assertEquals(4, olContacts.size(), "olContacts failed to update");
    }

    @Test void shouldAddAndDeleteAContact(){
        // Setup
        String testName = "Test McTestFace1";
        int contact_ID = 51;
        dao.deleteContactByName(testName);
        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size());
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");

        // Test
        dao.insertOrUpdateContact(current);
        contacts = dao.selectAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact should have been added");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }

    @Test void shouldInsertUpdateAndDeleteAContact(){
        // Setup
        String testName = "Test McTestFace2";
        int contact_ID = 52;
        dao.deleteContactByID(contact_ID);
        dao.deleteContactByName(testName);
        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size());

        // Test
        // Create new Contact
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        contacts = dao.selectAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact not added");
        // Insert same record again, should fail due to unique id;
        assertTrue(dao.recordExists(current));
        dao.insertOrUpdateContact(current); // should not work, should update existing contact
        assertTrue(dao.recordExists(current));
        assertEquals(5, contacts.getContacts().size(), "Contact added a second time");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }

    @Test void recordExistsShouldWork(){
        // Setup
        String testName = "Test McTestFace3";
        int contact_ID = 53;
        dao.deleteContactByName(testName);
        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size());

        // Test
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        assertEquals(4, contacts.getContacts().size());
        assertTrue(dao.recordExists(current));

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");

    }

    @Test void shouldBeAbleToUpdateRecords(){
        // Confirm old records are gone
        String testName = "Test McTestFace3";
        int contact_ID = 53;
        dao.deleteContactByID(contact_ID);
        dao.deleteContactByName(testName);

        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size());
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        contacts = dao.selectAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact not added");

        // Test
        Contact subject = contacts.getContactById(contact_ID);
        // Setup
        assertEquals("Test McTestFace3", subject.contact_Name);
        String newName = "Where did my name go?";
        subject.contact_Name = newName;


        dao.insertOrUpdateContact(subject);
        contacts = dao.selectAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Should not have added another contact");
        subject = contacts.getContactById(contact_ID);
        assertEquals(newName, subject.contact_Name, "Should have updated name");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        contacts = dao.selectAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }


}
