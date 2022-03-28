package Blockchain;

/**
 * The entry point into the program.
 */
public class Main {
    /**
     * Generates blockchains and prints information about them.
     *
     * @param args  command line arguments (not used)
     */
    public static void main(String[] args) {
        Configuration configuration = Configuration.from(new Settings());
        Application application = new Application(configuration);
        application.run();
    }
}
