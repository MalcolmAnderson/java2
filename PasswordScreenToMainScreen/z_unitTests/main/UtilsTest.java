package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

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
        assertEquals("2020-03-14T13:56:27", dt.toString());
        assertEquals("2020-03-14", dt.toLocalDate().toString());
        assertEquals("13:56:27", dt.toLocalTime().toString());
        ZoneOffset tzo = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
        assertEquals("-07:00", tzo.toString());
        assertEquals("2020-03-14T13:56:27", dt.toString());
        //assertTrue(false);
    }
}