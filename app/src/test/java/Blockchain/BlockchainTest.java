package Blockchain;

import Blockchain.core.BasicBlockData;
import Blockchain.core.Block;
import Blockchain.core.BlockchainEntry;
import Blockchain.exceptions.EmptyBlockchainException;
import Blockchain.testUtils.BlockCreator;
import Blockchain.testUtils.BasicBlockDataCreator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BlockchainTest {
    private Blockchain blockchain;
    private static final String initialHash = "000";
    private static final int firstBlockId = 0;
    private static final BasicBlockData firstBlockData = new BasicBlockData(firstBlockId, initialHash);
    private static Block block1;
    private static BlockchainEntry blockchainEntry1;
    private static Block block2;
    private static BlockchainEntry blockchainEntry2;
    private static Block block2WithCustomHash;
    private static BlockchainEntry blockchainEntry2WithCustomHash;

    @BeforeAll
    static void setUpStubs() {
        block1 = BlockCreator.withBasicBlockData(firstBlockData);
        blockchainEntry1 = new BlockchainEntry(block1, 21);

        block2 = BlockCreator.withBasicBlockData(BasicBlockDataCreator.withId(1));
        blockchainEntry2 = new BlockchainEntry(block2, 22);

        block2WithCustomHash = BlockCreator.withBasicBlockData(new BasicBlockData(1, "999"));
        blockchainEntry2WithCustomHash = new BlockchainEntry(block2WithCustomHash, 22);
    }

    @BeforeEach
    void setUpBlockchain() {
        blockchain = new Blockchain(initialHash);
    }

    @Test
    void getBlocksReturnsListOfBlocks() {
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);

        assertEquals(block1, blockchain.getBlocks().get(0));
        assertEquals(block2, blockchain.getBlocks().get(1));
    }

    @Test
    void getLastEntryThrowsEmptyBlockchainExceptionIfEmpty() {
        assertThrows(EmptyBlockchainException.class, () -> blockchain.getLastEntry());
    }

    @Test
    void addAddsBlockchainEntryToBlockchain() {
        blockchain.add(blockchainEntry1);

        assertEquals(blockchainEntry1, blockchain.getLastEntry());
    }

    @Test
    void addAddsBlockchainEntryToTopOfChain() {
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);

        assertEquals(blockchainEntry2, blockchain.getLastEntry());
    }

    @Test
    @DisplayName("If first block in chain has correct value as previous hash and" +
            "successive block hashes agree then verify returns true")
    void verifyReturnsTrueIfAllConditionsSatisfied() {
        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2, 12);

        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);

        assertTrue(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfFirstBlockDoesNotHaveCorrectPreviousHash() {
        BasicBlockData basicBlockData1 = new BasicBlockData(0, "F00");
        Block block1 = BlockCreator.withBasicBlockData(basicBlockData1);
        BlockchainEntry blockchainEntry1 = new BlockchainEntry(block1, 11);

        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2, 12);

        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, 12);

        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);
        blockchain.add(blockchainEntry3);

        assertFalse(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfSuccessiveHashesDoNotAgree() {
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2WithCustomHash);

        assertFalse(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfThreeBlocksSuccessiveHashesDoNotAgree() {
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2WithCustomHash.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, 13);

        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2WithCustomHash);
        blockchain.add(blockchainEntry3);

        assertFalse(blockchain.validate());
    }

    @Test
    void iteratorYieldsBlockchainEntriesFromBlockchainInOrder() {
        BlockchainEntry blockchainEntry1 = new BlockchainEntry(block1, 10);

        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2WithCustomHash, 11);

        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2WithCustomHash.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, 12);

        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);
        blockchain.add(blockchainEntry3);

        var blockchainIterator = blockchain.iterator();

        assertEquals(blockchainEntry1, blockchainIterator.next());
        assertEquals(blockchainEntry2, blockchainIterator.next());
        assertEquals(blockchainEntry3, blockchainIterator.next());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getLengthReturnsNumberOfBlocksInBlockchain(int numBlocks) {
        for (int i = 0; i < numBlocks; i++) {
            BlockchainEntry blockchainEntry = new BlockchainEntry(
                    BlockCreator.withBasicBlockData(firstBlockData), 12);
            blockchain.add(blockchainEntry);
        }

        assertEquals(numBlocks, blockchain.getLength());
    }

    @ParameterizedTest
    @ValueSource(strings = {"000", "F00"})
    void getLastBlockHashReturnsDefaultForEmptyBlockchain(String initialHash) {
        Blockchain blockchain = new Blockchain(initialHash);
        assertEquals(initialHash, blockchain.getLastBlockHash());
    }

    @Test
    void getLastBlockHashReturnsLastBlockHash() {
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2WithCustomHash);

        assertEquals(block2WithCustomHash.computeHash(), blockchain.getLastBlockHash());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getLastBlockIdReturnsLastBlockIdOfNonemptyBlockchain(int id) {
        BasicBlockData basicBlockData2 = new BasicBlockData(id, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2, 12);
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);

        assertEquals(block2.getId(), blockchain.getLastBlockId());
    }

    @Test
    void getLastBlockIdThrowsEmptyBlockchainExceptionForEmptyBlockchain() {
        assertThrows(EmptyBlockchainException.class, () -> blockchain.getLastBlockId());
    }
}
