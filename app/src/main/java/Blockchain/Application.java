package Blockchain;

import Blockchain.core.*;
import Blockchain.io.Printer;

/**
 * Represents instances of an application to be run based on a
 * configuration.
 */
public class Application {
    private final Configuration configuration;

    private static void validateConfiguration(Configuration configuration) {
        checkLengthOfBlockchainNonNegative(configuration);
        checkBlockchainGeneratorNotNull(configuration);
        checkPrinterNotNull(configuration);
    }

    private static void checkLengthOfBlockchainNonNegative(Configuration configuration) {
        if (configuration.lengthOfBlockchain < 0) {
            throw new RuntimeException("Length of blockchain negative, must be non-negative");
        }
    }

    private static void checkBlockchainGeneratorNotNull(Configuration configuration) {
        if (configuration.blockchainGenerator == null) {
            throw new RuntimeException("Blockchain generator is null, must be non-null");
        }
    }

    private static void checkPrinterNotNull(Configuration configuration) {
        if (configuration.printer == null) {
            throw new RuntimeException("Printer is null, must be non-null");
        }
    }

    private static void validateBlockchain(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException("Blockchain generated not valid, there is a problem with blockchain generation");
        }
    }

    /**
     * Allocates an {@code Application} for running a Blockchain application.
     *
     * @param configuration  the configuration to run the application with
     */
    public Application(Configuration configuration) {
        validateConfiguration(configuration);
        this.configuration = configuration;
    }

    /**
     * Runs the application.
     */
    public void run() {
        Blockchain blockchain = generateBlockchain();
        validateBlockchain(blockchain);
        printBlockchain(blockchain);
    }

    private Blockchain generateBlockchain() {
        BlockchainGenerator blockchainGenerator = configuration.blockchainGenerator;
        int lengthOfBlockchain = configuration.lengthOfBlockchain;
        return blockchainGenerator.generate(lengthOfBlockchain);
    }

    private void printBlockchain(Blockchain blockchain) {
        Printer printer = configuration.printer;
        printer.print(blockchain);
    }
}
