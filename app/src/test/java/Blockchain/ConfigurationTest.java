package Blockchain;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ConfigurationTest {

    @Test
    void getNumberOfBlocksReturns() {
        Configuration configuration = new Configuration();
        int numBlocks = configuration.getNumberOfBlocks();
    }

    @Test
    void getSetPrintStream() {
        Configuration configuration = new Configuration();
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        configuration.setPrintStream(printStream);
    }
}