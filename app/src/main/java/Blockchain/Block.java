package Blockchain;

public class Block {

    /**
     * Allocates a {@code Block} in a blockchain.
     *
     * @param primaryData  the data contained within the block, from which the
     *                     block's hash is generated
     */
    public Block(PrimaryBlockData primaryData) {}

    /**
     * Returns the hash of the block.
     *
     * Note: currently returns a default hash of "0".
     *
     * @return  the hash of the block.
     */
    public String getHash() {
        return "0";
    }
}
