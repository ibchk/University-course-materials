package ee.taltech.iti0200.readingfiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class DataScanner implements DataReader {
    private Scanner scanner;

    @Override
    public void setFileToRead(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readFile() {
        List<String> newList = new LinkedList<>();
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (!line.equals("")) {
                newList.add(line);
            }
        }
        return newList;
    }

    @Override
    public Optional<String> readNextLine() {
        String line;
        if (scanner.hasNext() && (line = scanner.nextLine()) != null && !line.equals("")) {
            return Optional.of(line);
        }
        return Optional.empty();
    }
}