package Blockchain;

public class Block {
    private final BasicBlockData basicBlockData;
    private final long creationTimestamp;

    /**
     * Allocates a {@code Block} in a blockchain.
     *
     * @param basicBlockData  the data contained within the block, from which
     *                        the block's hash is generated
     * @param stopClock  used to record the time of the block's creation
     */
    public Block(BasicBlockData basicBlockData, StopClock stopClock) {
        this.basicBlockData = basicBlockData;
        this.creationTimestamp = stopClock.now();
    }

    /**
     * Computes the hash of the block.
     *
     * @return  the hash of the block.
     */
    public String computeHash() {
        return StringUtil.applySha256(stringRepresentation());
    }

    private String stringRepresentation() {
        return String.format("%s%s%s", basicBlockData.getId(),
                getCreationTimestamp(), basicBlockData.getPreviousBlockHash());
    }

    /**
     * Gets the ID of the block.
     *
     * @return  the ID of the block.
     */
    public int getId() {
        return basicBlockData.getId();
    }

    /**
     * Gets the hash of the previous block.
     *
     * @return  the hash of the previous block.
     */
    public String getPreviousBlockHash() {
        return basicBlockData.getPreviousBlockHash();
    }

    /**
     * Returns the time that this block was created.
     *
     * The creation time is taken during this object's instantiation and is
     * represented as a number of milliseconds from the start of
     * the epoch, as returned by {@link StopClock#now()}.
     *
     * @return  the time that the block was created
     */
    public long getCreationTimestamp() {
        return this.creationTimestamp;
    }

    // TODO: implement real version
    public long getMagicNumber() {
        return 1L;
    }
}
