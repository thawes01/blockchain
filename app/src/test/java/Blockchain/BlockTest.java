package Blockchain;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    void getHashReturnsDefaultHash() {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData();
        Block block = new Block(primaryBlockData);
        String blockHash = block.getHash();
        assertEquals("0", blockHash);
    }
}
