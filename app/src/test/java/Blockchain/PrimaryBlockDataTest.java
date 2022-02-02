package Blockchain;

import static Blockchain.testUtils.Time.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

class PrimaryBlockDataTest {
    private static final int DUMMY_ID = 0;
    private static final String DUMMY_HASH = "0a1b";
    private static final StopClock DUMMY_STOPCLOCK = new StopClock(Clock.systemUTC());

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getIdReturnsSuppliedID(int id) {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(id, DUMMY_HASH, DUMMY_STOPCLOCK);
        int returnedId = primaryBlockData.getId();
        assertEquals(id, returnedId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0011", "a345"})
    void getPreviousBlockHashReturnsSuppliedHash(String previousBlockHash) {
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(DUMMY_ID, previousBlockHash, DUMMY_STOPCLOCK);
        String returnedPreviousBlockHash = primaryBlockData.getPreviousBlockHash();
        assertEquals(previousBlockHash, returnedPreviousBlockHash);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    void getTimestampReturnsMillisFromEpoch(long creationTimeMillis) {
        Clock clock = defaultFixedClock(creationTimeMillis);
        StopClock stopClock = new StopClock(clock);
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(DUMMY_ID, DUMMY_HASH, stopClock);
        assertEquals(creationTimeMillis, primaryBlockData.getTimestamp());
    }

    @Test
    void timestampRecordsCreationTime() {
        long creationTime = 1234L;
        StopClock mockedFixedStopClock = Mockito.mock(StopClock.class);
        Mockito.when(mockedFixedStopClock.now()).thenReturn(creationTime);
        PrimaryBlockData primaryBlockData = new PrimaryBlockData(DUMMY_ID, DUMMY_HASH, mockedFixedStopClock);
        Mockito.verify(mockedFixedStopClock).now();
        assertEquals(creationTime, primaryBlockData.getTimestamp());
    }
}