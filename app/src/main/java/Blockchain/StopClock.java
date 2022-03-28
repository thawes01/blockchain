package Blockchain;

import java.time.Clock;

public class StopClock {
    private final Clock clock;

    /**
     * Returns a {@code StopClock} based on the system's clock.
     *
     * Uses the UTC timezone. A wrapper around {@link Clock#systemUTC()}.
     *
     * @return  a {@code StopClock} based on the system's clock, in the UTC
     *          timezone
     */
    public static StopClock systemUTC() {
        return new StopClock(Clock.systemUTC());
    }

    /**
     * Allocates a {@code StopClock} object for timing computations.
     *
     * A wrapper around {@link Clock}.
     *
     * @param clock  a clock to use as the basis for timing
     */
    public StopClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * Returns the current time.
     *
     * The current time is represented as a number of milliseconds from the
     * start of the epoch, as returned by {@link Clock#millis()} for the
     * {@code Clock} supplied at instantiation this object.
     *
     * @return  the current number of milliseconds from the start of the epoch
     */
    public long now() {
        return clock.millis();
    }

    public void start() {
    }

    public void stop() {
    }

    public int getElapsedTime() {
        return 12345;
    }
}
