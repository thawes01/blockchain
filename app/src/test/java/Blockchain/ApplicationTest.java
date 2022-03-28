package Blockchain;

import static org.junit.jupiter.api.Assertions.*;

import Blockchain.io.Printer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class ApplicationTest {
    private static Application application;
    private static final int lengthOfBlockchain = 1;
    private static Printer printer;
    private static BlockchainGenerator blockchainGenerator;
    private static Blockchain blockchain;
    private static Configuration configuration;

    @BeforeEach
    void setUpMocksAndConfiguration() {
        blockchain = Mockito.mock(Blockchain.class);
        Mockito.when(blockchain.validate()).thenReturn(true);
        blockchainGenerator = Mockito.mock(BlockchainGenerator.class);
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        printer = Mockito.mock(Printer.class);

        configuration = new Configuration();
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        configuration.blockchainGenerator = blockchainGenerator;
        configuration.printer = printer;
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void applicationThrowsRuntimeExceptionIfLengthOfBlockchainNegative(int lengthOfBlockchain) {
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        assertThrows(RuntimeException.class, () -> new Application(configuration));
    }

    @Test
    void applicationThrowsRuntimeExceptionIfBlockchainGeneratorNull() {
        configuration.blockchainGenerator = null;
        assertThrows(RuntimeException.class, () -> new Application(configuration));
    }

    @Test
    void applicationThrowsRuntimeExceptionIfPrinterNull() {
        configuration.printer = null;
        assertThrows(RuntimeException.class, () -> new Application(configuration));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void runGeneratesBlockchainOfCorrectLength(int lengthOfBlockchain) {
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        application.run();

        Mockito.verify(blockchainGenerator).generate(lengthOfBlockchain);
    }

    @Test
    void runValidatesBlockchainPassesValidation() {
        application = new Application(configuration);

        application.run();

        Mockito.verify(blockchain).validate();
    }

    @Test
    void runThrowsRuntimeExceptionIfBlockchainIsInvalid() {
        Mockito.when(blockchain.validate()).thenReturn(false);
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        assertThrows(RuntimeException.class, application::run);
    }

    @Test
    void runPrintsWithPrinterFromConfiguration() {
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        application.run();

        Mockito.verify(printer).print(blockchain);
    }

}
