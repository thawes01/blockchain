package Blockchain.testUtils;

import Blockchain.BasicBlockData;
import Blockchain.StopClock;
import java.time.Clock;

public class BasicBlockDataCreator {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";
    private static final StopClock DUMMY_STOPCLOCK = new StopClock(Clock.systemUTC());

    public static BasicBlockData defaultBasicBlockData() {
        return new BasicBlockData(DUMMY_ID, DUMMY_HASH, DUMMY_STOPCLOCK);
    }

    public static BasicBlockData defaultBasicBlockData(int id) {
        return new BasicBlockData(id, DUMMY_HASH, DUMMY_STOPCLOCK);
    }

    public static BasicBlockData defaultBasicBlockData(String hash) {
        return new BasicBlockData(DUMMY_ID, hash, DUMMY_STOPCLOCK);
    }

    public static BasicBlockData defaultBasicBlockData(StopClock stopClock) {
        return new BasicBlockData(DUMMY_ID, DUMMY_HASH, stopClock);
    }
}
