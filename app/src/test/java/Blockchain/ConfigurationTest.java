package Blockchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void fromDefaultSettingsInitialisesBlockchainGenerator() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertNotNull(configuration.blockchainGenerator);
    }

    @Test
    void fromDefaultSettingsInitialisesPrinter() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertEquals(Settings.PRINT_STREAM, configuration.printer.getPrintStream());
    }

    @Test
    void fromDefaultSettingsInitialisesLengthOfBlockchain() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertEquals(Settings.LENGTH_OF_BLOCKCHAIN, configuration.lengthOfBlockchain);
    }
}