package Blockchain.io;

import java.util.Scanner;

/**
 * Responsible for obtaining input from the user via standard input.
 */
public class UserInputs {
    private static final String initialPrompt = "Enter how many zeros the hash must start with";
    private static final String rePrompt = initialPrompt + " (must be a non-negative integer)";

    /**
     * Gets the number of proof of work zeros from standard input.
     *
     * Re-prompts the user until a non-negative integer is supplied.
     *
     * @return  the number of proof of work zeros.
     */
    public static int getProofOfWorkNumber() {
        promptUser(initialPrompt);
        Scanner inputScanner = new Scanner(System.in);
        int proofOfWorkNumber = queryForNonNegativeIntegerUntilSuccess(inputScanner);
        endUserPrompting();
        return proofOfWorkNumber;
    }

    private static void promptUser(String message) {
        System.out.print(message + ": ");
    }

    private static int queryForNonNegativeIntegerUntilSuccess(Scanner inputScanner) {
        String input;
        while (true) {
            input = inputScanner.next();
            if (isNonNegativeInteger(input)) {
                return Integer.parseInt(input);
            }
            promptUser(rePrompt);
        }
    }

    private static boolean isNonNegativeInteger(String input) {
        return input.matches("^\\d+");
    }

    private static void endUserPrompting() {
        System.out.println();
    }
}
