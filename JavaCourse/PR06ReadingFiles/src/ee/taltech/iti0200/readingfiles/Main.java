package ee.taltech.iti0200.readingfiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static File createNewFile() {
        try {
            FileWriter writer = new FileWriter("example.txt");
            writer.write("This is an example.\n ");
            writer.close();
            return new File("example.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void deleteFile(File file) {
        file.delete();
    }

    public static void main(String[] args) throws IOException {

        File file = createNewFile();

        DataReader dataFileReader = new DataFileReader();
        dataFileReader.setFileToRead(file);
        System.out.println(dataFileReader.readNextLine());  // [This is an example., This is second line of file.]
        System.out.println(dataFileReader.readNextLine());  // [This is an example., This is second line of file.]

        deleteFile(file);

    }
}