package Blockchain;

public class PrimaryBlockData {
    private final int id;
    private final String previousBlockHash;

    public PrimaryBlockData(int id, String previousBlockHash) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
    }

    public int getId() {
        return this.id;
    }

    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }
}
