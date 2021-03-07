package ee.taltech.iti0200.readingfiles;

import java.io.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DataFileReader implements DataReader {
    private FileReader reader;

    @Override
    public void setFileToRead(File file) {
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> readFile() {
        List<String> newList = new LinkedList<>();
        try {
            BufferedReader text = new BufferedReader(reader);
            String line = text.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    newList.add(line);
                }
                line = text.readLine();
            }
            return newList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return newList;
    }

    @Override
    public Optional<String> readNextLine() {
        try {
            List<String> newList = readFile();
            reader = new FileReader("Example.txt");
            if (newList.size() > 0 && !newList.get(0).isEmpty()) {
                return Optional.of(newList.get(0));
            }
            return Optional.empty();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}