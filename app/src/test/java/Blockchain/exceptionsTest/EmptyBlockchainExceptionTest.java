package Blockchain.exceptionsTest;

import Blockchain.exceptions.EmptyBlockchainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class EmptyBlockchainExceptionTest {

    @Test
    void constructorWithNullMessageAndCause() {
        EmptyBlockchainException emptyBlockchainException = new EmptyBlockchainException();
        assertNull(emptyBlockchainException.getMessage());
        assertNull(emptyBlockchainException.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"foo", "bar"})
    void constructorStoresMessage(String message) {
        EmptyBlockchainException emptyBlockchainException = new EmptyBlockchainException(message);
        assertEquals(message, emptyBlockchainException.getMessage());
    }

    @Test
    void constructorStoresCause() {
        Throwable cause = new Throwable();
        EmptyBlockchainException emptyBlockchainException = new EmptyBlockchainException(cause);
        assertEquals(cause, emptyBlockchainException.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"foo", "bar"})
    void constructorStoresMessageAndCause(String message) {
        Throwable cause = new Throwable();
        EmptyBlockchainException emptyBlockchainException = new EmptyBlockchainException(message, cause);
        assertEquals(message, emptyBlockchainException.getMessage());
        assertEquals(cause, emptyBlockchainException.getCause());
    }
}