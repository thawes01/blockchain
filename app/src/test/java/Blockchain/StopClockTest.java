package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

public class StopClockTest {
    private static final long pausedTimeMillis = 5;
    private StopClock stopClock;

    @BeforeEach
    void setUpSystemStopClock() {
        stopClock = StopClock.systemUTC();
    }

    /** Pauses execution for {@link StopClockTest#pausedTimeMillis} milliseconds. */
    private static void pause() {
        try {
            Thread.sleep(pausedTimeMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

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
            StopClock.systemUTC();
            mockedClock.verify(Clock::systemUTC);
        }
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void getStartTimeReturnsTimeWhenStopClockStarted(long startTimeMillis) {
        Clock clock = defaultFixedClock(startTimeMillis);
        StopClock stopClock = new StopClock(clock);

        stopClock.start();

        assertEquals(startTimeMillis, stopClock.getStartTime());
    }

    @Test
    void startTimeRemainsUnchangedIfStopClockNotStoppedBeforeStarting() {
        stopClock.start();
        long startTime = stopClock.getStartTime();
        pause();
        stopClock.start();
        assertEquals(startTime, stopClock.getStartTime());
    }

    @Test
    void startTimeChangesIfStopClockStartedAfterBeingStopped() {
        stopClock.start();
        long startTime = stopClock.getStartTime();
        pause();
        stopClock.stop();
        stopClock.start();
        assertTrue(startTime < stopClock.getStartTime());
    }

    @Test
    void getStartTimeReturnsNegativeIfStopClockNeverStarted() {
        assertTrue(stopClock.getStartTime() < 0);
    }

    @Test
    void getElapsedTimeReturnsDifferenceBetweenStartAndStopTime() {
        // Fixed time case
        Clock clock = defaultFixedClock(1);
        StopClock fixedStopClock = new StopClock(clock);

        fixedStopClock.start();
        fixedStopClock.stop();

        assertEquals(0, fixedStopClock.getElapsedTime());

        // Real time case
        stopClock.start();
        pause();
        stopClock.stop();

        assertTrue(stopClock.getElapsedTime() >= pausedTimeMillis);
    }

    @Test
    void getElapsedTimeDoesNotChangeUntilStopClockStartedAgain() {
        stopClock.start();
        pause();
        stopClock.stop();
        pause();
        stopClock.stop();

        assertTrue(stopClock.getElapsedTime() < 2 * pausedTimeMillis);
    }

    @Test
    void getElapsedTimeReturnsNegativeValueIfStopClockStartedButNotStopped() {
        // Fixed time case
        Clock clock = defaultFixedClock(0);
        StopClock fixedStopClock = new StopClock(clock);

        fixedStopClock.start();

        assertTrue(fixedStopClock.getElapsedTime() < 0);

        // Real time case
        stopClock.start();
        pause();

        assertTrue(stopClock.getElapsedTime() < 0);
    }
}
