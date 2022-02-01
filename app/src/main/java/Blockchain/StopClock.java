package Blockchain;

import java.time.Clock;

public class StopClock {
    private final Clock clock;

    public StopClock(Clock clock) {
        this.clock = clock;
    }

    public long now() {
        return clock.millis();
    }
}
