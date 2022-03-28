package Blockchain.testUtils;

import Blockchain.core.*;
import Blockchain.utilities.StopClock;

public class BlockCreator {
    private static final BasicBlockData DUMMY_BASIC_DATA = BasicBlockDataCreator.withDefaultArgs();
    private static final StopClock DUMMY_STOPCLOCK = StopClock.systemUTC();

    public static Block withDefaultArgs() {
        return new Block(DUMMY_BASIC_DATA, DUMMY_STOPCLOCK);
    }

    public static Block withBasicBlockData(BasicBlockData basicBlockData) {
        return new Block(basicBlockData, DUMMY_STOPCLOCK);
    }

    /** Useful for creating multiple {@code Block}s in quick succession, which
     * can result in the same creation time being returned by the default
     * system clock. */
    public static Block withCreationTimestamp(long creationTimestamp) {
        StopClock stopClock = new StopClock(Time.defaultFixedClock(creationTimestamp));
        return new Block(DUMMY_BASIC_DATA, stopClock);
    }
}
