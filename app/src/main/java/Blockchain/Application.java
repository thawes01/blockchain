package Blockchain;

public class Application {
    private final int lengthOfBlockchain;
    private final BlockchainGenerator blockchainGenerator;
    private final Printer printer;

    private static void throwExceptionIfNotValid(Blockchain blockchain) {
        if (!blockchain.validate()) {
            throw new RuntimeException("Blockchain generated not valid, there is a problem with blockchain generation");
        }
    }

    public Application(Configuration configuration) {
        this.lengthOfBlockchain = configuration.lengthOfBlockchain;
        this.blockchainGenerator = configuration.blockchainGenerator;
        this.printer = configuration.printer;
    }

    public void start() {
        Blockchain blockchain = blockchainGenerator.generate(lengthOfBlockchain);
        throwExceptionIfNotValid(blockchain);
        printer.print(blockchain);
    }
}
