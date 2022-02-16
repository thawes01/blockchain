package Blockchain;

import Blockchain.testUtils.BlockCreator;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private Configuration configuration;
    private BlockchainGenerator blockchainGenerator;
    private ByteArrayOutputStream outputStreamCaptor;

    private static int countSubstringInstances(String string, String substring) {
        String[] stringSplits = string.split(substring);
        return stringSplits.length - 1;
    }

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        configuration = new Configuration();
        configuration.setPrintStream(new PrintStream(outputStreamCaptor));
        blockchainGenerator = new BlockchainGenerator();
    }

    @Test
    void runPrintsBlockchainInformation() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        configuration.setPrintStream(new PrintStream(outputStreamCaptor));

        App.run(configuration, blockchainGenerator);

        String systemOutContents = outputStreamCaptor.toString();
        int numberOfBlocks = countSubstringInstances(systemOutContents, "Block:");
        assertEquals(configuration.getNumberOfBlocks(), numberOfBlocks);
    }

    @Test
    void runThrowsRuntimeExceptionIfBlockchainNotValid() {
        Blockchain invalidBlockchain = new Blockchain("0");
        BasicBlockData basicBlockData = new BasicBlockData(0, "999");
        Block invalidFirstBlock = BlockCreator.withBasicBlockData(basicBlockData);
        invalidBlockchain.push(invalidFirstBlock);

        BlockchainGenerator mockedBlockchainGenerator = mock(BlockchainGenerator.class);
        when(mockedBlockchainGenerator.generate(1)).thenReturn(invalidBlockchain);

        assertThrows(RuntimeException.class,
                () -> App.run(configuration, mockedBlockchainGenerator));
    }
}
