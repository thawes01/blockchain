package Blockchain;

public class PrimaryBlockData {
    private final int id;
    private final String previousBlockHash;
    private final long timestamp;

    public PrimaryBlockData(int id, String previousBlockHash, StopClock stopClock) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
        this.timestamp = stopClock.now();
    }

    public int getId() {
        return this.id;
    }

    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
