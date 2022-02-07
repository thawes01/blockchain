package Blockchain.testUtils;

import Blockchain.BasicBlockData;

public class BasicBlockDataCreator {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";

    public static BasicBlockData defaultBasicBlockData() {
        return new BasicBlockData(DUMMY_ID, DUMMY_HASH);
    }

    public static BasicBlockData defaultBasicBlockData(int id) {
        return new BasicBlockData(id, DUMMY_HASH);
    }

    public static BasicBlockData defaultBasicBlockData(String hash) {
        return new BasicBlockData(DUMMY_ID, hash);
    }
}
