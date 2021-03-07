import ee.taltech.iti0200.logfiles.exception.LogFileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FilesLogFileReader implements LogFileReader {

    public static final int LINE11 = 11;
    public static final int LINE23 = 23;

    @Override
    public String readLogs(String path) {
        StringBuilder lines = new StringBuilder();
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                if (lines.length() == 0) {
                    lines.append(line);
                } else {
                    lines.append("\n").append(line);
                }
            }
        } catch (NoSuchFileException e) {
            throw new LogFileReaderException("File not found");
        } catch (IOException e) {
            throw new LogFileReaderException("Unable to read file");
        }
        return lines.toString();
    }

    @Override
    public String readLogsWithLevel(String path, String level) {
        StringBuilder lines = new StringBuilder();
        if (level.equals("INFO") || level.equals("WARN") || level.equals("ERROR")) {
            try {
                for (String line : Files.readAllLines(Paths.get(path))) {
                    if (line.contains(level)) {
                        if (lines.length() == 0) {
                            lines.append(line);
                        } else {
                            lines.append("\n").append(line);
                        }
                    }
                }
            } catch (NoSuchFileException e) {
                throw new LogFileReaderException("File not found");
            } catch (IOException e) {
                throw new LogFileReaderException("Unable to read file");
            }
        }
        return lines.toString();
    }

    @Override
    public String readLogsBetween(String path, LocalDateTime from, LocalDateTime to) {
        StringBuilder lines = new StringBuilder();
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                LocalDateTime now = LocalDateTime.parse(line.substring(0, 10) + "T" + line.substring(LINE11, LINE23));
                if (from.isBefore(now) && to.isAfter(now)) {
                    if (lines.length() == 0) {
                        lines.append(line);
                    } else {
                        lines.append("\n").append(line);
                    }
                }
            }
        } catch (NoSuchFileException e) {
            throw new LogFileReaderException("File not found");
        } catch (IOException e) {
            throw new LogFileReaderException("Unable to read file");
        } catch (DateTimeParseException e) {
            throw new LogFileReaderException("Unable to parse date");
        }
        return lines.toString();
    }
}
