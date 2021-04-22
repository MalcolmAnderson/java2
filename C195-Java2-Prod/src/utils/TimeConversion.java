package utils;

import main.Globals;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * Time Conversion tools to manage the 3 important timezone, Local, Eastern US and UTC
 */
public class TimeConversion {

    /**
     * Returns the ZoneId of the local system
     * @return - Zone ID
     */
    public ZoneId getLocalZoneID() {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        return localZoneId;
    }

    /**
     * Returns the Zone Id of the Eastern USA
     * @return - Zone Id
     */
    public ZoneId getEasternZoneID() {
        ZoneId estZoneId = ZoneId.of("America/New_York");
        return estZoneId;
    }

    /**
     * Returns the Zone Id of UTC
     * @return
     */
    public ZoneId getUTCZoneID() {
        ZoneId utcZoneId = ZoneId.of("UTC");
        return utcZoneId;
    }

    /**
     * Converts a LocalDateTime from one time zone to another
     * @param ldtToConvert - Date Time to be converted
     * @param from - Zone Id of the date time to be converted
     * @param to - Target Zone id
     * @return Local Date Time - Date Time Converted to target Zone
     */
    public LocalDateTime Time_FromTo(LocalDateTime ldtToConvert, ZoneId from, ZoneId to){
        ZonedDateTime zdtFrom = ZonedDateTime.of(ldtToConvert, from);
        ZonedDateTime zdtTo = ZonedDateTime.ofInstant(zdtFrom.toInstant(), to);
        return zdtTo.toLocalDateTime();
    }

    /**
     * Converts a local time to eastern time
     * @param ldt - local time to be converted
     * @return - converted time
     */
    public LocalDateTime Local_ToEastern(LocalDateTime ldt) {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId estZoneId = ZoneId.of("America/New_York");

        ZonedDateTime zdtLocal = ZonedDateTime.of(ldt, localZoneId);
        ZonedDateTime zdtEastern = ZonedDateTime.ofInstant(zdtLocal.toInstant(), estZoneId);
        return zdtEastern.toLocalDateTime();
    }

    /**
     * Converts an eastern time to local time
     * @param eastern - eastern time to be converted
     * @return - converted time
     */
    public LocalDateTime Eastern_ToLocal(LocalDateTime eastern){
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId estZoneId = ZoneId.of("America/New_York");

        ZonedDateTime zdtUTC = ZonedDateTime.of(eastern, estZoneId);
        ZonedDateTime zdtLocal = ZonedDateTime.ofInstant(zdtUTC.toInstant(), localZoneId);
        return zdtLocal.toLocalDateTime();
    }

    /**
     * Converts an local time to utc time
     * @param ldt - local time to be converted
     * @return - converted time
     */
    public LocalDateTime Local_ToUTC(LocalDateTime ldt) {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime zdtLocal = ZonedDateTime.of(ldt, localZoneId);
        ZonedDateTime zdtUTC = ZonedDateTime.ofInstant(zdtLocal.toInstant(), utcZoneId);
        return zdtUTC.toLocalDateTime();
    }

    /**
     * Converts a UTC time to local time
     * @param utc - utc time to be converted
     * @return - converted time
     */
    public LocalDateTime UTC_ToLocal(LocalDateTime utc){
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime zdtUTC = ZonedDateTime.of(utc, utcZoneId);
        ZonedDateTime zdtLocal = ZonedDateTime.ofInstant(zdtUTC.toInstant(), localZoneId);
        return zdtLocal.toLocalDateTime();
    }


    /**
     * Converts an eastern time to utc time
     * @param eastern - eastern time to be converted
     * @return - converted time
     */
    public LocalDateTime Eastern_ToUTC(LocalDateTime eastern) {
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZoneId estZoneId = ZoneId.of("America/New_York");

        ZonedDateTime zdtEastern = ZonedDateTime.of(eastern, estZoneId);
        ZonedDateTime zdtUDT = ZonedDateTime.ofInstant(zdtEastern.toInstant(), utcZoneId);
        return zdtUDT.toLocalDateTime();
    }

    /**
     * From a Local Date Time, find the first of the month for that month
     * Converts to Eastern time as that is the zone of policy
     * @param now - input time
     * @return - Eastern time zone for first of the month
     */
    public LocalDateTime getFirstOfTheMonth(LocalDateTime now) {
        // set to eastern midnight
        LocalDate sunday = now.withDayOfMonth(1).toLocalDate();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime easternSundayMidnight = LocalDateTime.of(sunday, midnight);
        LocalDateTime easternSundayMidnight_UTC = Eastern_ToUTC(easternSundayMidnight);
        return easternSundayMidnight_UTC;
    }

