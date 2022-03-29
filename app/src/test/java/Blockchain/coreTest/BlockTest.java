package Blockchain.coreTest;

import Blockchain.core.*;
import static Blockchain.testUtils.Time.defaultFixedClock;
import Blockchain.testUtils.BasicBlockDataCreator;
import Blockchain.utilities.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private static final StopClock DUMMY_STOPCLOCK = new StopClock(defaultFixedClock(999));
    private static final long DUMMY_TIMESTAMP = 1234L;
    private static final int HASH_LENGTH = 64;

    @Test
    void getHashReturnsStringOfCorrectLength() {
        Block block = new Block(BasicBlockDataCreator.withDefaultArgs(), DUMMY_STOPCLOCK);
        String blockHash = block.computeHash();

        assertEquals(HASH_LENGTH, blockHash.length());
    }

    @Test
    void getHashReturnsHashRepresentationOfBlock() {
        Block block = new Block(BasicBlockDataCreator.withDefaultArgs(), DUMMY_STOPCLOCK);
        String expectedHash = StringUtil.applySha256(stringRepresentation(block));
        String blockHash = block.computeHash();

        assertEquals(expectedHash, blockHash);
    }

    private String stringRepresentation(Block block) {
        return String.format("%s%s%s%s", block.getId(),
                block.getCreationTimestamp(), block.getMagicNumber(),
                block.getPreviousBlockHash());
    }

    private void assertBlockHashesNotEqual(Block block1, Block block2) {
        String blockHash1 = block1.computeHash();
        String blockHash2 = block2.computeHash();

        assertNotEquals(blockHash1, blockHash2);
    }

    @Test
    void equalBlocksGiveSameHash() {
        Block block1 = new Block(BasicBlockDataCreator.withDefaultArgs(), DUMMY_STOPCLOCK);
        Block block2 = new Block(BasicBlockDataCreator.withDefaultArgs(), DUMMY_STOPCLOCK);

        assertEquals(block1.computeHash(), block2.computeHash());
    }

    @Test
    void getHashChangesWithDifferentID() {
        int id1 = 0, id2 = 1;
        Block block1 = new Block(BasicBlockDataCreator.withId(id1), DUMMY_STOPCLOCK);
        Block block2 = new Block(BasicBlockDataCreator.withId(id2), DUMMY_STOPCLOCK);

        assertBlockHashesNotEqual(block1, block2);
    }

    @Test
    void getHashChangesWithDifferentPreviousBlockHash() {
        String previousBlockHash1 = "a1", previousBlockHash2 = "b2";
        Block block1 = new Block(BasicBlockDataCreator.withPreviousBlockHash(previousBlockHash1),
                DUMMY_STOPCLOCK);
        Block block2 = new Block(BasicBlockDataCreator.withPreviousBlockHash(previousBlockHash2),
                DUMMY_STOPCLOCK);

        assertBlockHashesNotEqual(block1, block2);
    }

    @Test
    void getHashChangesWithDifferentTimestamp() {
        BasicBlockData basicBlockData = BasicBlockDataCreator.withDefaultArgs();
        Block block1 = new Block(basicBlockData, 1L);
        Block block2 = new Block(basicBlockData, 2L);

        assertBlockHashesNotEqual(block1, block2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void getIdReturnsId(int id) {
        Block block = new Block(BasicBlockDataCreator.withId(id), DUMMY_STOPCLOCK);

        assertEquals(id, block.getId());
    }

    @ParameterizedTest
    @ValueSource(longs = {10L, 20L})
    void getCreationTimestampReturnsGivenCreationTime(long creationTimestamp) {
        Block block = new Block(BasicBlockDataCreator.withDefaultArgs(), creationTimestamp);

        assertEquals(creationTimestamp, block.getCreationTimestamp());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b2"})
    void getPreviousBlockHashReturnsPreviousBlockHash(String previousBlockHash) {
        Block block = new Block(BasicBlockDataCreator.withPreviousBlockHash(previousBlockHash), DUMMY_STOPCLOCK);

        assertEquals(previousBlockHash, block.getPreviousBlockHash());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getHashStartsWithGivenNumberOfZerosWhenMagicNumberFound(int numberOfZeros) {
        Block block = new Block(BasicBlockDataCreator.withId(0), DUMMY_TIMESTAMP);

        block.findMagicNumber(numberOfZeros);

        assertStartsWithZeros(block.computeHash(), numberOfZeros);
    }

    private void assertStartsWithZeros(String hash, int numberOfZeros) {
        String startString = "0".repeat(numberOfZeros);
        assertTrue(hash.startsWith(startString));
    }

    @Test
    void getMagicNumberReturnsNegativeOneIfMagicNumberNotFound() {
        Block block = new Block(BasicBlockDataCreator.withId(0), DUMMY_TIMESTAMP);

        assertEquals(-1, block.getMagicNumber());
    }
}
