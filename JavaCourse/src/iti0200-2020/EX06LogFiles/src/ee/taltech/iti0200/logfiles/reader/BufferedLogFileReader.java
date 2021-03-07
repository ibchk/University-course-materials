import ee.taltech.iti0200.logfiles.exception.LogFileReaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class BufferedLogFileReader implements LogFileReader {

    public static final int LINE11 = 11;
    public static final int LINE23 = 23;

    @Override
    public String readLogs(String path) {
        StringBuilder readedFile = new StringBuilder();
        BufferedReader reader;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while (line != null) {
                if (readedFile.length() == 0) {
                    readedFile = new StringBuilder(line);
                } else {
                    readedFile.append("\n").append(line);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new LogFileReaderException("File not found");
        } catch (IOException e) {
            throw new LogFileReaderException("Unable to read file");
        }
        return readedFile.toString();
    }

    @Override
    public String readLogsWithLevel(String path, String level) {
        StringBuilder readedFile = new StringBuilder();
        if (level.equals("INFO") || level.equals("WARN") || level.equals("ERROR")) {
            BufferedReader reader;
            String line = "";
            try {
                reader = new BufferedReader(new FileReader(path));
                line = reader.readLine();
                while (line != null) {
                    if (line.contains(level)) {
                        if (readedFile.length() == 0) {
                            readedFile = new StringBuilder(line);
                        } else {
                            readedFile.append("\n").append(line);
                        }
                    }
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new LogFileReaderException("File not found");
            } catch (IOException e) {
                throw new LogFileReaderException("Unable to read file");
            }
        }
        return readedFile.toString();
    }

    @Override
    public String readLogsBetween(String path, LocalDateTime from, LocalDateTime to) {
        StringBuilder readedFile = new StringBuilder();
        BufferedReader reader;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while (line != null) {
                LocalDateTime now = LocalDateTime.parse(line.substring(0, 10) + "T" + line.substring(LINE11, LINE23));
                if (from.isBefore(now) && to.isAfter(now)) {
                    if (readedFile.length() == 0) {
                        readedFile = new StringBuilder(line);
                    } else {
                        readedFile.append("\n").append(line);
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new LogFileReaderException("File not found");
        } catch (IOException e) {
            throw new LogFileReaderException("Unable to read file");
        } catch (DateTimeParseException e) {
            throw new LogFileReaderException("Unable to parse date");
        }
        return readedFile.toString();
    }
}
