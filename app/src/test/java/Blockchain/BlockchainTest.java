package Blockchain;

import Blockchain.testUtils.BlockCreator;
import Blockchain.testUtils.BasicBlockDataCreator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Iterator;

public class BlockchainTest {
    private Blockchain blockchain;

    @BeforeEach
    void setUpBlockchain() {
        blockchain = new Blockchain();
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
    @DisplayName("If first block in chain has 0 as previous hash and successive" +
            "block hashes agree then verify returns true")
    void verifyReturnsTrueIfAllConditionsSatisfied() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertTrue(blockchain.verify());
    }

    @Test
    void verifyReturnsFalseIfFirstBlockDoesNotHave0PreviousHash() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
        BasicBlockData basicBlockData2 = new BasicBlockData(1, block1.computeHash());
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);

        assertTrue(blockchain.verify());
    }

    @Test
    void verifyReturnsFalseIfSuccessiveHashesDoNotAgree() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        blockchain.push(block1);
        blockchain.push(block2);

        assertFalse(blockchain.verify());
    }

    @Test
    void verifyReturnsFalseIfThreeBlocksSuccessiveHashesDoNotAgree() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
        BasicBlockData basicBlockData2 = new BasicBlockData(1, "999");
        Block block2 = BlockCreator.withBasicBlockData(basicBlockData2);
        BasicBlockData basicBlockData3 = new BasicBlockData(2, block2.computeHash());
        Block block3 = BlockCreator.withBasicBlockData(basicBlockData3);
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);

        assertFalse(blockchain.verify());
    }

    @Test
    void iteratorYieldsBlocksFromBlockchainInOrder() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
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
}
