package Blockchain.core;

/**
 * Represents the basic data used to create blocks in a blockchain.
 */
public class BasicBlockData {
    private final int id;
    private final String previousBlockHash;

    /**
     * Allocates a {@code BasicBlockData} object with an ID number and the
     * hash of a previous block.
     *
     * @param id  the ID number of the block
     * @param previousBlockHash  the hash of a previous block in a blockchain
     */
    public BasicBlockData(int id, String previousBlockHash) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
    }

    /**
     * Returns the ID of the block data.
     *
     * @return  the ID number of the block. Expected to uniquely identify the
     *          block
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the hash of a previous block in a blockchain.
     *
     * @return  the hash of a previous block in a blockchain
     */
    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }
}
