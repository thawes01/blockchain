package Blockchain;

public class AppConfiguration {
    private final String initialHash = "0";

    /**
     * Returns the initial hash that is used for the "previous block's hash"
     * record in the first block in a blockchain.
     *
     * @return  the initial hash
     */
    public String getInitialHash() {
        return initialHash;
    }
}
