package Blockchain;

import org.junit.jupiter.api.*;
import java.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";
    private static final StopClock DUMMY_STOPCLOCK = new StopClock(Clock.systemUTC());

    @Test
    void getHashReturnsDefaultHash() {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(DUMMY_ID, DUMMY_HASH, DUMMY_STOPCLOCK);
        Block block = new Block(primaryBlockData);
        String blockHash = block.getHash();
        assertEquals("0", blockHash);
    }
}
