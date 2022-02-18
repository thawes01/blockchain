package Blockchain;

import java.io.PrintStream;

public class Configuration {
    private PrintStream printStream;
    private BlockchainGenerator blockchainGenerator;

    /**
     * Gets the print stream to capture this application's output.
     *
     * @return  the print stream capturing application output
     */
    public PrintStream getPrintStream() {
        return printStream;
    }


    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public BlockchainGenerator getBlockchainGenerator() {
        return blockchainGenerator;
    }

    public void setBlockchainGenerator(BlockchainGenerator blockchainGenerator) {
        this.blockchainGenerator = blockchainGenerator;
    }
}
