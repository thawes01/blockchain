package Blockchain;

import Blockchain.exceptions.EmptyBlockchainException;

import java.util.*;

public class Blockchain implements Iterable<BlockchainEntry> {
    private final String initialHash;
    private Block lastBlock;
    private String lastBlockHash;
    private Block penultimateBlock;
    private int length = 0;
    private final List<Block> blocks = new ArrayList<>();
    private final List<BlockchainEntry> blockchainEntries = new ArrayList<>();
    private boolean startingHashCorrect = true;
    private boolean successiveBlockHashesAgree = true;

    /**
     * Allocates a {@code Blockchain} with a specified initial hash.
     *
     * The initial hash is used to populate the "previous block's hash" for the
     * first block in this blockchain.
     *
     * @param initialHash  an initial hash to use for the first block
     */
    public Blockchain(String initialHash) {
        this.initialHash = initialHash;
        lastBlockHash = initialHash;
    }

    /**
     * Gets the {@code List} of {@link Block}s contained within this blockchain.
     *
     * @return  the list of blockchain blocks
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * Adds an entry to the end of this blockchain.
     *
     * @param blockchainEntry  a blockchain entry
     */
    public void add(BlockchainEntry blockchainEntry) {
        blockchainEntries.add(blockchainEntry);
        Block block = blockchainEntry.block();
        blocks.add(block);
        updateBlockCaches(block);
        updateLength();
        updateVerification();
    }

    private void updateBlockCaches(Block block) {
        penultimateBlock = lastBlock;
        lastBlock = block;
        lastBlockHash = lastBlock.computeHash();
    }

    private void updateLength() {
        length++;
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
        startingHashCorrect = lastBlockPreviousHash.equals(initialHash);
    }

    private void updateSuccessiveBlockHashesAgree() {
        String lastBlockPreviousHash = lastBlock.getPreviousBlockHash();
        String penultimateBlockHash = penultimateBlock.computeHash();
        successiveBlockHashesAgree &= lastBlockPreviousHash.equals(penultimateBlockHash);
    }

    /**
     * Retrieves (but does not remove) the last entry in this blockchain.
     *
     * @return  the last entry in this blockchain
     *
     * @throws EmptyBlockchainException  if the blockchain is empty
     */
    public BlockchainEntry getLastEntry() {
        if (length == 0) {
            throw new EmptyBlockchainException(
                    "Tried getting last entry of blockchain, but blockchain is empty");
        }
        return blockchainEntries.get(length - 1);
    }

    /**
     * Check that this blockchain is valid.
     *
     * A blockchain is valid if:
     * <ul>
     *     <li>the first block has the correct entry in its 'previous block
     *     hash' field; and</li>
     *     <li>each block correctly records the hash of the previous block in
     *     the chain.</li>
     * </ul>
     * In the case where this blockchain is empty, the return value is
     * {@code true}.
     *
     * @return  whether this blockchain is valid
     */
    public boolean validate() {
        return startingHashCorrect && successiveBlockHashesAgree;
    }

    /**
     * Returns an iterator of {@link BlockchainEntry} objects that make up this
     * blockchain.
     *
     * @return  an iterator of blockchain entries
     */
    public Iterator<BlockchainEntry> iterator() {
        return blockchainEntries.iterator();
    }

    /**
     * Returns the number of blocks in this blockchain.
     *
     * @return  the number of blocks
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the hash of the last block in this blockchain.
     *
     * If this blockchain is empty, then the initial hash is returned.
     *
     * @return  the hash of the last block in the chain, or the initial hash if
     * there are no blocks.
     */
    public String getLastBlockHash() {
        return lastBlockHash;
    }

    /**
     * Gets the ID of the last block in this blockchain.
     *
     * @return  the ID of the last block in this blockchain
     *
     * @throws EmptyBlockchainException  if the blockchain is empty
     */
    public int getLastBlockId() {
        if (length == 0) {
            throw new EmptyBlockchainException(
                    "Tried accessing ID of last block in blockchain, but blockchain is empty");
        }
        return lastBlock.getId();
    }
}
