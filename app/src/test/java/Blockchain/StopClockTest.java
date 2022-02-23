package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

public class StopClockTest {

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void nowReturnsNowTimeOfSuppliedClock(long creationTimeMillis) {
        Clock clock = defaultFixedClock(creationTimeMillis);
        StopClock stopClock = new StopClock(clock);
        assertEquals(creationTimeMillis, stopClock.now());
    }

    @Test
    void systemUTCUsesClockSystemUTCMethod() {
        try (MockedStatic<Clock> mockedClock = Mockito.mockStatic(Clock.class)) {
            StopClock stopClock = StopClock.systemUTC();
            mockedClock.verify(Clock::systemUTC);
        }
    }
}
