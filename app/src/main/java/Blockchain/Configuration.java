package Blockchain;

import java.io.PrintStream;

public class Configuration {
    private final int numberOfBlocks = 5;
    private PrintStream printStream = System.out;

    /**
     * Gets the number of blocks to generate in this application's blockchain.
     *
     * @return  the number of blocks in this application's blockchain.
     */
    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    /**
     * Gets the print stream to capture this application's output.
     *
     * @return  the print stream capturing application output
     */
    public PrintStream getPrintStream() {
        return printStream;
    }

    /** Sets the print stream for capturing this application's output. */
    void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
