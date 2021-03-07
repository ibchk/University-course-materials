package ee.taltech.iti0200.readingfiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DataReader {
    void setFileToRead(File file) throws FileNotFoundException;

    List<String> readFile() throws IOException;

    Optional<String> readNextLine() throws IOException;
}