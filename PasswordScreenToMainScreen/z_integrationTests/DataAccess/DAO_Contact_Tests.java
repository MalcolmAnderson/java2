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

import static org.junit.jupiter.api.Assertions.assertEquals;


import models.Contact;

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

        System.out.println("sql statement");
        System.out.println(sql);
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            System.out.println("       getting Column names for contacts");
            int size = rsmd.getColumnCount();
            System.out.println("ResultSet column count: " + size);
            // Note: columns is apparently 1 based
            for (int i = 1; i <= size; i++) {
                System.out.println(rsmd.getColumnName(i));
            }
        }
    }

    @Test
    void shouldRefreshContacts() {
        Contacts contacts = new Contacts();
        assertEquals(0, contacts.getContacts().size());
        contacts = dao.selectAllContacts();
        assertEquals(3, contacts.getContacts().size());
        ObservableList<Contact> olContacts = FXCollections.observableArrayList();
        assertEquals(0, olContacts.size());
        olContacts.setAll(contacts.getContacts());
        assertEquals(3, olContacts.size());
    }

}
