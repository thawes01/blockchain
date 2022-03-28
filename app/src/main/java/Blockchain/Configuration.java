package Blockchain;

public class Configuration {
    public int lengthOfBlockchain;
    public BlockchainGenerator blockchainGenerator;
    public Printer printer;

    /**
     * Allocates an application {@code Configuration} from default parameters
     * set in {@link Settings}.
     *
     * @return  the configuration created from default settings
     */
    public static Configuration from(Settings settings) {
        return new Configuration()
                .addLengthOfBlockchainFrom(settings)
                .addBlockchainGeneratorFrom(settings)
                .addPrinterFrom(settings);
    }

    private Configuration addLengthOfBlockchainFrom(Settings settings) {
        lengthOfBlockchain = settings.lengthOfBlockchain;
        return this;
    }

    private Configuration addBlockchainGeneratorFrom(Settings settings) {
        int proofOfWorkNumber = getProofOfWorkNumberFrom(settings);
        blockchainGenerator = new BlockchainGenerator(proofOfWorkNumber);
        return this;
    }

    private int getProofOfWorkNumberFrom(Settings settings) {
        if (settings.proofOfWorkNumber < 0) {
            return UserInputs.getProofOfWorkNumber();
        }
        return settings.proofOfWorkNumber;
    }

    private Configuration addPrinterFrom(Settings settings) {
        int generationTime = 99;
        printer = new Printer(settings.printStream, generationTime);
        return this;
    }
}
