package Blockchain;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setPrintStream(System.out);
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator();
        configuration.setBlockchainGenerator(blockchainGenerator);
        Application application = new Application(configuration);
        application.start();
    }
}
