package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TimeConversion;
import utils.Utils;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    Utils utils;
    private TimeConversion tc = new TimeConversion();
    LocalDateTime forcedValueForNow = LocalDateTime.of(1980, 1, 1, 0,0,0);

    @BeforeEach
    void setUp() {
        utils = new Utils();
        utils.setForcedNowValue(forcedValueForNow);
    }

    @AfterEach
    void tearDown() {
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

    @Test void utilsNowShouldReturnExpectedLDT(){
        assertEquals(forcedValueForNow.toString(), utils.now().toString());
    }

    @Test void shouldGetEasternForLastSundayAtMidnight(){
        // pacific time
        LocalDateTime now = LocalDateTime.of(2021,4,7, 0,0,0);
        LocalDate sunday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).toLocalDate();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime easternSundayMidnight = LocalDateTime.of(sunday, midnight);
        LocalDateTime easternSundayMidnight_UTC = tc.Eastern_ToUTC(easternSundayMidnight);
        LocalDateTime expected = LocalDateTime.of(2021, 4, 4, 4, 0, 0);
        assertEquals(expected, easternSundayMidnight_UTC);

    }

    @Test void shouldGetSundayOfWeekFromDate(){
        LocalDateTime now = LocalDateTime.of(2021, 4, 7,0,0,0);
        LocalDateTime expected = LocalDateTime.of(2021, 4, 4,4,0,0);
        LocalDateTime actual = tc.getLastSunday(now);
        assertEquals(expected, actual);
    }

    @Test void shouldGetSundayOfNextWeekFromDate(){
        LocalDate now = LocalDate.of(2021, 4, 7);
        LocalDate expected = LocalDate.of(2021, 4, 11);
        LocalDate actual = tc.getNextSunday(now);
        assertEquals(expected, actual);
    }

    @Test void shouldFailBecauseTooEarly(){
        LocalTime earlyLimit = Globals.earlyLimitEasternTime;
        LocalTime lateLimit = Globals.lateLimitEasternTime;

        // local pacific time of 4:59:59 is before 5am, which is 8am eastern
        LocalDateTime bad = LocalDateTime.of(2021, 4,15,4,59,59);
        boolean isValid = tc.doesTimeFallOutsideOfForbiddenTimes(bad, earlyLimit, lateLimit);
        assertFalse(isValid);
    }

    @Test void shouldFailBecauseTooLate(){
        LocalTime earlyLimit = Globals.earlyLimitEasternTime;
        LocalTime lateLimit = Globals.lateLimitEasternTime;

        // local pacific time of 4:59:59 is before 5am, which is 8am eastern
        LocalDateTime bad = LocalDateTime.of(2021, 4,15,19,0,1);
        boolean isValid = tc.doesTimeFallOutsideOfForbiddenTimes(bad, earlyLimit, lateLimit);
        assertFalse(isValid);
    }
}