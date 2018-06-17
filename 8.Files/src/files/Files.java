package files;

import java.io.IOException;

public class Files {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ExtendedSystemCache exCache = new ExtendedSystemCache();
        ExtendedSystemCache imCache = new ExtendedSystemCache();
        ScatterSystem system = new ScatterSystem();
        String path = "cache.txt";
        String binPath = "bCache.bin";
        String pathTest = "test.txt";

        double[] input1 = {1, 2, 3};
        double[] input2 = {2, 2, 0};
        double[] input3 = {1, 2, -1};
        double output;

        output = system.sum(input1);
        exCache.put(input1, output);
        System.out.println("Counted & added to cache");

        output = system.sum(input2);
        exCache.put(input2, output);
        System.out.println("Counted & added to cache");

        output = system.sum(input3);
        exCache.put(input3, output);
        System.out.println("Counted & added to cache");

        exCache.exportCSV(path);
        imCache.importCSV(path);
        imCache.exportCSV(pathTest);
        System.out.println(exCache.cache.equals(imCache.cache));

        exCache.serialize(binPath);
        imCache.deserialize(binPath);
        imCache.exportCSV(pathTest);
        System.out.println(exCache.cache.equals(imCache.cache));

//        ExtendedSystemCache exCache = new ExtendedSystemCache();
//        ExtendedSystemCache imCache = new ExtendedSystemCache();
//        ScatterSystem system = new ScatterSystem();
//
//        String path = "cache.txt";
//        String binPath = "bCache.bin";
//        String pathTest = "test.txt";
//
//        double[] input = new double[3];
//        double start = 1;
//        double end = 10;
//        double random;
//
//        for (int k = 0; k < 5; ++k) {
//            for (int i = 0; i < 3; ++i) {
//                 random = new Random(System.currentTimeMillis()).nextDouble();
//                input[i] = start + (random * (end - start));
//            }
//
//            if (!exCache.cache.containsKey(input)) {
//                double output = system.sum(input);
//                exCache.put(input, output);
//                System.out.println("Counted & added to cache");
//            } else {
//                System.out.println("Aready exist in cache");
//            }
//        }
//        
//        exCache.exportCSV(path);
//        imCache.importCSV(path);
//        imCache.exportCSV(pathTest);
////////////////////////////////////////////////////////////////////   
////////////////////////////////////////////////////////////////////
//        ExtendedSystemCache exCache = new ExtendedSystemCache();
//        ExtendedSystemCache imCache = new ExtendedSystemCache();
//        ScatterSystem system = new ScatterSystem();
//
//        double[] input = new double[3];
//        double start = 1;
//        double end = 10;
//        double random;
//        String path = "cache.txt";
//        String pathTest = "test.txt";
//        
//        for (int j = 0; j < 5; ++j) {
//            for (int i = 0; i < 3; ++i) {
//                random = new Random().nextDouble();
//                input[i] = start + (random * (end - start));
//            }
//
//            if (!exCache.cache.containsKey(input)) {
//                double output = system.sum(input);
//                exCache.put(input, output);
//                System.out.println("Counted & added to cache");
//            } else {
//                System.out.println("Aready exist in cache");
//            }
//        }
//
//        exCache.exportCSV(path);
//        imCache.importCSV(path);
//        imCache.exportCSV(pathTest);
    }
}
