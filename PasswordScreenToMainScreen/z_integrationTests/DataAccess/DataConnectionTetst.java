package DataAccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DataConnectionTetst {

    @BeforeEach void setUp() {
    }

    @AfterEach void tearDown() {
    }

    @Test void GetConnectionShouldReopenConnection() throws SQLException {
        Connection conn = DBConnection.startConnection();
        conn.close();
        conn = DBConnection.getConnection();
        DBConnection.endConnection();
    }
    @Test void StartConnectionShouldWork(){
        Connection conn = DBConnection.startConnection();
        DBConnection.endConnection();
    }


}
