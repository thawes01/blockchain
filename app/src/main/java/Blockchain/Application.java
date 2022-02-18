package Blockchain;

import java.io.PrintStream;

public class Application {
    private final PrintStream printStream;
    private final BlockchainGenerator blockchainGenerator;
    private final int lengthOfBlockchain;

    public Application(Configuration configuration) {
        this.printStream = configuration.printStream;
        this.blockchainGenerator = configuration.blockchainGenerator;
        this.lengthOfBlockchain = configuration.lengthOfBlockchain;
    }

    public void start() {
        blockchainGenerator.generate(lengthOfBlockchain);
        printStream.print("output");
    }
}
