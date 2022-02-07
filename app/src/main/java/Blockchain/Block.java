package Blockchain;

public class Block {
    private final BasicBlockData basicBlockData;

    /**
     * Allocates a {@code Block} in a blockchain.
     *
     * @param basicBlockData  the data contained within the block, from which
     *                        the block's hash is generated
     */
    public Block(BasicBlockData basicBlockData) {
        this.basicBlockData = basicBlockData;
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
                basicBlockData.getCreationTimestamp(), basicBlockData.getPreviousBlockHash());
    }
}
