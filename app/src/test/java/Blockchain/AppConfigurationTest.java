package Blockchain;

import org.junit.jupiter.api.Test;

class AppConfigurationTest {

    @Test
    void gettersReturn() {
        AppConfiguration appConfiguration = new AppConfiguration();
        appConfiguration.getInitialHash();
    }
}