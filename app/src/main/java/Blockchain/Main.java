package Blockchain;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.printStream = System.out;
        configuration.lengthOfBlockchain = 5;
        configuration.blockchainGenerator = new BlockchainGenerator();
        Application application = new Application(configuration);
        application.start();
    }
}
