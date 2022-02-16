package Blockchain;

public class BlockchainGenerator {
    private final StopClock stopClock = StopClock.systemUTC();
    private static final int FIRST_BLOCK_ID = 0;
    private static final String INITIAL_HASH = "0";

    /**
     * Generates a blockchain with a specified number of blocks.
     *
     * @param numBlocks  the number of blocks in the blockchain
     * @return  a blockchain with the specified number of blocks
     */
    public Blockchain generate(int numBlocks) {
        Blockchain blockchain = new Blockchain(INITIAL_HASH);
        for (int i = 0; i < numBlocks; i++) {
            generateAndAddNextBlock(blockchain);
        }
        return blockchain;
    }

    private void generateAndAddNextBlock(Blockchain blockchain) {
        Block block = generateNextBlock(blockchain);
        blockchain.push(block);
    }

    private Block generateNextBlock(Blockchain blockchain) {
        BasicBlockData basicBlockData = nextBasicBlockData(blockchain);
        return new Block(basicBlockData, stopClock);
    }

    private BasicBlockData nextBasicBlockData(Blockchain blockchain) {
        if (blockchain.getLength() == 0) {
            return basicBlockDataGivenEmptyBlockchain();
        } else {
            return basicBlockDataGivenNonemptyBlockchain(blockchain);
        }
    }

    private BasicBlockData basicBlockDataGivenEmptyBlockchain() {
        return new BasicBlockData(FIRST_BLOCK_ID, INITIAL_HASH);
    }

    private BasicBlockData basicBlockDataGivenNonemptyBlockchain(Blockchain blockchain) {
        int nextId = 0;
        String previousBlockHash = blockchain.getLastBlockHash();
        return new BasicBlockData(nextId, previousBlockHash);
    }
}
