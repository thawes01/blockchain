package Blockchain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

public class StopClockTest {
    private final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void nowReturnsNowTimeOfSuppliedClock(long creationTimeMillis) {
        Instant creationInstant = Instant.ofEpochMilli(creationTimeMillis);
        Clock clock = Clock.fixed(creationInstant, DEFAULT_ZONE);
        StopClock stopClock = new StopClock(clock);
        assertEquals(creationTimeMillis, stopClock.now());
    }
}
