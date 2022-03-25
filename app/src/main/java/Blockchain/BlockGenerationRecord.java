package Blockchain;

public class BlockGenerationRecord {
    public Block block;
    public int generationTime;

    public BlockGenerationRecord(Block block, int generationTime) {
        this.block = block;
        this.generationTime = generationTime;
    }
}
