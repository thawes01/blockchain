package Blockchain;

import Blockchain.testUtils.BlockCreator;
import Blockchain.testUtils.BasicBlockDataCreator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BlockchainTest {
    private Blockchain blockchain;
    private final String initialHash = "000";
    private final int firstBlockId = 0;
    private final BasicBlockData firstBlockData = new BasicBlockData(firstBlockId, initialHash);
    private final Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
    private final Block block2 = BlockCreator.withBasicBlockData(BasicBlockDataCreator.withId(1));
    private final Block block2dummyHash = BlockCreator.withBasicBlockData(new BasicBlockData(1, "999"));

    @BeforeEach
    void setUpBlockchain() {
        blockchain = new Blockchain(initialHash);
    }

    @Test
    void getBlocksReturnsListOfBlocks() {
        blockchain.push(block1);
        blockchain.push(block2);

        assertEquals(block1, blockchain.getBlocks().get(0));
        assertEquals(block2, blockchain.getBlocks().get(1));
    }

    @Test
    void pushAddsBlockToBlockchain() {
        blockchain.push(block1);

        assertEquals(block1, blockchain.peek());
    }

    @Test
    void pushAddsBlockToTopOfChain() {
        blockchain.push(block1);
        blockchain.push(block2);

        assertEquals(block2, blockchain.peek());
    }

    @Test
    @DisplayName("If first block in chain has correct value as previous hash and" +
            "successive block hashes agree then verify returns true")
    void verifyReturnsTrueIfAllConditionsSatisfied() {
        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertTrue(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfFirstBlockDoesNotHaveCorrectPreviousHash() {
        BasicBlockData basicBlockData1 = new BasicBlockData(0, "F00");
        Block block1 = BlockCreator.withBasicBlockData(basicBlockData1);
        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);

        assertFalse(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfSuccessiveHashesDoNotAgree() {
        blockchain.push(block1);
        blockchain.push(block2dummyHash);

        assertFalse(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfThreeBlocksSuccessiveHashesDoNotAgree() {
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2dummyHash.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2dummyHash);
        blockchain.push(block3);

        assertFalse(blockchain.validate());
    }

    @Test
    void iteratorYieldsBlockchainEntriesFromBlockchainInOrder() {
        BlockchainEntry blockchainEntry1 = new BlockchainEntry(block1, 10);

        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2dummyHash, 11);

        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2dummyHash.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, 12);

        blockchain.push(blockchainEntry1);
        blockchain.push(blockchainEntry2);
        blockchain.push(blockchainEntry3);

        var blockchainIterator = blockchain.iterator();

        assertEquals(blockchainEntry1, blockchainIterator.next());
        assertEquals(blockchainEntry2, blockchainIterator.next());
        assertEquals(blockchainEntry3, blockchainIterator.next());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getLengthReturnsNumberOfBlocksInBlockchain(int numBlocks) {
        for (int i = 0; i < numBlocks; i++) {
            blockchain.push(BlockCreator.withBasicBlockData(firstBlockData));
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
        blockchain.push(block1);
        blockchain.push(block2dummyHash);

        assertEquals(block2dummyHash.computeHash(), blockchain.getLastBlockHash());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getLastBlockIdReturnsLastBlockIdOfNonemptyBlockchain(int id) {
        BasicBlockData basicBlockData2 = new BasicBlockData(id, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertEquals(block2.getId(), blockchain.getLastBlockId());
    }

    @Test
    void getLastBlockIdThrowsRuntimeExceptionForEmptyBlockchain() {
        assertThrows(EmptyBlockchainException.class, () -> blockchain.getLastBlockId());
    }
}
