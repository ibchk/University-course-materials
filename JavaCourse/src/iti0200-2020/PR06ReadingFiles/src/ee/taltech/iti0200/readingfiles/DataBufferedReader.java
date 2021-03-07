package ee.taltech.iti0200.readingfiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DataBufferedReader implements DataReader {
    private BufferedReader reader;

    @Override
    public void setFileToRead(File file) {
        try {
            reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> readFile() {
        List<String> newList = new LinkedList<>();
        String line;
        try {
            line = reader.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    newList.add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newList;
    }

    @Override
    public Optional<String> readNextLine() {
        String line;
        try {
            if ((line = reader.readLine()) != null && !line.equals("")) {
                return Optional.of(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
