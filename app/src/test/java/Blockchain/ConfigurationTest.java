package Blockchain;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class ConfigurationTest {
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    /** Replaces {@code System.in} calls so that tests run with different values
     * of {@link Settings#PROOF_OF_WORK_NUMBER}.
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
    void fromDefaultSettingsInitialisesBlockchainGenerator() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertNotNull(configuration.blockchainGenerator);
    }

    @Test
    void fromDefaultSettingsInitialisesPrinter() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertEquals(Settings.PRINT_STREAM, configuration.printer.getPrintStream());
    }

    @Test
    void fromDefaultSettingsInitialisesLengthOfBlockchain() {
        Configuration configuration = Configuration.fromDefaultSettings();
        assertEquals(Settings.LENGTH_OF_BLOCKCHAIN, configuration.lengthOfBlockchain);
    }

    // Mocking adapted from
    //
    // https://www.baeldung.com/mockito-mock-static-methods#mocking-a-static-method-with-arguments
    //
    // as accessed on 2022-03-08.
    @Test
    void fromDefaultSettingsInitialisesBlockchainGeneratorWithCorrectProofOfWorkZeros() {
        int proofOfWorkZeros = 2;
        try (MockedStatic<ProofOfWorkMarshaller> proofOfWorkMarshaller = Mockito.mockStatic(ProofOfWorkMarshaller.class)) {
            proofOfWorkMarshaller.when(
                    () -> ProofOfWorkMarshaller.fromProofOfWorkNumber(Settings.PROOF_OF_WORK_NUMBER))
                    .thenReturn(proofOfWorkZeros);

            Configuration configuration = Configuration.fromDefaultSettings();

            proofOfWorkMarshaller.verify(
                    () -> ProofOfWorkMarshaller.fromProofOfWorkNumber(Settings.PROOF_OF_WORK_NUMBER));
            assertEquals(proofOfWorkZeros, configuration.blockchainGenerator.getProofOfWorkZeros());
        }
    }
}