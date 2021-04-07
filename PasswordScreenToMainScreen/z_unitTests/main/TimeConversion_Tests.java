package main;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TimeConversion;
import utils.Utils;

import java.text.DateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// TimeZone.getDefault().getDisplayName()
public class TimeConversion_Tests {

    Utils utils;
    TimeConversion tc;
    LocalDateTime startTime;

    @BeforeEach
    void setUp() {
        utils = new Utils();
        tc = new TimeConversion();
        startTime = LocalDateTime.of(2020, 8, 3, 11,0,0);

    }

    @AfterEach
    void tearDown() {
    }

//    @Disabled
//    @Test void shouldGetRightTimeZone() {
//        int actual = utils.add(5, 2);
//        String timezone = TimeZone.getDefault().getDisplayName();
//        assertEquals("Pacific Daylight Time", timezone);
//    }

//    @Disabled
//    @Test void ZonedDateTime_shouldHandleDayLightSavings(){
//        Locale locale = Locale.getDefault();
//        assertEquals("en_US", locale.toString());
//        TimeZone localTimeZone = TimeZone.getDefault();
//        assertEquals(true, localTimeZone.useDaylightTime());
//
//        //TimeZone localTimeZone = TimeZone.getTimeZone("Australia/Sydney");
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
//        assertEquals("foo", dateFormat.toString());
//
////            dateFormat.setTimeZone(localTimeZone);
////            Date rightNow = new Date();
////            System.out.println(locale.toString() + ": " + dateFormat.format(rightNow));
//    }

//    @Test void shouldGetRightTimeOffset(){
//        ZoneOffset tzo = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
//        assertEquals("-07:00", tzo.toString());
//
//        TimeZone offtime_zone = TimeZone.getDefault();
////        offtime_zone.getOffset()
//        Date dt = new Date();
//        boolean isInDayLightSavings = offtime_zone.inDaylightTime(dt);
//        assertEquals(true, isInDayLightSavings);
//    }

//    @Test void getLocalZone(){
//        String currentZoneName = ZoneId.systemDefault().toString();
//        assertEquals("America/Los_Angeles", currentZoneName);
//
//        ZoneOffset tzo = ZoneOffset.of("America/Los_Angeles");
//        assertEquals("-07:00", tzo.toString());
//
//        LocalDateTime pdx_localTime = LocalDateTime.of(2020, 02, 28, 14, 00, 00);
//
//        ZoneId utcZone = ZoneId.of("UTC");
//        ZoneId pdxZone = ZoneId.systemDefault();
//
//        ZonedDateTime pdxZoneDT = pdx_localTime.atZone(pdxZone);
//        assertEquals("-07:00", pdxZoneDT.getOffset().toString());
//        ZonedDateTime utcTime = pdxZoneDT.withZoneSameInstant(utcZone);
//        assertEquals("2020-02-28T21:00Z[UTC]", utcTime.toString());
//    }

//    @Test void playWithTimeZones(){
//        LocalDateTime expectedLDT = LocalDateTime.of(2021, 02, 28, 14, 00, 00);
//        LocalDateTime actualDT = LocalDateTime.of(2021, 02, 28, 21, 00, 00);
//
//
//        ZoneOffset tzo = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
//
//        ZonedDateTime zdt = expectedLDT.atZone(ZoneId.systemDefault());
//        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UTC"));
//
//        assertEquals(actualDT.toString(), utc.toLocalDateTime().toString());
//
//    }

//    @Test void sampleTimeStuff(){
//        LocalDate estDate = LocalDate.of(2020, 8, 3);
//        LocalTime estTime = LocalTime.of(11, 0); // Get from Combo Box
//        ZoneId estZoneId = ZoneId.of("America/New_York");
//        ZonedDateTime estZDT = ZonedDateTime.of(estDate, estTime, estZoneId);
//        assertEquals("2020-08-03T11:00", estZDT.toLocalDateTime().toString());// Creates ZonedDateTime object
//
//        // Convert EST to UTC
//        ZoneId utcZID = ZoneId.of("UTC");
//        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), utcZID);
//        System.out.println("EST - UTC Time: " + utcZDT);
//        assertEquals("2020-08-03T15:00", utcZDT.toLocalDateTime().toString());// Creates ZonedDateTime object
//
//        // Convert UTC to Local
//        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
//        ZonedDateTime localStartTime = ZonedDateTime.ofInstant(utcZDT.toInstant(),localZoneId);
//        System.out.println("UTC - Local Time: " + localStartTime);
//        assertEquals("2020-08-03T08:00", localStartTime.toLocalDateTime().toString());// Creates ZonedDateTime object
//    }

    @Test void localToEastern(){
//        LocalDateTime local = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getLocalZoneID(), tc.getEasternZoneID());
        assertEquals("2020-08-03T14:00", eastern.toString());
    }
    @Test void EasternToLocal(){
//        LocalDateTime local = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getEasternZoneID(), tc.getLocalZoneID());
        assertEquals("2020-08-03T08:00", eastern.toString());
    }

    @Test void localToUTC(){
//        LocalDateTime pacific = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getLocalZoneID(), tc.getUTCZoneID());
        assertEquals("2020-08-03T18:00", eastern.toString());
    }
    @Test void UTCTolocal(){
//        LocalDateTime pacific = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getUTCZoneID(), tc.getLocalZoneID());
        assertEquals("2020-08-03T04:00", eastern.toString());
    }

    @Test void EasternToUTC(){
//        LocalDateTime pacific = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getEasternZoneID(), tc.getUTCZoneID());
        assertEquals("2020-08-03T15:00", eastern.toString());
    }
    @Test void UTCToEastern(){
//        LocalDateTime pacific = LocalDateTime.of(2020, 8, 3, 11,0,0);
        LocalDateTime eastern = tc.Time_FromTo(startTime, tc.getUTCZoneID(), tc.getEasternZoneID());
        assertEquals("2020-08-03T07:00", eastern.toString());
    }

}
