package Blockchain;

import static Blockchain.testUtils.Time.*;
import org.junit.jupiter.api.*;
import java.time.Clock;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";
    private static final StopClock DUMMY_STOPCLOCK = new StopClock(Clock.systemUTC());
    private static final int HASH_LENGTH = 64;

    @Test
    void getHashReturnsStringOfCorrectLength() {
        BasicBlockData basicBlockData = new BasicBlockData(DUMMY_ID, DUMMY_HASH, DUMMY_STOPCLOCK);
        Block block = new Block(basicBlockData);
        String blockHash = block.computeHash();
        assertEquals(HASH_LENGTH, blockHash.length());
    }

    private void assertResultingBlockHashesNotEqual(BasicBlockData basicBlockData1,
                                                    BasicBlockData basicBlockData2) {
        Block block1 = new Block(basicBlockData1);
        Block block2 = new Block(basicBlockData2);
        String blockHash1 = block1.computeHash();
        String blockHash2 = block2.computeHash();
        assertNotEquals(blockHash1, blockHash2);
    }

    @Test
    void equalBlocksGiveSameHash() {
        BasicBlockData basicBlockData1 = new BasicBlockData(DUMMY_ID, DUMMY_HASH, DUMMY_STOPCLOCK);
        BasicBlockData basicBlockData2 = new BasicBlockData(DUMMY_ID, DUMMY_HASH, DUMMY_STOPCLOCK);
        Block block1 = new Block(basicBlockData1);
        Block block2 = new Block(basicBlockData2);
        assertEquals(block1.computeHash(), block2.computeHash());
    }

    @Test
    void getHashChangesWithDifferentID() {
        int id1 = 0, id2 = 1;
        BasicBlockData basicBlockData1 = new BasicBlockData(id1, DUMMY_HASH, DUMMY_STOPCLOCK);
        BasicBlockData basicBlockData2 = new BasicBlockData(id2, DUMMY_HASH, DUMMY_STOPCLOCK);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }

    @Test
    void getHashChangesWithDifferentPreviousBlockHash() {
        String previousBlockHash1 = "a1", previousBlockHash2 = "b2";
        BasicBlockData basicBlockData1 = new BasicBlockData(DUMMY_ID, previousBlockHash1, DUMMY_STOPCLOCK);
        BasicBlockData basicBlockData2 = new BasicBlockData(DUMMY_ID, previousBlockHash2, DUMMY_STOPCLOCK);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }

    @Test
    void getHashChangesWithDifferentTimestamp() {
        StopClock stopClock1 = new StopClock(defaultFixedClock(1L));
        StopClock stopClock2 = new StopClock(defaultFixedClock(2L));
        BasicBlockData basicBlockData1 = new BasicBlockData(DUMMY_ID, DUMMY_HASH, stopClock1);
        BasicBlockData basicBlockData2 = new BasicBlockData(DUMMY_ID, DUMMY_HASH, stopClock2);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }
}
