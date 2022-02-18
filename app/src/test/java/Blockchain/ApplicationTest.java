package Blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
    private static Application application;
    private static ByteArrayOutputStream outputStreamCaptor;
    private static PrintStream printStream;
    private static BlockchainGenerator blockchainGenerator;
    private static int lengthOfBlockchain;

    @BeforeEach
    void setUpApplicationElements() {
        outputStreamCaptor = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStreamCaptor);
        blockchainGenerator = new BlockchainGenerator();
        lengthOfBlockchain = 1;
    }

    @Test
    void startPrintsOutputToPrintStreamInConfiguration() {
        Configuration configuration = new Configuration();
        configuration.printStream = printStream;
        configuration.blockchainGenerator = blockchainGenerator;
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        application = new Application(configuration);

        application.start();

        String outputContent = outputStreamCaptor.toString();
        assertNotEquals("", outputContent);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void startGeneratesBlockchainOfCorrectLength(int lengthOfBlockchain) {
        Configuration configuration = new Configuration();
        BlockchainGenerator mockedBlockchainGenerator = Mockito.mock(BlockchainGenerator.class);
        configuration.printStream = printStream;
        configuration.blockchainGenerator = mockedBlockchainGenerator;
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        application = new Application(configuration);

        application.start();

        Mockito.verify(mockedBlockchainGenerator).generate(lengthOfBlockchain);
    }
}
