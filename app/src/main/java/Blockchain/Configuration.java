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
        Configuration configuration = new Configuration();
        configuration.lengthOfBlockchain = Settings.LENGTH_OF_BLOCKCHAIN;
        configuration.blockchainGenerator = new BlockchainGenerator();
        configuration.printer = new Printer(Settings.PRINT_STREAM);
        return configuration;
    }
}
