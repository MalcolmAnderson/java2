package tests_Integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DBConnection;
import utils.DBQueryManager;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ConnectionShouldExist(){
        assertNotNull(DBConnection.getConnection());
    }

    void UglyTestShouldNotFail(){
        // get current countries count
        DBQueryManager dbQM = new DBQueryManager();
        dbQM.RunSQLString("SELECT COUNT(*) FROM author;");
    }
}