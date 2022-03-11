package Blockchain;

import java.util.Scanner;

public class ProofOfWorkMarshaller {

    /**
     * Get the number of zeros each {@code Block}'s hash should begin with, as
     * a proof of work.
     *
     * If the parameter {@code proofOfWorkNumber} is non-negative, this is
     * the number returned. Otherwise, the number is returned from standard
     * input.
     *
     * @param proofOfWorkNumber  the number of zeros that hashes of
     *                           {@code Block}s should begin with, or a
     *                           negative number
     *
     * @return  the number of zeros that hashes of {@code Block}s should begin
     *          with
     */
    public static int fromProofOfWorkNumber(int proofOfWorkNumber) {
        if (proofOfWorkNumber >= 0) {
            return proofOfWorkNumber;
        }
        return proofOfWorkZerosFromSystemIn();
    }

    private static int proofOfWorkZerosFromSystemIn() {
        initialPrompt();
        Scanner inputScanner = new Scanner(System.in);
        int proofOfWorkZeros = queryForNonNegativeIntegerUntilSuccess(inputScanner);
        System.out.println();
        return proofOfWorkZeros;
    }

    private static void initialPrompt() {
        System.out.print("Enter how many zeros the hash must start with: ");
    }

    private static int queryForNonNegativeIntegerUntilSuccess(Scanner inputScanner) {
        String input;
        while (true) {
            input = inputScanner.next();
            if (isNonNegativeInteger(input)) {
                return Integer.parseInt(input);
            }
            rePrompt();
        }
    }

    private static boolean isNonNegativeInteger(String input) {
        return input.matches("^\\d+");
    }

    private static void rePrompt() {
        System.out.print("Enter how many zeros the hash must start with (must be a non-negative integer): ");
    }
}
