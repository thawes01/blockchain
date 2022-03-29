package Blockchain.testUtils;

import Blockchain.core.*;

public class BlockCreator {
    private static final BasicBlockData DUMMY_BASIC_DATA = BasicBlockDataCreator.withDefaultArgs();
    private static final long DUMMY_TIMESTAMP = 1234L;

    public static Block withBasicBlockData(BasicBlockData basicBlockData) {
        return new Block(basicBlockData, DUMMY_TIMESTAMP);
    }

    /** Useful for creating multiple {@code Block}s in quick succession, which
     * can result in the same creation time being returned by the default
     * system clock. */
    public static Block withCreationTimestamp(long creationTimestamp) {
        return new Block(DUMMY_BASIC_DATA, creationTimestamp);
    }
}
