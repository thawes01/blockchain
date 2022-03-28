package Blockchain;

public class BlockchainEntry {
    public Block block;
    public int generationTime;

    public BlockchainEntry(Block block, int generationTime) {
        this.block = block;
        this.generationTime = generationTime;
    }
}
