package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static org.junit.jupiter.api.Assertions.*;

import Blockchain.testUtils.BlockCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUpOutputStreams() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @AfterEach
    void tearDownPrintStream() {
        printStream.close();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            # Id, Creation Timestamp, Previous Block Hash
            0,    10,                 a1
            1,    20,                 b2
            """)
    void printWritesBlockDataInExpectedFormat(int id, long creationTimestamp,
                                              String previousBlockHash) {
        StopClock stopClock = new StopClock(defaultFixedClock(creationTimestamp));
        BasicBlockData basicBlockData = new BasicBlockData(id, previousBlockHash);
        Block block = new Block(basicBlockData, stopClock);
        Printer printer = new Printer(printStream);

        String expectedContents = blockInformation(block.getId(),
                block.getCreationTimestamp(), block.getPreviousBlockHash(),
                block.computeHash());
        printer.print(block);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    private String blockInformation(int id, long creationTimestamp,
                                    String previousBlockHash, String blockHash) {
        return String.format("Id: %d\n", id) +
                String.format("Timestamp: %s\n", creationTimestamp) +
                String.format("Hash of the previous block: %s\n", previousBlockHash) +
                String.format("Hash of the block: %s\n", blockHash);
    }

    @Test
    void printWritesBlockchainWithSingleBlockInExpectedFormat() {
        Block block = BlockCreator.firstBlockInBlockchain();
        Blockchain blockchain = new Blockchain();
        blockchain.push(block);
        Printer printer = new Printer(printStream);

        String expectedContents = "Block:\n" + blockInformation(block.getId(),
                block.getCreationTimestamp(), block.getPreviousBlockHash(),
                block.computeHash());
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    @Test
    void printWritesBlockchainWithMultipleBlocksInExpectedFormat() {
        Block block1 = BlockCreator.firstBlockInBlockchain();
        Block block2 = BlockCreator.withCreationTimestamp(1L);
        Block block3 = BlockCreator.withCreationTimestamp(2L);
        Blockchain blockchain = new Blockchain();
        blockchain.push(block1);
        blockchain.push(block2);
        blockchain.push(block3);
        Printer printer = new Printer(printStream);

        String expectedContents = blockchainInformation(block1, block2, block3);
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    private String blockchainInformation(Block... blocks) {
        String[] blockInformationStrings = new String[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            Block block = blocks[i];
            blockInformationStrings[i] = "Block:\n" +
                    blockInformation(block.getId(), block.getCreationTimestamp(),
                            block.getPreviousBlockHash(), block.computeHash());
        }
        return String.join("\n", blockInformationStrings);
    }
}
