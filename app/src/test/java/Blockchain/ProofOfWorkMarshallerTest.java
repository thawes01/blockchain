package Blockchain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

// Approach to simulating System.in and System.out adapted from
//
// https://www.baeldung.com/java-testing-system-out-println
//
// as accessed on 2022-03-08.
class ProofOfWorkMarshallerTest {
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;
    private static final int inputProofOfWorkZeros = 3;
    private static final String userPrompt = "Enter how many zeros the hash must start with: ";
    private static final String userRePrompt = "Enter how many zeros the hash must start with (must be a non-negative integer): ";
    private ByteArrayOutputStream outputCaptor;

    @BeforeEach
    void setUpSystemInReplacement() {
        byte[] input = String.valueOf(inputProofOfWorkZeros).getBytes();
        System.setIn(new ByteArrayInputStream(input));
    }

    @BeforeEach
    void setUpSystemOutRedirect() {
        outputCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCaptor));
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(systemIn);
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(systemOut);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void fromProofOfWorkZerosReturnsProofOfWorkNumberIfNonNegative(int proofOfWorkNumber) {
        assertEquals(proofOfWorkNumber, ProofOfWorkMarshaller.fromProofOfWorkNumber(proofOfWorkNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void fromProofOfWorkZerosReturnsFromStandardInputIfProofOfWorkNumberNegative(int proofOfWorkNumber) {
        int proofOfWorkZeros = ProofOfWorkMarshaller.fromProofOfWorkNumber(proofOfWorkNumber);

        assertEquals(inputProofOfWorkZeros, proofOfWorkZeros);
    }

    @Test
    void fromProofOfWorkZerosPromptsUserForInputIfProofOfWorkNumberNegative() {
        int proofOfWorkNumber = -1;
        ProofOfWorkMarshaller.fromProofOfWorkNumber(proofOfWorkNumber);
        String prompt = outputCaptor.toString();
        assertEquals(userPrompt + "\n", prompt);
    }

    @Test
    void fromProofOfWorkZerosPromptsUserRepeatedlyIfNonNegativeIntegerNotProvided() {
        String input1 = "a", input2 = "-1", input3 = "99";
        byte[] input = "%s\n%s\n%s".formatted(input1, input2, input3).getBytes();
        System.setIn(new ByteArrayInputStream(input));

        ProofOfWorkMarshaller.fromProofOfWorkNumber(-1);

        String prompt = outputCaptor.toString();
        String userPrompts = "%s%s%s\n".formatted(userPrompt, userRePrompt, userRePrompt);
        assertEquals(userPrompts, prompt);
    }
}