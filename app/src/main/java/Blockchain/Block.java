package Blockchain;

public class Block {
    private final int id;
    private final String previousBlockHash;
    private final long creationTimestamp;

    /**
     * Allocates a {@code Block} in a blockchain.
     *
     * @param primaryData  the data contained within the block, from which the
     *                     block's hash is generated
     */
    public Block(PrimaryBlockData primaryData) {
        this.id = primaryData.getId();
        this.previousBlockHash = primaryData.getPreviousBlockHash();
        this.creationTimestamp = primaryData.getCreationTimestamp();
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
        return String.format("%s%s%s", id, creationTimestamp, previousBlockHash);
    }
}
