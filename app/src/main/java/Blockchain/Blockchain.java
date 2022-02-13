package Blockchain;

import java.util.*;

public class Blockchain implements Iterable<Block> {
    private static final String EXPECTED_STARTING_HASH = "0";
    private Block lastBlock;
    private Block penultimateBlock;
    private int length = 0;
    private final List<Block> blocks = new ArrayList<>();
    private boolean startingHashCorrect = true;
    private boolean successiveBlockHashesAgree = true;

    /**
     * Adds a block to the end of this blockchain.
     *
     * @param block  a block to add to the end of the chain
     */
    public void push(Block block) {
        blocks.add(block);
        penultimateBlock = lastBlock;
        lastBlock = block;
        length++;
        updateVerification();
    }

    private void updateVerification() {
        if (penultimateBlock == null) {
            updateStartingHashCorrect();
        } else {
            updateSuccessiveBlockHashesAgree();
        }
    }

    private void updateStartingHashCorrect() {
        String lastBlockPreviousHash = lastBlock.getPreviousBlockHash();
        startingHashCorrect = lastBlockPreviousHash.equals(EXPECTED_STARTING_HASH);
    }

    private void updateSuccessiveBlockHashesAgree() {
        String lastBlockPreviousHash = lastBlock.getPreviousBlockHash();
        String penultimateBlockHash = penultimateBlock.computeHash();
        successiveBlockHashesAgree &= lastBlockPreviousHash.equals(penultimateBlockHash);
    }

    /**
     * Retrieve (but do not remove) the last block in this blockchain.
     *
     * @return  the last block in this blockchain
     */
    public Block peek() {
        return this.lastBlock;
    }

    public boolean verify() {
        return startingHashCorrect && successiveBlockHashesAgree;
    }

    public Iterator<Block> iterator() {
        return blocks.iterator();
    }

    /**
     * Returns the number of blocks in this blockchain.
     *
     * @return  the number of blocks
     */
    public int getLength() {
        return length;
    }
}
