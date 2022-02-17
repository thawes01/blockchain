package Blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class BlockchainGeneratorTest {
    BlockchainGenerator blockchainGenerator;

    @BeforeEach
    void setUpBlockchainGenerator() {
        blockchainGenerator = new BlockchainGenerator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void generateReturnsBlockchainWithCorrectNumberOfBlocks(int numBlocks) {
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);
        assertEquals(numBlocks, blockchain.getLength());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void generateReturnsValidBlockchain(int numBlocks) {
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);
        assertTrue(blockchain.verify());
    }

    @Test
    void generateReturnsBlockchainWithSequentialBlockIds() {
        int numBlocks = 5;
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);

        int id = 0;
        for (var block : blockchain) {
            assertEquals(id, block.getId());
            id++;
        }
    }
}
