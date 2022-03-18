package Blockchain;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = Configuration.from(new Settings());
        Application application = new Application(configuration);
        application.run();
    }
}
