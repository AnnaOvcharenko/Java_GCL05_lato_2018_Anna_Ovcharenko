
import files.ExtendedSystemCache;
import files.ScatterSystem;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExtendedSystemCacheTest {

    ScatterSystem system;
    ExtendedSystemCache cacheModel;
    ExtendedSystemCache cacheTest;

    String csv;
    String bin;

    public ExtendedSystemCacheTest() {
    }

    @Before
    public void setUp() {

        system = new ScatterSystem();
        cacheModel = new ExtendedSystemCache();
        cacheTest = new ExtendedSystemCache();

        csv = "test.csv";
        bin = "test.bin";

        for (int i = 0; i < 10; i++) {

            int random = ThreadLocalRandom.current().nextInt(0, 5 + 1);

            double[] input = {1, 2, random, 10};

            Double output = cacheTest.get(input);

            if (output == null) {
                output = system.sum(input);
                cacheTest.put(input, output);
                cacheModel.put(input, output);
            }
        }
    }

    @Test
    public void shouldSaveToCSVFromPath() {

        try {
            cacheTest.exportCSV(csv);
            cacheTest.importCSV(csv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assertTrue(cacheModel.cache.equals(cacheTest.cache));
    }

    @Test
    public void shouldSaveToCSVFromFile() {

        File file = new File(csv);

        try {
            cacheTest.exportCSV(file);
            cacheTest.importCSV(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(cacheModel.cache.equals(cacheTest.cache));
    }

    @Test
    public void shouldSerializeFromPath() {
        try {
            cacheTest.serialize(bin);

            cacheTest.deserialize(bin);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(cacheModel.cache.equals(cacheTest.cache));
    }

    @Test
    public void shouldSerializeFromFile() {

        File file = new File(bin);

        try {
            cacheTest.serialize(file);

            cacheTest.deserialize(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(cacheModel.cache.equals(cacheTest.cache));
    }

//    @After
//    public void tearDown() {
//        File f = new File(csv);
//        if (f.exists() && !f.isDirectory()) {
//            f.delete();
//        }
//
//        f = new File(bin);
//        if (f.exists() && !f.isDirectory()) {
//            f.delete();
//        }
//    }

}
