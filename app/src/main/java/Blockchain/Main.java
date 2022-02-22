package Blockchain;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = Configuration.fromDefaultSettings();
        Application application = new Application(configuration);
        application.start();
    }
}
