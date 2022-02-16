package Blockchain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class BlockchainGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void generateReturnsBlockchainWithCorrectNumberOfBlocks(int numBlocks) {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator();
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);
        assertEquals(numBlocks, blockchain.getLength());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void generateReturnsValidBlockchain(int numBlocks) {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator();
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);
        assertTrue(blockchain.verify());
    }
}
