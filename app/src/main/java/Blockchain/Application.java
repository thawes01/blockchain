package Blockchain;

public class Application {
    private final int lengthOfBlockchain;
    private final BlockchainGenerator blockchainGenerator;
    private final Printer printer;

    public Application(Configuration configuration) {
        this.lengthOfBlockchain = configuration.lengthOfBlockchain;
        this.blockchainGenerator = configuration.blockchainGenerator;
        this.printer = configuration.printer;
    }

    public void start() {
        Blockchain blockchain = blockchainGenerator.generate(lengthOfBlockchain);
        printer.print(blockchain);
    }
}
