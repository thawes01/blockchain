package Blockchain.exceptions;

/**
 * An exception thrown when an operation is attempted on an empty blockchain
 * that is not valid.
 */
public class EmptyBlockchainException extends RuntimeException {

    /**
     * Allocates a new {@code EmptyBlockchainException} with {@code null} as its
     * detail message and cause.
     */
    public EmptyBlockchainException() {}

    /**
     * Allocates a new {@code EmptyBlockchainException} with the specified
     * detail message.
     *
     * @param message  the detail message
     */
    public EmptyBlockchainException(String message) {
        super(message);
    }

    /**
     * Allocates a new {@code EmptyBlockchainException} with the specified
     * cause.
     *
     * @param cause  the cause of this exception
     */
    public EmptyBlockchainException(Throwable cause) {
        super(cause);
    }

    /**
     * Allocates a new {@code EmptyBlockchainException} with the specified
     * detail message and cause.
     *
     * @param message  the detail message
     * @param throwable  the cause of this exception
     */
    public EmptyBlockchainException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
