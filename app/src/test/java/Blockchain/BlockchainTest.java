package Blockchain;

import Blockchain.testUtils.BlockCreator;
import Blockchain.testUtils.BasicBlockDataCreator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BlockchainTest {

    @Test
    void pushAddsBlockToBlockchain() {
        Block block = BlockCreator.withDefaultArgs();
        Blockchain blockchain = new Blockchain();
        blockchain.push(block);
        assertEquals(block, blockchain.peek());
    }

    @Test
    void pushAddsBlockToTopOfChain() {
        Block block1 = BlockCreator.withDefaultArgs();
        Block block2 = BlockCreator.withBasicBlockData(BasicBlockDataCreator.withId(1));
        Blockchain blockchain = new Blockchain();
        blockchain.push(block1);
        blockchain.push(block2);
        assertEquals(block2, blockchain.peek());
    }
}
