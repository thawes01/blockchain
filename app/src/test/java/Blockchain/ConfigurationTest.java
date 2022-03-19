package Blockchain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class ConfigurationTest {
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;
    private Settings settings;

    @BeforeEach
    void setUpSettings() {
        settings = new Settings();
    }

    /** Replaces {@code System.in} calls so that tests run with different values
     * of {@link Settings#proofOfWorkNumber}.
     *
     * Adapted from approach outlined in
     *
     * https://www.baeldung.com/java-testing-system-out-println
     *
     * as accessed on 2022-03-08.
     */
    @BeforeEach
    void setUpSystemInReplacement() {
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(inputStream);
    }

    /** Redirects {@code System.out} calls to avoid cluttering the console
     * during tests.
     *
     * Adapted from approach outlined in
     *
     * https://www.baeldung.com/java-testing-system-out-println
     *
     * as accessed on 2022-03-08.
     */
    @BeforeEach
    void setUpSystemOutRedirect() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(systemIn);
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(systemOut);
    }

    @Test
    void fromInitialisesBlockchainGenerator() {
        Configuration configuration = Configuration.from(settings);
        assertNotNull(configuration.blockchainGenerator);
    }

    @Test
    void fromInitialisesPrinter() {
        Configuration configuration = Configuration.from(settings);
        assertEquals(settings.printStream, configuration.printer.getPrintStream());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void fromInitialisesLengthOfBlockchain(int lengthOfBlockchain) {
        settings.lengthOfBlockchain = lengthOfBlockchain;
        Configuration configuration = Configuration.from(settings);
        assertEquals(lengthOfBlockchain, configuration.lengthOfBlockchain);
    }

    // Mocking adapted from
    //
    // https://www.baeldung.com/mockito-mock-static-methods#mocking-a-static-method-with-arguments
    //
    // as accessed on 2022-03-08.
    @Test
    void fromInitialisesBlockchainGeneratorWithCorrectProofOfWorkZerosWhenNegative() {
        settings.proofOfWorkNumber = -1;
        int userSubmittedProofOfWork = 2;
        try (MockedStatic<UserInputs> proofOfWorkPrompter = Mockito.mockStatic(UserInputs.class)) {
            proofOfWorkPrompter.when(UserInputs::getProofOfWorkNumber)
                    .thenReturn(userSubmittedProofOfWork);

            Configuration configuration = Configuration.from(settings);

            proofOfWorkPrompter.verify(UserInputs::getProofOfWorkNumber);
            assertEquals(userSubmittedProofOfWork, configuration.blockchainGenerator.getProofOfWorkNumber());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void fromInitialisesBlockchainGeneratorWithCorrectProofOfWorkZerosWhenNonNegative(int proofOfWorkNumber) {
        settings.proofOfWorkNumber = proofOfWorkNumber;

        Configuration configuration = Configuration.from(settings);

        assertEquals(proofOfWorkNumber, configuration.blockchainGenerator.getProofOfWorkNumber());
    }
}