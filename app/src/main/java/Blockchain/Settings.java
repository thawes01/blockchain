package Blockchain;

import java.io.PrintStream;

public class Settings {

    /** The print stream to print results to.*/
    public static final PrintStream PRINT_STREAM = System.out;

    /** The number of blocks to generate in the blockchain.*/
    public static final int LENGTH_OF_BLOCKCHAIN = 5;

    /**
     * If zero or positive, then gives the number of zeros at the beginning of
     * a block's hash to prove the work of generating the block. If negative,
     * then this number will be obtained from the user via standard input.
     */
    public static final int PROOF_OF_WORK_NUMBER = 4;
}
