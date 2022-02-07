package Blockchain.testUtils;

import Blockchain.BasicBlockData;
import Blockchain.Block;
import Blockchain.StopClock;

public class BlockCreator {
    private static final BasicBlockData DUMMY_BASIC_DATA = BasicBlockDataCreator.withDefaultArgs();
    private static final StopClock DUMMY_STOPCLOCK = StopClock.systemUTC();

    public static Block withDefaultArgs() {
        return new Block(DUMMY_BASIC_DATA, DUMMY_STOPCLOCK);
    }

    public static Block withBasicBlockData(BasicBlockData basicBlockData) {
        return new Block(basicBlockData, DUMMY_STOPCLOCK);
    }
}
