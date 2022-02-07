package Blockchain;

public class BasicBlockData {
    private final int id;
    private final String previousBlockHash;
    private final long creationTimestamp;

    /**
     * Allocates a {@code BasicBlockData} object with an ID number and the
     * hash of a previous block.
     *
     * Uses a {@link StopClock} to capture the creation time of this object.
     *
     * @param id  the ID number of the block
     * @param previousBlockHash  the hash of a previous block in a blockchain
     * @param stopClock  used to record the creation time of the block
     */
    public BasicBlockData(int id, String previousBlockHash, StopClock stopClock) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
        this.creationTimestamp = stopClock.now();
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

    /**
     * Returns the time that this block data was created.
     *
     * The creation time is taken during this object's instantiation and is
     * represented as a number of milliseconds from the start of
     * the epoch, as returned by {@link StopClock#now()}.
     *
     * @return  the time that the block data was created
     */
    public long getCreationTimestamp() {
        return this.creationTimestamp;
    }
}
