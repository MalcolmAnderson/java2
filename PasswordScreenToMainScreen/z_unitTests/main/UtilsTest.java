package z_unitTests.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    Utils utils;

    @BeforeEach
    void setUp() {
        utils = new Utils();
    }

    @AfterEach
    void tearDown() {
    }

    @Test void add() {
        int actual = utils.add(5, 2);
        assertEquals(7, actual);
    }

    @Test void shouldConvertLocalDateTimeToMySQLInputString(){
        LocalDateTime dt = LocalDateTime.of(2020, 3, 14, 13, 56, 27);
        assertTrue(false);
    }
}