package Blockchain;

import java.io.PrintStream;

public class App {
    private static Configuration configuration;
    private static BlockchainGenerator blockchainGenerator;

    /**
     * Generates a blockchain, validates it and prints the contents of it.
     *
     * @param args  command line arguments (not used)
     */
    public static void main(String[] args) {
        configureApp();
        run(configuration, blockchainGenerator);
    }

    private static void configureApp() {
        configuration = new Configuration();
        blockchainGenerator = new BlockchainGenerator();
    }

    static void run(Configuration configuration,
                    BlockchainGenerator blockchainGenerator) {
        Blockchain blockchain = blockchainGenerator.generate(configuration.getNumberOfBlocks());
        validateBlockchain(blockchain);
        printBlockchainInformation(blockchain, configuration.getPrintStream());
    }

    private static void printBlockchainInformation(Blockchain blockchain, PrintStream printStream) {
        Printer printer = new Printer(printStream);
        printer.print(blockchain);
    }

    private static void validateBlockchain(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException();
        }
    }
}
