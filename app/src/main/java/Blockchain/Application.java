package Blockchain;

import java.io.PrintStream;

public class Application {
    private final PrintStream printStream;
    private final BlockchainGenerator blockchainGenerator;

    public Application(Configuration configuration) {
        this.printStream = configuration.getPrintStream();
        this.blockchainGenerator = configuration.getBlockchainGenerator();
    }

    public void start() {
        blockchainGenerator.generate(1);
        printStream.print("output");
    }
}
