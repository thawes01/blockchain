package Blockchain;

import java.io.PrintStream;

/**
 * Settings that provide the starting point for configuring an application.
 */
public class Settings {

    /** The print stream to print results to.*/
    public PrintStream printStream = System.out;

    /** The number of blocks to generate in the blockchain.*/
    public int lengthOfBlockchain = 5;

    /**
     * If zero or positive, then gives the number of zeros at the beginning of
     * a block's hash to prove the work of generating the block. If negative,
     * then this number will be obtained from the user via standard input.
     */
    public int proofOfWorkNumber = -1;
}
