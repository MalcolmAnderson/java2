package DataAccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import utils.Utils;

public class Utils_Tests {

    @BeforeEach void setUp() {
    }

    @AfterEach void tearDown() {
    }

    @Test void shouldGetDefaultNextIdNumberFilePath(){
        Utils utils = new Utils();
        assertEquals("./NextIdNumber.txt", utils.getNextIdNumberFilePath());
    }

    @Test void shouldCreateNewNextIdNumberFile(){
        // Setup
        Utils utils = new Utils();
        String defaultNextIdNumberFilePath = utils.getNextIdNumberFilePath();
        assertEquals("./NextIdNumber.txt", defaultNextIdNumberFilePath);
        String testFilePath = "./testFile1";
        // delete test file if it exists
        utils.deleteFileIfItExists(testFilePath);
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
        Utils utils = new Utils();
        String defaultNextIdNumberFilePath = utils.getNextIdNumberFilePath();
        assertEquals("./NextIdNumber.txt", defaultNextIdNumberFilePath);
        String testFilePath = "./testFile2";
        // delete test file if it exists
        utils.deleteFileIfItExists(testFilePath);
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


}
