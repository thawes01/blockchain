package Blockchain.coreTest;

import Blockchain.core.*;
import Blockchain.utilities.StopClock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class BlockchainGeneratorTest {
    BlockchainGenerator blockchainGenerator;

    @BeforeEach
    void setUpBlockchainGenerator() {
        blockchainGenerator = new BlockchainGenerator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getProofOfWorkNumberReturnsProofOfWorkNumber(int proofOfWorkNumber) {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator(proofOfWorkNumber);

        Assertions.assertEquals(proofOfWorkNumber, blockchainGenerator.getProofOfWorkNumber());
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
        assertTrue(blockchain.validate());
    }

    @Test
    void generateReturnsBlockchainWithSequentialBlockIds() {
        int numBlocks = 5;
        Blockchain blockchain = blockchainGenerator.generate(numBlocks);

        int id = 0;
        for (var block : blockchain.getBlocks()) {
            assertEquals(id, block.getId());
            id++;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void generateProducesBlocksWithProvenHashes(int proofOfWorkNumber) {
        int numBlocks = 1;
        String zeros = "0".repeat(proofOfWorkNumber);
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator(proofOfWorkNumber);

        Blockchain blockchain = blockchainGenerator.generate(numBlocks);

        assertTrue(blockchain.getLastBlockHash().startsWith(zeros));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void generateProducesBlockchainWithRecordsOfBlockGenerationTime(int blockchainLength) {
        StopClock stopClock = Mockito.mock(StopClock.class);
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator(2, stopClock);

        blockchainGenerator.generate(blockchainLength);

        // Check stop clock used as expected for each block in the chain
        Mockito.verify(stopClock, Mockito.times(blockchainLength)).start();
        Mockito.verify(stopClock, Mockito.times(blockchainLength)).getStartTime();
        Mockito.verify(stopClock, Mockito.times(blockchainLength)).stop();
        Mockito.verify(stopClock, Mockito.times(blockchainLength)).getElapsedTime();
    }
}
