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
    public static Configuration fromDefaultSettings() {
        return new Configuration()
                .addLengthOfBlockchainFromDefaultSettings()
                .addBlockchainGeneratorFromDefaultSettings()
                .addPrinterFromDefaultSettings();
    }

    private Configuration addLengthOfBlockchainFromDefaultSettings() {
        lengthOfBlockchain = Settings.LENGTH_OF_BLOCKCHAIN;
        return this;
    }

    private Configuration addBlockchainGeneratorFromDefaultSettings() {
        int proofOfWorkZeros = ProofOfWorkMarshaller.fromProofOfWorkNumber(Settings.PROOF_OF_WORK_NUMBER);
        blockchainGenerator = new BlockchainGenerator(proofOfWorkZeros);
        return this;
    }

    private Configuration addPrinterFromDefaultSettings() {
        printer = new Printer(Settings.PRINT_STREAM);
        return this;
    }
}
