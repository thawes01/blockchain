package Blockchain;

public class Application {
    private final int lengthOfBlockchain;
    private final BlockchainGenerator blockchainGenerator;
    private final Printer printer;

    private static void validateBlockchain(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException("Blockchain generated not valid, there is a problem with blockchain generation");
        }
    }

    private static int getLengthOfBlockchainIfNotNegative(Configuration configuration) {
        if (configuration.lengthOfBlockchain < 0) {
            throw new RuntimeException("Length of blockchain negative, must be non-negative");
        }
        return configuration.lengthOfBlockchain;
    }

    private static BlockchainGenerator getBlockchainGeneratorIfNotNull(Configuration configuration) {
        if (configuration.blockchainGenerator == null) {
            throw new RuntimeException("Blockchain generator is null, must be non-null");
        }
        return configuration.blockchainGenerator;
    }

    private static Printer getPrinterIfNotNull(Configuration configuration) {
        if (configuration.printer == null) {
            throw new RuntimeException("Printer is null, must be non-null");
        }
        return configuration.printer;
    }

    /**
     * Allocates an {@code Application} for running a Blockchain application.
     *
     * @param configuration  the configuration to run the application with
     */
    public Application(Configuration configuration) {
        this.lengthOfBlockchain = getLengthOfBlockchainIfNotNegative(configuration);
        this.blockchainGenerator = getBlockchainGeneratorIfNotNull(configuration);
        this.printer = getPrinterIfNotNull(configuration);
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