    /**
     * From a Local Date Time, find the first of the next month for that date
     * Converts to Eastern time as that is the zone of policy.
     * Because of time math, the first of the next month is effectively the last of this month
     * @param now - input time
     * @return - Eastern time zone for first of next month
     */
    public LocalDateTime getFirstOfNextMonth(LocalDateTime now) {
        // set to eastern midnight
        LocalDate sunday = now.with(TemporalAdjusters.firstDayOfNextMonth()).toLocalDate();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime easternSundayMidnight = LocalDateTime.of(sunday, midnight);
        LocalDateTime easternSundayMidnight_UTC = Eastern_ToUTC(easternSundayMidnight);
        return easternSundayMidnight_UTC;
    }


    /**
     * From a Local Date Time, find midnight of the most recent Sunday
     * Converts to Eastern time as that is the zone of policy.
     * @param now - input time
     * @return - Eastern time zone for midnight last Sunday
     */
    public LocalDateTime getLastSunday(LocalDateTime now) {
        // set to eastern midnight
        LocalDate sunday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).toLocalDate();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime easternSundayMidnight = LocalDateTime.of(sunday, midnight);
        LocalDateTime easternSundayMidnight_UTC = Eastern_ToUTC(easternSundayMidnight);
        return easternSundayMidnight_UTC;
    }

    /**
     * From a Local Date Time, find midnight of the next Sunday
     * Converts to Eastern time as that is the zone of policy.
     * Because of time math, the next Sunday is effectively the last of this week
     * @param now - input time
     * @return - Eastern time zone for midnight next Sunday
     */
    public LocalDateTime getNextSunday(LocalDateTime now) {
        // set to eastern midnight
        LocalDate sunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).toLocalDate();
        LocalTime midnight = LocalTime.of(0, 0);
        LocalDateTime easternSundayMidnight = LocalDateTime.of(sunday, midnight);
        LocalDateTime easternSundayMidnight_UTC = Eastern_ToUTC(easternSundayMidnight);
        return easternSundayMidnight_UTC;
    }

    /**
     * Gets the date for next Sunday
     * @param now - input time
     * @return - Date of next Sunday
     */
    public LocalDate getNextSunday(LocalDate now) {
        return now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }

    /**
     * Checks a local time against the early and late limit
     * @param ldt - time to check
     * @param earlyLimit - should be 8am Eastern
     * @param lateLimit - should be 10pm Eastern
     * @return boolean - true if time falls within the acceptable hours
     */
    public boolean doesTimeFallOutsideOfForbiddenTimes(LocalDateTime ldt, LocalTime earlyLimit, LocalTime lateLimit) {
        // Business Hours by Policy is between 8am and 10pm Eastern
        ldt = Local_ToEastern(ldt);
        LocalTime lt = ldt.toLocalTime();
        if(lt.isBefore(earlyLimit) || lt.isAfter(lateLimit)){
            return false;
        }
        return true;
    }

    public boolean isAppointmentWithinPolicy(LocalDateTime start, LocalDateTime end) {
        boolean retVal = true;
        boolean startIsGood;
        boolean endIsGood;
        LocalTime earlyLimit = Globals.earlyLimitEasternTime;
        LocalTime lateLimit = Globals.lateLimitEasternTime;
        startIsGood = doesTimeFallOutsideOfForbiddenTimes(start, earlyLimit, lateLimit);  // seems to be having an issue
        endIsGood = doesTimeFallOutsideOfForbiddenTimes(end, earlyLimit, lateLimit);
        boolean validAppointment = true;
        if(startIsGood && endIsGood){
            LocalDateTime runner = start;
            while(runner.isBefore(end)){
                runner = runner.plusMinutes(15);
                if( ! doesTimeFallOutsideOfForbiddenTimes(runner, earlyLimit, lateLimit)){
                    validAppointment = false;
                    break;
                }
            }
        }
        retVal = startIsGood && endIsGood && validAppointment;
        return retVal;
    }

// Sample code from Malcolm W. (WGU) as a starter point for making my timezone logic work
//
//        ZonedDateTime localStartTime = ZonedDateTime.ofInstant(utcZDT.toInstant(),localZoneId);
//
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

}
