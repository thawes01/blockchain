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
    private final String initialHash = "0";
    private final int firstBlockId = 0;
    private final BasicBlockData firstBlockData = new BasicBlockData(firstBlockId, initialHash);
    private final int generationTime = 12;

    @BeforeEach
    void setUpOutputStreams() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    void getPrintStreamReturnsExpectedPrintStream() {
        Printer printer = new Printer(printStream);
        assertEquals(printStream, printer.getPrintStream());
    }

    @AfterEach
    void tearDownPrintStream() {
        printStream.close();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            # Id, Creation Timestamp, Previous Block Hash, Generation time
            0,    10,                 a1,                  12
            1,    20,                 b2,                  13
            """)
    void printReportsSingleBlockchainEntryInExpectedFormat(int id, long creationTimestamp,
                                              String previousBlockHash,
                                              int generationTime) {
        StopClock stopClock = new StopClock(defaultFixedClock(creationTimestamp));
        BasicBlockData basicBlockData = new BasicBlockData(id, previousBlockHash);
        Block block = new Block(basicBlockData, stopClock);
        BlockchainEntry blockchainEntry = new BlockchainEntry(block, generationTime);
        Printer printer = new Printer(printStream);

        printer.print(blockchainEntry);

        String expectedContents = blockReport(block, generationTime);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    private String blockReport(Block block, int generationDuration) {
        return String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Magic number: %s\n", block.getMagicNumber()) +
                String.format("Hash of the previous block:\n%s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block:\n%s\n", block.computeHash()) +
                String.format("Block was generating for %d seconds\n", generationDuration);
    }

    @Test
    void printReportsBlockchainWithSingleBlockInExpectedFormat() {
        Block block = BlockCreator.withBasicBlockData(firstBlockData);
        BlockchainEntry blockchainEntry = new BlockchainEntry(block, generationTime);
        Blockchain blockchain = new Blockchain(initialHash);
        blockchain.push(blockchainEntry);
        Printer printer = new Printer(printStream);

        String expectedContents = "Block:\n" + blockReport(block, generationTime);
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    @Test
    void printWritesReportOfBlockchainWithMultipleBlocksInExpectedFormat() {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        BlockchainEntry blockchainEntry1 = new BlockchainEntry(block1, generationTime);

        Block block2 = BlockCreator.withCreationTimestamp(1L);
        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2, generationTime);

        Block block3 = BlockCreator.withCreationTimestamp(2L);
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, generationTime);

        Blockchain blockchain = new Blockchain(initialHash);
        blockchain.push(blockchainEntry1);
        blockchain.push(blockchainEntry2);
        blockchain.push(blockchainEntry3);
        Printer printer = new Printer(printStream);

        String expectedContents = blockchainReport(block1, block2, block3);
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    private String blockchainReport(Block... blocks) {
        String[] blockInformationStrings = new String[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            Block block = blocks[i];
            blockInformationStrings[i] = "Block:\n" +
                    blockReport(block, generationTime);
        }
        return String.join("\n", blockInformationStrings);
    }
}
