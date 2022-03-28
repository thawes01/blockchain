package Blockchain;

import java.io.PrintStream;
import java.util.*;

public class Printer {
    private final PrintStream printStream;
    private final int generationTime;

    /**
     * Allocates a {@code Printer} for printing information about blockchains.
     *
     * @param printStream  a stream to print the information to.
     */
    public Printer(PrintStream printStream, int generationTime) {
        this.printStream = printStream;
        this.generationTime = generationTime;
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
        printStream.print(blockInformation(block, generationTime));
    }

    private String blockInformation(Block block, int generationTime) {
        return String.format("Id: %d\n", block.getId()) +
                String.format("Timestamp: %s\n", block.getCreationTimestamp()) +
                String.format("Magic number: %s\n", block.getMagicNumber()) +
                String.format("Hash of the previous block:\n%s\n", block.getPreviousBlockHash()) +
                String.format("Hash of the block:\n%s\n", block.computeHash()) +
                String.format("Block was generating for %d seconds\n", generationTime);
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
            blockInformationStrings.add("Block:\n" + blockInformation(block, generationTime));
        }
        return blockInformationStrings;
    }

    public void print(BlockchainEntry... blockchainEntries) {
        String report = compileBlockGenerationRecordsReport(blockchainEntries);
        printStream.print(report);
    }

    private String compileBlockGenerationRecordsReport(BlockchainEntry... blockchainEntries) {
        String[] blockInformationStrings = new String[blockchainEntries.length];
        for (int i = 0; i < blockchainEntries.length; i++) {
            blockInformationStrings[i] = "Block:\n" +
                    compileBlockGenerationRecordReport(blockchainEntries[i]);
        }
        return String.join("\n", blockInformationStrings);
    }

    private String compileBlockGenerationRecordReport(BlockchainEntry blockchainEntry) {
        Block block = blockchainEntry.block;
        int generationTime = blockchainEntry.generationTime;
        return blockInformation(block, generationTime);
    }
}
