package Blockchain.testUtils;

import java.time.*;

public class Time {
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    public static Clock defaultFixedClock(long millisFromEpoch) {
        Instant instant = Instant.ofEpochMilli(millisFromEpoch);
        return Clock.fixed(instant, DEFAULT_ZONE);
    }
}
