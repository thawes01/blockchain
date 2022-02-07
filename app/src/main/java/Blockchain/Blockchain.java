package Blockchain;

public class Blockchain {
    private Block block;

    /**
     * Adds a block to the end of this blockchain.
     *
     * @param block  a block to add to the end of the chain
     */
    public void push(Block block) {
        this.block = block;
    }

    /**
     * Look at (but do not remove) the last block in this blockchain.
     *
     * @return  the last block in this blockchain
     */
    public Block peek() {
        return this.block;
    }
}
