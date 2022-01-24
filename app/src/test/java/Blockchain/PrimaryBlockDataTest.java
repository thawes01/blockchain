package Blockchain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryBlockDataTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getIdReturnsSuppliedID(int id) {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(id);
        int returnedId = primaryBlockData.getId();
        assertEquals(id, returnedId);
    }
}