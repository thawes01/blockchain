package Blockchain;

import Blockchain.core.BasicBlockData;
import Blockchain.utilities.StopClock;
import Blockchain.utilities.StringUtil;

public class Block {
    private final BasicBlockData basicBlockData;
    private final long creationTimestamp;
    private long magicNumber;

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
        this.magicNumber = -1;
    }

    /**
     * Find a magic number for this block.
     *
     * This is a number which forms part of this block's data and results in a
     * block hash starting with the specified number of zeros.
     *
     * @param numberOfZeros  the number of zeros this block's hash should begin
     *                       with
     */
    public void findMagicNumber(int numberOfZeros) {
        String desiredStartOfHash = desiredStartOfHash(numberOfZeros);
        magicNumber = 0;
        while (!computeHash().startsWith(desiredStartOfHash)) {
            magicNumber++;
        }
    }

    private String desiredStartOfHash(int numberOfZeros) {
        return "0".repeat(numberOfZeros);
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
        return String.format("%s%s%s%s", basicBlockData.getId(),
                getCreationTimestamp(), magicNumber,
                basicBlockData.getPreviousBlockHash());
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

    /**
     * Returns a magic number of this block.
     *
     * If a magic number for this block has not been found then this method will
     * return -1. Magic numbers are computed using the
     * {@link Block#findMagicNumber} method.
     *
     * @return  a magic number for this block, or -1 if not a magic number has
     *          not been found
     */
    public long getMagicNumber() {
        return this.magicNumber;
    }
}
