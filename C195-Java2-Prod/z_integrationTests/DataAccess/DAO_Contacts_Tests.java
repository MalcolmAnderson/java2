package DataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Contacts;
import models.Geography;
import org.junit.jupiter.api.*;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import models.Contact;
import utils.dataAccess.DBQueryManager;

import static org.junit.jupiter.api.Assertions.*;


public class DAO_Contacts_Tests {
    static DAOContacts dao;
    Contacts contacts;


    @BeforeAll
    public static void runBeforeAll(){
        DBConnection.startConnection();
        dao = new DAOContacts(new DBQueryManager());
        dao.selectAllContacts();
    }
    @AfterAll
    public static void runAfterAll(){
        DBConnection.endConnection();
    }


    @BeforeEach
    void setUp() {
        contacts = dao.getAllContacts();
    }

    @AfterEach
    void tearDown() {
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
        Contacts localContacts = new Contacts();
        assertEquals(0, localContacts.getContacts().size());
        // This line already accomplished during test setup
        //        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size(), "selectAllContacts Failed");
        ObservableList<Contact> olContacts = FXCollections.observableArrayList();
        assertEquals(0, olContacts.size());
        olContacts.setAll(contacts.getContacts());
        assertEquals(4, olContacts.size(), "olContacts failed to update");
    }

    @Test void shouldAddAndDeleteAContact(){
        // Setup

        // delete any old copies of test records
        String testName = "Test McTestFace1";
        int contact_ID = 51;
        dao.deleteContactByName(testName);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size());

        // Test
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact should have been added");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }

    @Test void shouldInsertUpdateAndDeleteAContact(){
        // Setup
        String testName = "Test McTestFace2";
        int contact_ID = 52;
        dao.deleteContactByID(contact_ID);
        dao.deleteContactByName(testName);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size());

        // Test
        // Create new Contact
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact not added");
        // Insert same record again, should fail due to unique id;
        assertTrue(dao.recordExists(current));
        dao.insertOrUpdateContact(current); // should not work, should update existing contact
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertTrue(dao.recordExists(current));
        assertEquals(5, contacts.getContacts().size(), "Contact added a second time");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }

    @Test void recordExistsShouldWork(){
        // Setup
        String testName = "Test McTestFace3";
        int contact_ID = 53;
        dao.deleteContactByName(testName);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size());

        // Test
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(5, contacts.getContacts().size());
        assertTrue(dao.recordExists(current));

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");

    }

    @Test void shouldBeAbleToUpdateRecords(){
        // Confirm old records are gone
        String testName = "Test McTestFace3";
        int contact_ID = 53;
        dao.deleteContactByID(contact_ID);
        dao.deleteContactByName(testName);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size());
        Contact current = new Contact(contact_ID, testName, "Test@Test.com");
        dao.insertOrUpdateContact(current);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Contact not added");

        // Test
        Contact subject = contacts.getContactById(contact_ID);
        // Setup
        assertEquals("Test McTestFace3", subject.contact_Name);
        String newName = "Where did my name go?";
        subject.contact_Name = newName;


        dao.insertOrUpdateContact(subject);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(5, contacts.getContacts().size(), "Should not have added another contact");
        subject = contacts.getContactById(contact_ID);
        assertEquals(newName, subject.contact_Name, "Should have updated name");

        // Teardown
        dao.deleteContactByID(current.contact_ID);
        dao.selectAllContacts();
        contacts = dao.getAllContacts();
        assertEquals(4, contacts.getContacts().size(), "Contact not deleted");
    }

    @Test void contactsShouldBeLoaded(){
        assertEquals(4, contacts.getContacts().size());
    }


}
