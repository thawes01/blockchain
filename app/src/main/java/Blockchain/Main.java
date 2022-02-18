package Blockchain;

public class Main {

    public static void main(String[] args) {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator();
        Application application = new Application(System.out, blockchainGenerator);
        application.start();
    }
}
