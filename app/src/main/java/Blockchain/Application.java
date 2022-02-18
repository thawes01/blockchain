package Blockchain;

import java.io.PrintStream;

public class Application {
    private PrintStream printStream;
    private BlockchainGenerator blockchainGenerator;

    public Application(PrintStream printStream,
                       BlockchainGenerator blockchainGenerator) {
        this.printStream = printStream;
        this.blockchainGenerator = blockchainGenerator;
    }

    public void start() {
        blockchainGenerator.generate(1);
        printStream.print("output");
    }
}
