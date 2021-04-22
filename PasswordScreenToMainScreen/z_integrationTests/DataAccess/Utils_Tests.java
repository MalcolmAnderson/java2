package DataAccess;

import main.Globals;
import org.junit.jupiter.api.*;

import utils.Utils;
import utils.dataAccess.DBConnection;

import static org.junit.jupiter.api.Assertions.*;

public class Utils_Tests {
    Utils utils;
    UtilsForTesting uft = new UtilsForTesting();

    @BeforeAll
    static void runOnceBeforeAllHaveStarted(){
        System.out.println("begin beforeAll");
        DBConnection.startConnection();
        System.out.println("end beforeAll");
    }
    @AfterAll
    static void runOnceAfterAllAreDone(){
        System.out.println("begin afterAll");
        DBConnection.endConnection();
        System.out.println("end afterAll");
    }

    @BeforeEach void setUp() {
        utils = new Utils();
        Globals.setUserId(-1);
        Globals.setUserName("userName not set");

    }

    @AfterEach void tearDown() {
    }

    @Test void shouldGetDefaultNextIdNumberFilePath(){
        assertEquals("./NextIdNumber.txt", utils.getNextIdNumberFilePath());
    }

    @Test void shouldCreateNewNextIdNumberFile(){
        // Setup
        String defaultNextIdNumberFilePath = utils.getNextIdNumberFilePath();
        assertEquals("./NextIdNumber.txt", defaultNextIdNumberFilePath);
        String testFilePath = "./testFile1";
        // delete test file if it exists
        uft.deleteFileIfItExists(testFilePath);
        // get next number
        utils.setNextIdNumberFilePath(testFilePath);
        assertEquals(testFilePath, utils.getNextIdNumberFilePath());


        int defaultIdNumber = utils.getNextIdNumber();
        assertEquals(101, defaultIdNumber);


        utils.setNextIdNumberFilePath(defaultNextIdNumberFilePath);
        assertEquals(defaultNextIdNumberFilePath, utils.getNextIdNumberFilePath());
    }


    @Test void commitShouldIncrementNumberInNextIdNumberFile(){
        // Setup
        String defaultNextIdNumberFilePath = utils.getNextIdNumberFilePath();
        assertEquals("./NextIdNumber.txt", defaultNextIdNumberFilePath);
        String testFilePath = "./testFile2";
        // delete test file if it exists
        uft.deleteFileIfItExists(testFilePath);
        // get next number
        utils.setNextIdNumberFilePath(testFilePath);
        assertEquals(testFilePath, utils.getNextIdNumberFilePath());


        int defaultIdNumber = utils.getNextIdNumber();
        assertEquals(101, defaultIdNumber);
        utils.commitNextIdNumber();
        defaultIdNumber = utils.getNextIdNumber();
        assertEquals(102, defaultIdNumber);



        utils.setNextIdNumberFilePath(defaultNextIdNumberFilePath);
        assertEquals(defaultNextIdNumberFilePath, utils.getNextIdNumberFilePath());
    }


    @Test void shouldFailNonUser(){
        boolean actual = utils.CheckPassword("foo", "bar");
        assertFalse(actual);
        assertEquals(-1, Globals.getUserId());
        assertEquals("userName not set", Globals.getUserName());
    }

    @Test void shouldFailTestUser(){
        boolean actual = utils.CheckPassword("test", "wrong password");
        assertFalse(actual);
        assertEquals(-1, Globals.getUserId());
        assertEquals("userName not set", Globals.getUserName());
    }

    @Test void shouldPassTestUser(){
        boolean actual = utils.CheckPassword("test", "test");
        assertTrue(actual);
        assertEquals(1, Globals.getUserId());
        assertEquals("test", Globals.getUserName());
    }

    @Test void shouldPassAdminUser(){
        boolean actual = utils.CheckPassword("admin", "admin");
        assertTrue(actual);
        assertEquals(2, Globals.getUserId());
        assertEquals("admin", Globals.getUserName());
    }

}
