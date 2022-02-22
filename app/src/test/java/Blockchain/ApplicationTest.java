package Blockchain;

import static org.junit.jupiter.api.Assertions.*;
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
    @ValueSource(ints = {0, 1, 2})
    void startGeneratesBlockchainOfCorrectLength(int lengthOfBlockchain) {
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        application.start();

        Mockito.verify(blockchainGenerator).generate(lengthOfBlockchain);
    }

    @Test
    void startValidatesBlockchainPassesValidation() {
        application = new Application(configuration);

        application.start();

        Mockito.verify(blockchain).validate();
    }

    @Test
    void startThrowsRuntimeExceptionIfBlockchainIsInvalid() {
        Mockito.when(blockchain.validate()).thenReturn(false);
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        assertThrows(RuntimeException.class, application::start);
    }

    @Test
    void startPrintsWithPrinterFromConfiguration() {
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        application.start();

        Mockito.verify(printer).print(blockchain);
    }

}
