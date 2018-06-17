package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExtendedSystemCache extends SystemCache implements Serializable {

    public ExtendedSystemCache() {
    }

    public ExtendedSystemCache(ExtendedSystemCache cache) {
        this.cache = cache.cache;
    }

//WRITE METHODS:
    public void writeCSV(PrintWriter out) throws IOException {

        for (Map.Entry<Parameter, Double> entry : this.cache.entrySet()) {
            Parameter input = entry.getKey();
            double output = entry.getValue();

            for (double i : input.values) {
                out.write(Double.toString(i));
                out.write(",");
            }
            out.write(Double.toString(output));
            out.write("\n");
        }
        //out.close();
    }

    public void exportCSV(String path) throws IOException {
        try (PrintWriter out = new PrintWriter(path);) {
            this.writeCSV(out);
        }

    }

    public void exportCSV(File file) throws IOException {

        PrintWriter out = new PrintWriter(file);
        this.writeCSV(out);

    }

    public void exportCSV(OutputStream stream) throws IOException {

        PrintWriter out = new PrintWriter(stream);
        this.writeCSV(out);
    }

//READ METHODS:
    public void readCSV(Scanner scan) throws IOException {
        String str;
        String arrStr[];

        while (scan.hasNext()) {
            str = scan.nextLine();
            arrStr = str.split(",");
            double input[] = new double[arrStr.length - 1];

            for (int i = 0; i < arrStr.length - 1; ++i) {
                input[i] = Double.parseDouble(arrStr[i]);
            }

            this.put(input, Double.parseDouble(arrStr[arrStr.length - 1]));

        }
        scan.close();

    }

    public void importCSV(String path) throws IOException {
        FileReader fr = new FileReader(path);
        Scanner scan = new Scanner(fr);
        this.readCSV(scan);

    }

    public void importCSV(File file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(file);
        this.readCSV(scan);

    }

    public void importCSV(InputStream stream) throws IOException {
        Scanner scan = new Scanner(stream);
        this.readCSV(scan);

    }

//SERIALIZE METHODS:
    public void serializeObj(FileOutputStream fs) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(fs)) {
            outputStream.writeObject(this.cache);
            outputStream.close();
            fs.close();
        }
    }

    public void serialize(String path) throws IOException {
        FileOutputStream fs = new FileOutputStream(path);
        this.serializeObj(fs);
    }

    public void serialize(File file) throws IOException {
        FileOutputStream fs = new FileOutputStream(file);
        this.serializeObj(fs);

    }

    public void serialize(OutputStream stream) throws IOException {
        this.serializeObj((FileOutputStream) stream);
    }

//DESERIALIZE METHODS:
    public void deserializeObj(FileInputStream fs) throws IOException, ClassNotFoundException {
        HashMap<Parameter, Double> tmp;
        try (ObjectInputStream inputStream = new ObjectInputStream(fs)) {
            tmp = (HashMap<Parameter, Double>) inputStream.readObject();
            this.cache = tmp;
            inputStream.close();
            fs.close();
        }

    }

    public void deserialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(path);
        deserializeObj(fs);

    }

    public void deserialize(File file) throws IOException, ClassNotFoundException {

        FileInputStream fs = new FileInputStream(file);
        deserializeObj(fs);
    }

    public void deserialize(InputStream stream) throws IOException, ClassNotFoundException {
        deserializeObj((FileInputStream) stream);
    }

}
