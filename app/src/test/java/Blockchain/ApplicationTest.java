package Blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        blockchainGenerator = Mockito.mock(BlockchainGenerator.class);
        printer = Mockito.mock(Printer.class);

        configuration = new Configuration();
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        configuration.blockchainGenerator = blockchainGenerator;
        configuration.printer = printer;
    }

    @Test
    void startPrintsWithPrinterFromConfiguration() {
        Mockito.when(blockchainGenerator.generate(lengthOfBlockchain)).thenReturn(blockchain);
        application = new Application(configuration);

        application.start();

        Mockito.verify(printer).print(blockchain);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void startGeneratesBlockchainOfCorrectLength(int lengthOfBlockchain) {
        configuration.lengthOfBlockchain = lengthOfBlockchain;
        application = new Application(configuration);

        application.start();

        Mockito.verify(blockchainGenerator).generate(lengthOfBlockchain);
    }
}
