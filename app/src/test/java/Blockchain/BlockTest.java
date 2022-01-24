package Blockchain;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    void getHashReturnsDefaultHash() {
        int blockId = 0;
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(blockId);
        Block block = new Block(primaryBlockData);
        String blockHash = block.getHash();
        assertEquals("0", blockHash);
    }
}
