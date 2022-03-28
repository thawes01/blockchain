package Blockchain.ioTest;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static org.junit.jupiter.api.Assertions.*;

import Blockchain.*;
import Blockchain.core.BasicBlockData;
import Blockchain.core.Block;
import Blockchain.core.BlockchainEntry;
import Blockchain.io.Printer;
import Blockchain.testUtils.BlockCreator;
import Blockchain.utilities.StopClock;
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
            1,    20,                 b2,                  999
            """)
    void printReportsSingleBlockchainEntryInExpectedFormat(int id, long creationTimestamp,
                                              String previousBlockHash,
                                              long generationTime) {
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

    private String blockReport(Block block, long generationTime) {
        double generationTimeSeconds = (double) generationTime / 1000;
        return String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Magic number: %s\n", block.getMagicNumber()) +
                String.format("Hash of the previous block:\n%s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block:\n%s\n", block.computeHash()) +
                String.format("Block was generating for %.0f seconds\n", generationTimeSeconds);
    }

    @Test
    void printReportsBlockchainWithSingleBlockInExpectedFormat() {
        Block block = BlockCreator.withBasicBlockData(firstBlockData);
        long generationTime = 1500;
        BlockchainEntry blockchainEntry = new BlockchainEntry(block, generationTime);
        Blockchain blockchain = new Blockchain(initialHash);
        blockchain.add(blockchainEntry);
        Printer printer = new Printer(printStream);

        String expectedContents = "Block:\n" + blockReport(block, generationTime);
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    @Test
    void printWritesReportOfBlockchainWithMultipleBlocksInExpectedFormat() {
        Block block1 = BlockCreator.withBasicBlockData(firstBlockData);
        int generationTime1 = 1000;
        BlockchainEntry blockchainEntry1 = new BlockchainEntry(block1, generationTime1);

        Block block2 = BlockCreator.withCreationTimestamp(1L);
        int generationTime2 = 1499;
        BlockchainEntry blockchainEntry2 = new BlockchainEntry(block2, generationTime2);

        Block block3 = BlockCreator.withCreationTimestamp(2L);
        int generationTime3 = 2001;
        BlockchainEntry blockchainEntry3 = new BlockchainEntry(block3, generationTime3);

        Blockchain blockchain = new Blockchain(initialHash);
        blockchain.add(blockchainEntry1);
        blockchain.add(blockchainEntry2);
        blockchain.add(blockchainEntry3);
        Printer printer = new Printer(printStream);

        String expectedContents = blockchainReport(
                blockchainEntry1, blockchainEntry2, blockchainEntry3);
        printer.print(blockchain);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }

    private String blockchainReport(BlockchainEntry... blockchainEntries) {
        String[] blockInformationStrings = new String[blockchainEntries.length];
        for (int i = 0; i < blockchainEntries.length; i++) {
            BlockchainEntry entry = blockchainEntries[i];
            blockInformationStrings[i] = "Block:\n" +
                    blockReport(entry.block(), entry.generationTime());
        }
        return String.join("\n", blockInformationStrings);
    }
}
