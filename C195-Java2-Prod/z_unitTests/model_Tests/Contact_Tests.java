package model_Tests;

import models.Contact;
import models.Contacts;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Contact_Tests {

    Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact();
    }

    @AfterEach
    void tearDown() {
    }

    @Test void shouldBeAbleToCheckOnNullValue(){
        assertEquals(-1, contact.contact_ID);
    }

    @Test void getContactByIdShouldWork() {
        // Setup
        String testName = "Contact McTestFace";
        String testEmail = "contact@test.com";
        int contact_ID = 90;
        Contacts contacts = _ManageTestData.BuildPlaceHolder_Contacts();
        assertEquals(5, contacts.getContacts().size(), "Test Contacts Not Created");
        Contact current = new Contact(contact_ID, testName, testEmail);
        contacts.addContact(current);
        assertEquals(6, contacts.getContacts().size(), "Contact not added");

        // Test
        Contact subject = contacts.getContactById(contact_ID);
        assertEquals(testName, subject.contact_Name);
        assertEquals(contact_ID, subject.contact_ID);
        assertEquals(testEmail, subject.email);
    }
}
