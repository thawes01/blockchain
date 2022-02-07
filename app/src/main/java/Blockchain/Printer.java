package Blockchain;

import java.io.PrintStream;

public class Printer {
    private final PrintStream printStream;

    /**
     * Allocates a {@code Printer} for printing information about blockchains.
     *
     * @param printStream  a stream to print the information to.
     */
    public Printer(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Print information about a block to this {@code Printer}'s stream.
     *
     * @param block  a block to print information about.
     */
    public void print(Block block) {
        String output = String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Hash of the previous block: %s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block: %s\n", block.computeHash());
        printStream.print(output);
    }
}
