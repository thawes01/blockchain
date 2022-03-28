package Blockchain;

import Blockchain.core.BasicBlockData;
import Blockchain.core.Block;
import Blockchain.utilities.StopClock;

public class BlockchainGenerator {
    private static final int FIRST_BLOCK_ID = 0;
    private static final String INITIAL_HASH = "0";
    private final StopClock stopClock;
    private final int proofOfWorkNumber;

    /**
     * Allocates a {@code BlockchainGenerator} for generating blockchains.
     *
     * Blockchains generated by this {@code BlockchainGenerator} do not exhibit
     * any proof of work.
     */
    public BlockchainGenerator() {
        this(0);
    }

    /**
     * Allocates a {@code BlockchainGenerator} for generating blockchains that
     * are proven with a given number of zeros.
     *
     * The blockchain is proven for the given number of zeros if the hash of
     * each block in the blockchain begins with the given number of zeros.
     *
     * @param proofOfWorkNumber  the number of zeros for confirming proof of work
     *                          for each block in the blockchain
     */
    public BlockchainGenerator(int proofOfWorkNumber) {
        this(proofOfWorkNumber, StopClock.systemUTC());
    }

    public BlockchainGenerator(int proofOfWorkNumber, StopClock stopClock) {
        this.stopClock = stopClock;
        this.proofOfWorkNumber = proofOfWorkNumber;
    }

    int getProofOfWorkNumber() {
        return proofOfWorkNumber;
    }

    /**
     * Generates a blockchain with a specified number of blocks.
     *
     * Each block in the resulting blockchain is proved with the number of zeros
     * specified in this {@code BlockchainGenerator}'s construction.
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
        stopClock.start();
        Block block = generateNextBlock(blockchain);
        stopClock.stop();
        BlockchainEntry entry = new BlockchainEntry(block, stopClock.getElapsedTime());
        blockchain.add(entry);
    }

    private Block generateNextBlock(Blockchain blockchain) {
        BasicBlockData basicBlockData = nextBasicBlockData(blockchain);
        Block block = new Block(basicBlockData, stopClock);
        block.findMagicNumber(proofOfWorkNumber);
        return block;
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
        int nextId = blockchain.getLastBlockId() + 1;
        String previousBlockHash = blockchain.getLastBlockHash();
        return new BasicBlockData(nextId, previousBlockHash);
    }
}
