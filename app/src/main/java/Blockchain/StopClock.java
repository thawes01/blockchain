package Blockchain;

import java.time.Clock;

public class StopClock {
    private final Clock clock;
    private long startTime;
    private long stopTime;
    private boolean timerRunning = false;

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
        this.startTime = -1;
        this.stopTime = -1;
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

    /**
     * Starts the stop clock timer.
     *
     * Once started, the timer cannot be restarted until it has been stopped.
     */
    public void start() {
        if (!timerRunning) {
            startTime = now();
            timerRunning = true;
        }
    }

    /**
     * Gets the time the stop clock timer was started.
     *
     * The start time is represented as a number of milliseconds from the
     * start of the epoch, as returned by {@link Clock#millis()} for the
     * {@code Clock} supplied at instantiation this object.
     *
     * Returns a negative value if the stop clock timer has never been started.
     *
     * @return  the time the stop clock timer was started
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Stops the stop clock timer.
     */
    public void stop() {
        if (timerRunning) {
            stopTime = now();
            timerRunning = false;
        }
    }

    /**
     * Get the time that elapsed between starting and stopping the stop clock's
     * timer.
     *
     * Returns a time duration in milliseconds. This should be called starting
     * and stopping the timer using {@link StopClock#start()} and
     * {@link StopClock#stop()}. If called before stopping the timer, then
     * returns a negative value (or zero if the timer was never started).
     *
     * @return  the number of milliseconds that elapsed between starting and
     *          stopping the timer
     */
    public long getElapsedTime() {
        return stopTime - startTime;
    }
}
