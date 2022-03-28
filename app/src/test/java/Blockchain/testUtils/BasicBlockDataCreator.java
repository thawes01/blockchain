package Blockchain.testUtils;

import Blockchain.core.BasicBlockData;

public class BasicBlockDataCreator {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";

    public static BasicBlockData withDefaultArgs() {
        return new BasicBlockData(DUMMY_ID, DUMMY_HASH);
    }

    public static BasicBlockData withId(int id) {
        return new BasicBlockData(id, DUMMY_HASH);
    }

    public static BasicBlockData withPreviousBlockHash(String hash) {
        return new BasicBlockData(DUMMY_ID, hash);
    }
}
