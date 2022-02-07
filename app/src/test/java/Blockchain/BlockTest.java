package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static Blockchain.testUtils.BasicBlockDataCreator.defaultBasicBlockData;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private static final int HASH_LENGTH = 64;

    @Test
    void getHashReturnsStringOfCorrectLength() {
        Block block = new Block(defaultBasicBlockData());
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
        Block block1 = new Block(defaultBasicBlockData());
        Block block2 = new Block(defaultBasicBlockData());
        assertEquals(block1.computeHash(), block2.computeHash());
    }

    @Test
    void getHashChangesWithDifferentID() {
        int id1 = 0, id2 = 1;
        BasicBlockData basicBlockData1 = defaultBasicBlockData(id1);
        BasicBlockData basicBlockData2 = defaultBasicBlockData(id2);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }

    @Test
    void getHashChangesWithDifferentPreviousBlockHash() {
        String previousBlockHash1 = "a1", previousBlockHash2 = "b2";
        BasicBlockData basicBlockData1 = defaultBasicBlockData(previousBlockHash1);
        BasicBlockData basicBlockData2 = defaultBasicBlockData(previousBlockHash2);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }

    @Test
    void getHashChangesWithDifferentTimestamp() {
        StopClock stopClock1 = new StopClock(defaultFixedClock(1L));
        StopClock stopClock2 = new StopClock(defaultFixedClock(2L));
        BasicBlockData basicBlockData1 = defaultBasicBlockData(stopClock1);
        BasicBlockData basicBlockData2 = defaultBasicBlockData(stopClock2);
        assertResultingBlockHashesNotEqual(basicBlockData1, basicBlockData2);
    }
}
