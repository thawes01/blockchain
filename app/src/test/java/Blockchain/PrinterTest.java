package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static org.junit.jupiter.api.Assertions.*;
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

        String expectedContents = String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Hash of the previous block: %s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block: %s\n", block.computeHash());
        printer.print(block);
        String printContents = outputStream.toString();
        assertEquals(expectedContents, printContents);
    }
}
