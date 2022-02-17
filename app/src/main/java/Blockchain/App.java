package Blockchain;

public class App {
    private static Configuration configuration;
    private static BlockchainGenerator blockchainGenerator;
    private static Printer printer;

    /**
     * Generates a blockchain, validates it and prints the contents of it.
     *
     * @param args  command line arguments (not used)
     */
    public static void main(String[] args) {
        configureApp();
        run(configuration, blockchainGenerator, printer);
    }

    private static void configureApp() {
        configuration = new Configuration();
        printer = new Printer(configuration.getPrintStream());
        blockchainGenerator = new BlockchainGenerator();
    }

    static void run(Configuration configuration,
                    BlockchainGenerator blockchainGenerator,
                    Printer printer) {
        Blockchain blockchain = blockchainGenerator.generate(configuration.getNumberOfBlocks());
        validateBlockchain(blockchain);
        printer.print(blockchain);
    }

    private static void validateBlockchain(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException();
        }
    }
}
