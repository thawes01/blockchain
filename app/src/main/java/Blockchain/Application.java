package Blockchain;

public class Application {
    private final int lengthOfBlockchain;
    private final BlockchainGenerator blockchainGenerator;
    private final Printer printer;

    private static void validateConfiguration(Configuration configuration) {
        validateLengthOfBlockchain(configuration);
        validateBlockchainGenerator(configuration);
        validatePrinter(configuration);
    }

    private static void validateBlockchain(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException("Blockchain generated not valid, there is a problem with blockchain generation");
        }
    }

    private static void validateLengthOfBlockchain(Configuration configuration) {
        if (configuration.lengthOfBlockchain < 0) {
            throw new RuntimeException("Length of blockchain negative, must be non-negative");
        }
    }

    private static void validateBlockchainGenerator(Configuration configuration) {
        if (configuration.blockchainGenerator == null) {
            throw new RuntimeException("Blockchain generator is null, must be non-null");
        }
    }

    private static void validatePrinter(Configuration configuration) {
        if (configuration.printer == null) {
            throw new RuntimeException("Printer is null, must be non-null");
        }
    }

    /**
     * Allocates an {@code Application} for running a Blockchain application.
     *
     * @param configuration  the configuration to run the application with
     */
    public Application(Configuration configuration) {
        validateConfiguration(configuration);
        this.lengthOfBlockchain = configuration.lengthOfBlockchain;
        this.blockchainGenerator = configuration.blockchainGenerator;
        this.printer = configuration.printer;
    }

    /**
     * Runs the application.
     */
    public void run() {
        Blockchain blockchain = blockchainGenerator.generate(lengthOfBlockchain);
        validateBlockchain(blockchain);
        printer.print(blockchain);
    }
}
