package Blockchain;

import static Blockchain.testUtils.BasicBlockDataCreator.defaultBasicBlockData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class BasicBlockDataTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getIdReturnsSuppliedID(int id) {
        BasicBlockData basicBlockData = defaultBasicBlockData(id);
        int returnedId = basicBlockData.getId();
        assertEquals(id, returnedId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0011", "a345"})
    void getPreviousBlockHashReturnsSuppliedHash(String previousBlockHash) {
        BasicBlockData basicBlockData = defaultBasicBlockData(previousBlockHash);
        String returnedPreviousBlockHash = basicBlockData.getPreviousBlockHash();
        assertEquals(previousBlockHash, returnedPreviousBlockHash);
    }
}