package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static Blockchain.testUtils.BasicBlockDataCreator.defaultBasicBlockData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private static final StopClock DUMMY_STOPCLOCK = StopClock.systemUTC();
    private static final int HASH_LENGTH = 64;

    @Test
    void getHashReturnsStringOfCorrectLength() {
        Block block = new Block(defaultBasicBlockData(), DUMMY_STOPCLOCK);
        String blockHash = block.computeHash();

        assertEquals(HASH_LENGTH, blockHash.length());
    }

    private void assertBlockHashesNotEqual(Block block1, Block block2) {
        String blockHash1 = block1.computeHash();
        String blockHash2 = block2.computeHash();

        assertNotEquals(blockHash1, blockHash2);
    }

    @Test
    void equalBlocksGiveSameHash() {
        Block block1 = new Block(defaultBasicBlockData(), DUMMY_STOPCLOCK);
        Block block2 = new Block(defaultBasicBlockData(), DUMMY_STOPCLOCK);

        assertEquals(block1.computeHash(), block2.computeHash());
    }

    @Test
    void getHashChangesWithDifferentID() {
        int id1 = 0, id2 = 1;
        Block block1 = new Block(defaultBasicBlockData(id1), DUMMY_STOPCLOCK);
        Block block2 = new Block(defaultBasicBlockData(id2), DUMMY_STOPCLOCK);

        assertBlockHashesNotEqual(block1, block2);
    }

    @Test
    void getHashChangesWithDifferentPreviousBlockHash() {
        String previousBlockHash1 = "a1", previousBlockHash2 = "b2";
        Block block1 = new Block(defaultBasicBlockData(previousBlockHash1),
                DUMMY_STOPCLOCK);
        Block block2 = new Block(defaultBasicBlockData(previousBlockHash2),
                DUMMY_STOPCLOCK);

        assertBlockHashesNotEqual(block1, block2);
    }

    @Test
    void getHashChangesWithDifferentTimestamp() {
        StopClock stopClock1 = new StopClock(defaultFixedClock(1L));
        StopClock stopClock2 = new StopClock(defaultFixedClock(2L));
        BasicBlockData basicBlockData = defaultBasicBlockData();
        Block block1 = new Block(basicBlockData, stopClock1);
        Block block2 = new Block(basicBlockData, stopClock2);

        assertBlockHashesNotEqual(block1, block2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void getIdReturnsId(int id) {
        Block block = new Block(defaultBasicBlockData(id), DUMMY_STOPCLOCK);

        assertEquals(id, block.getId());
    }

    @ParameterizedTest
    @ValueSource(longs = {10L, 20L})
    void getCreationTimestampReturnsMillisFromEpoch(long creationTimestamp) {
        StopClock stopClock = new StopClock(defaultFixedClock(creationTimestamp));
        Block block = new Block(defaultBasicBlockData(), stopClock);

        assertEquals(creationTimestamp, block.getCreationTimestamp());
    }

    @Test
    void creationTimestampRecordsCreationTime() {
        long creationTime = 1234L;
        StopClock mockedFixedStopClock = Mockito.mock(StopClock.class);
        Mockito.when(mockedFixedStopClock.now()).thenReturn(creationTime);
        BasicBlockData basicBlockData = defaultBasicBlockData();

        Mockito.verify(mockedFixedStopClock, Mockito.atMost(0)).now();
        Block block = new Block(basicBlockData, mockedFixedStopClock);
        Mockito.verify(mockedFixedStopClock).now();

        assertEquals(creationTime, block.getCreationTimestamp());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2"})
    void getPreviousBlockHashReturnsPreviousBlockHash(String previousBlockHash) {
        Block block = new Block(defaultBasicBlockData(previousBlockHash), DUMMY_STOPCLOCK);

        assertEquals(previousBlockHash, block.getPreviousBlockHash());
    }
}
