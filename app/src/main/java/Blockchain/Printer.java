package Blockchain;

import java.io.PrintStream;
import java.util.*;

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
     * Returns the print stream associated with this printer.
     *
     * @return  the print stream of this printer.
     */
    public PrintStream getPrintStream() {
        return printStream;
    }

    /**
     * Print information about a block to this {@code Printer}'s stream.
     *
     * Note: the string printed ends with a newline character.
     *
     * @param block  a block to print information about.
     */
    public void print(Block block) {
        printStream.print(blockInformation(block));
    }

    private String blockInformation(Block block) {
        return String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Hash of the previous block: %s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block: %s\n", block.computeHash());
    }

    /**
     * Print information about a blockchain to this {@code Printer}'s stream.
     *
     * Note: the string printed ends with a newline character.
     *
     * @param blockchain  a blockchain to print information about.
     */
    public void print(Blockchain blockchain) {
        printStream.print(blockchainInformation(blockchain));
    }

    private String blockchainInformation(Blockchain blockchain) {
        return String.join("\n", compileBlockInformationStrings(blockchain));
    }

    private List<String> compileBlockInformationStrings(Blockchain blockchain) {
        List<String> blockInformationStrings = new ArrayList<>();
        for (var block : blockchain) {
            blockInformationStrings.add("Block:\n" + blockInformation(block));
        }
        return blockInformationStrings;
    }
}
