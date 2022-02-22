package Blockchain;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.lengthOfBlockchain = 5;
        configuration.blockchainGenerator = new BlockchainGenerator();
        configuration.printer = new Printer(System.out);
        Application application = new Application(configuration);
        application.start();
    }
}
