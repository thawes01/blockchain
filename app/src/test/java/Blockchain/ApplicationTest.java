package Blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
    private static Application application;
    private static ByteArrayOutputStream outputStreamCaptor;
    private static PrintStream printStream;
    private static BlockchainGenerator blockchainGenerator;

    @BeforeEach
    void setUpApplication() {
        outputStreamCaptor = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStreamCaptor);
        blockchainGenerator = new BlockchainGenerator();
    }

    @Test
    void startPrintsOutputToSpecifiedPrintStream() {
        application = new Application(printStream, blockchainGenerator);
        application.start();
        String outputContent = outputStreamCaptor.toString();
        assertNotEquals("", outputContent);
    }

    @Test
    void startGeneratesBlockchain() {
        int blockchainLength = 1;
        BlockchainGenerator mockedBlockchainGenerator = Mockito.mock(BlockchainGenerator.class);
        application = new Application(printStream, mockedBlockchainGenerator);
        application.start();
        Mockito.verify(mockedBlockchainGenerator).generate(blockchainLength);
    }
}
