package Blockchain;

public class Block {
    private final int id;
    private final String previousBlockHash;
    private final long creationTimestamp;

    /**
     * Allocates a {@code Block} in a blockchain.
     *
     * @param basicBlockData  the data contained within the block, from which
     *                        the block's hash is generated
     */
    public Block(BasicBlockData basicBlockData) {
        this.id = basicBlockData.getId();
        this.previousBlockHash = basicBlockData.getPreviousBlockHash();
        this.creationTimestamp = basicBlockData.getCreationTimestamp();
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
