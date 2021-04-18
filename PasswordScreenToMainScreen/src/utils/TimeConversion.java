package utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeConversion {

    public ZoneId getLocalZoneID() {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        return localZoneId;
    }

    public ZoneId getEasternZoneID() {
        ZoneId estZoneId = ZoneId.of("America/New_York");
        return estZoneId;
    }

    public ZoneId getUTCZoneID() {
        ZoneId utcZoneId = ZoneId.of("UTC");
        return utcZoneId;
    }

    public LocalDateTime Time_FromTo(LocalDateTime ldtToConvert, ZoneId from, ZoneId to){
        ZonedDateTime zdtFrom = ZonedDateTime.of(ldtToConvert, from);
        ZonedDateTime zdtTo = ZonedDateTime.ofInstant(zdtFrom.toInstant(), to);
        return zdtTo.toLocalDateTime();
    }
}
