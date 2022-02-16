package Blockchain;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AppConfigurationTest {

    @Test
    void getNumberOfBlocksReturns() {
        AppConfiguration appConfiguration = new AppConfiguration();
        int numBlocks = appConfiguration.getNumberOfBlocks();
    }

    @Test
    void getSetPrintStream() {
        AppConfiguration appConfiguration = new AppConfiguration();
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        appConfiguration.setPrintStream(printStream);
    }
}