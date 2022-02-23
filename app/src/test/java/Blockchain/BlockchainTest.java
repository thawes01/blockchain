package Blockchain;

import Blockchain.testUtils.BlockCreator;
import Blockchain.testUtils.BasicBlockDataCreator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Iterator;

public class BlockchainTest {
    private Blockchain blockchain;
    private final String initialHash = "000";
    private final int firstBlockId = 0;
    private final BasicBlockData firstBlockData = new BasicBlockData(firstBlockId, initialHash);

    @BeforeEach
    void setUpBlockchain() {
        blockchain = new Blockchain(initialHash);
    }

    @Test
    void pushAddsBlockToBlockchain() {
        Block block = BlockCreator.withDefaultArgs();
        blockchain.push(block);

        assertEquals(block, blockchain.peek());
    }

    @Test
    void pushAddsBlockToTopOfChain() {
        Block block1 = BlockCreator.withDefaultArgs();
        Block block2 = BlockCreator.withBasicBlockData(BasicBlockDataCreator.withId(1));
        blockchain.push(block1);
        blockchain.push(block2);

        assertEquals(block2, blockchain.peek());
    }

    @Test
    @DisplayName("If first block in chain has correct value as previous hash and" +
            "successive block hashes agree then verify returns true")
    void verifyReturnsTrueIfAllConditionsSatisfied() {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
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
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertFalse(blockchain.validate());
    }

    @Test
    void verifyReturnsFalseIfThreeBlocksSuccessiveHashesDoNotAgree() {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);

        assertFalse(blockchain.validate());
    }

    @Test
    void iteratorYieldsBlocksFromBlockchainInOrder() {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);

        Iterator<Block> blockIterator = blockchain.iterator();

        assertEquals(block1, blockIterator.next());
        assertEquals(block2, blockIterator.next());
        assertEquals(block3, blockIterator.next());
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
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertEquals(block2.computeHash(), blockchain.getLastBlockHash());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getLastBlockIdReturnsLastBlockIdOfNonemptyBlockchain(int id) {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
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
