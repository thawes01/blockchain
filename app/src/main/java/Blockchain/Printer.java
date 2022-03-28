package Blockchain;

import java.io.PrintStream;
import java.util.*;

public class Printer {
    private final PrintStream printStream;

    private static double millisToSeconds(long milliseconds) {
        return (double) milliseconds / 1000;
    }

    /**
     * Allocates a {@code Printer} for printing information about blockchains.
     *
     * @param printStream  a stream to print the information to.
     */
    public Printer(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Returns the print stream associated with this printer.
     *
     * @return  the print stream of this printer.
     */
    public PrintStream getPrintStream() {
        return printStream;
    }

    /**
     * Prints information about a blockchain entry to this {@code Printer}'s stream.
     *
     * Note: the string printed ends with a newline character.
     *
     * @param blockchainEntry  a blockchain entry to print information about
     */
    public void print(BlockchainEntry blockchainEntry) {
        printStream.print(report(blockchainEntry));
    }

    private String report(BlockchainEntry blockchainEntry) {
        Block block = blockchainEntry.block();
        double generationTimeSeconds = millisToSeconds(blockchainEntry.generationTime());
        return String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Magic number: %s\n", block.getMagicNumber()) +
                String.format("Hash of the previous block:\n%s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block:\n%s\n", block.computeHash()) +
                String.format("Block was generating for %.0f seconds\n", generationTimeSeconds);
    }

    /**
     * Prints information about a blockchain to this {@code Printer}'s stream.
     *
     * Note: the string printed ends with a newline character.
     *
     * @param blockchain  a blockchain to print information about.
     */
    public void print(Blockchain blockchain) {
        printStream.print(report(blockchain));
    }

    private String report(Blockchain blockchain) {
        return String.join("\n", compileBlockchainEntryReports(blockchain));
    }

    private List<String> compileBlockchainEntryReports(Blockchain blockchain) {
        List<String> blockInformationStrings = new ArrayList<>();
        for (var blockchainEntry : blockchain) {
            blockInformationStrings.add("Block:\n" + report(blockchainEntry));
        }
        return blockInformationStrings;
    }
}
