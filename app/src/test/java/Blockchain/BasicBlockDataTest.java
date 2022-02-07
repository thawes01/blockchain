package Blockchain;

import static Blockchain.testUtils.Time.defaultFixedClock;
import static Blockchain.testUtils.BasicBlockDataCreator.defaultBasicBlockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.time.*;
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

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void getCreationTimestampReturnsMillisFromEpoch(long creationTimeMillis) {
        Clock clock = defaultFixedClock(creationTimeMillis);
        StopClock stopClock = new StopClock(clock);
        BasicBlockData basicBlockData = defaultBasicBlockData(stopClock);
        assertEquals(creationTimeMillis, basicBlockData.getCreationTimestamp());
    }

    @Test
    void creationTimestampRecordsCreationTime() {
        long creationTime = 1234L;
        StopClock mockedFixedStopClock = Mockito.mock(StopClock.class);
        Mockito.when(mockedFixedStopClock.now()).thenReturn(creationTime);
        BasicBlockData basicBlockData = defaultBasicBlockData(mockedFixedStopClock);
        Mockito.verify(mockedFixedStopClock).now();
        assertEquals(creationTime, basicBlockData.getCreationTimestamp());
    }
}