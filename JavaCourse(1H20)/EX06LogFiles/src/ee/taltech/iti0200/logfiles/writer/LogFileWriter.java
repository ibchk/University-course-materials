import ee.taltech.iti0200.logfiles.exception.LogFileWriterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogFileWriter {

    /**
     * Write provided logs content to file.
     *
     * @param path    file saving path
     * @param content the content to write
     */
    public void writeLogs(String path, String content) {
        try {
            Files.deleteIfExists(Paths.get(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new LogFileWriterException("Unable to write file");
        }
    }
}
