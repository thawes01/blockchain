package Blockchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ConfigurationTest {
    private Configuration configuration;

    @BeforeEach
    void setUpConfiguration() {
        configuration = new Configuration();
    }

    @Test
    void getSetPrintStream() {
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        configuration.setPrintStream(printStream);
        assertEquals(printStream, configuration.getPrintStream());
    }

    @Test
    void getSetBlockchainGenerator() {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator();
        configuration.setBlockchainGenerator(blockchainGenerator);
        assertEquals(blockchainGenerator, configuration.getBlockchainGenerator());
    }
}