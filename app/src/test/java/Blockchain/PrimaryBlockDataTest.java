package Blockchain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryBlockDataTest {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getIdReturnsSuppliedID(int id) {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(id, DUMMY_HASH);
        int returnedId = primaryBlockData.getId();
        assertEquals(id, returnedId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0011", "a345"})
    void getPreviousBlockHashReturnsSuppliedHash(String previousBlockHash) {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(DUMMY_ID, previousBlockHash);
        String returnedPreviousBlockHash = primaryBlockData.getPreviousBlockHash();
        assertEquals(previousBlockHash, returnedPreviousBlockHash);
    }
}