import LogFileReaderException;
import LogFileWriterException;
import LogFileWriter;


import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogFileReaderTest {

    private final BufferedLogFileReader bufferedLogFileReader = new BufferedLogFileReader();
    private final FilesLogFileReader filesLogFileReader = new FilesLogFileReader();
    private final LogFileWriter logFileWriter = new LogFileWriter();

    @org.junit.jupiter.api.Test
    void readLogs() {
        logFileWriter.writeLogs("file.txt", "This gets saved in file.txt");
        assertEquals("This gets saved in file.txt", bufferedLogFileReader.readLogs("file.txt"));
        assertEquals("This gets saved in file.txt", filesLogFileReader.readLogs("file.txt"));
        boolean thrown = false;
        try {
            filesLogFileReader.readLogs("fileNO.txt");
        } catch (LogFileReaderException e) {
            thrown = true;
        }
        assertTrue(thrown);
        boolean thrown2 = false;
        try {
            filesLogFileReader.readLogs("fileNO2.txt");
        } catch (LogFileReaderException e) {
            thrown2 = true;
        }
        assertTrue(thrown2);
    }

    @org.junit.jupiter.api.Test
    void readLogsWithLevel() throws IOException {
        logFileWriter.writeLogs("fileEmpty.txt", "");
        logFileWriter.writeLogs("file2.txt", "This gets saved in file.txt");
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                bufferedLogFileReader.readLogsWithLevel("file2.txt", "WARN"));
        logFileWriter.writeLogs("file3.txt", "This gets saved in file.txt WARN");
        assertEquals("This gets saved in file.txt WARN",
                bufferedLogFileReader.readLogsWithLevel("file3.txt", "WARN"));
        logFileWriter.writeLogs("file4.txt", "2019-01-16 17:48:01.049 INFO 60674 "
                + "--- [nio-8080-exec-1] com.Application : This is an info message\n INFO 60674 ---");
        assertEquals("2019-01-16 17:48:01.049 INFO 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message\n INFO 60674 ---",
                bufferedLogFileReader.readLogsWithLevel("file4.txt", "INFO"));
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                bufferedLogFileReader.readLogsWithLevel("file4.txt", "WARN"));
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                bufferedLogFileReader.readLogsWithLevel("file4.txt", "ERROR"));
        logFileWriter.writeLogs("file5.txt", "2019-01-16 17:48:01.049 WARN"
                + " 60674 --- [nio-8080-exec-1] com.Application : This is an info message");
        assertEquals("2019-01-16 17:48:01.049 WARN 60674 --- [nio-8080-exec-1] com.Application "
                        + ": This is an info message",
                bufferedLogFileReader.readLogsWithLevel("file5.txt", "WARN"));
        logFileWriter.writeLogs("file6.txt", "2019-01-16 17:48:01.049 ERROR 60674 ---"
                + " [nio-8080-exec-1] com.Application : This is an info message\n2019-01-16 17:48:0"
                + "1.049 WARN 60674 --- [nio-8080-exec-1] com.Application : This is an info message\nTHis is wrong");
        assertEquals("2019-01-16 17:48:01.049 ERROR 60674 --- [nio-8080-exec-1] com.Application"
                        + " : This is an info message",
                bufferedLogFileReader.readLogsWithLevel("file6.txt", "ERROR"));

        logFileWriter.writeLogs("file7.txt", "This gets saved in file.txt");
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                filesLogFileReader.readLogsWithLevel("file7.txt", "WARN"));
        logFileWriter.writeLogs("file8.txt", "This gets saved in file.txt WARN");
        assertEquals("This gets saved in file.txt WARN",
                filesLogFileReader.readLogsWithLevel("file8.txt", "WARN"));
        logFileWriter.writeLogs("file9.txt", "2019-01-16 17:48:01.049 INFO 60674 --- "
                + "[nio-8080-exec-1] com.Application : This is an info message\n INFO 60674 ---");
        assertEquals("2019-01-16 17:48:01.049 INFO 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message\n INFO 60674 ---",
                filesLogFileReader.readLogsWithLevel("file9.txt", "INFO"));
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                filesLogFileReader.readLogsWithLevel("file9.txt", "WARN"));
        assertEquals(bufferedLogFileReader.readLogs("fileEmpty.txt"),
                filesLogFileReader.readLogsWithLevel("file9.txt", "ERROR"));
        logFileWriter.writeLogs("file10.txt", "2019-01-16 17:48:01.049 WARN 60674 -"
                + "-- [nio-8080-exec-1] com.Application : This is an info message");
        assertEquals("2019-01-16 17:48:01.049 WARN 60674 --- [nio-8080-exec-1] com.Application "
                        + ": This is an info message",
                filesLogFileReader.readLogsWithLevel("file10.txt", "WARN"));
        logFileWriter.writeLogs("file11.txt", "2019-01-16 17:48:01.049 ERROR 60674 ---"
                + " [nio-8080-exec-1] com.Application : This is an info message\n2019-01-16 17:48:01.049 "
                + "WARN 60674 --- [nio-8080-exec-1] com.Application : This is an info message\nTHis is wrong");
        assertEquals("2019-01-16 17:48:01.049 ERROR 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message",
                filesLogFileReader.readLogsWithLevel("file11.txt", "ERROR"));

        boolean thrown = false;
        try {
            filesLogFileReader.readLogsWithLevel("fileNO2.txt", "WARN");
        } catch (LogFileReaderException e) {
            thrown = true;
        }
        assertTrue(thrown);

        boolean thrown2 = false;
        try {
            bufferedLogFileReader.readLogsWithLevel("fileNO2.txt", "WARN");
        } catch (LogFileReaderException e) {
            thrown2 = true;
        }
        assertTrue(thrown2);

    }

    @org.junit.jupiter.api.Test
    void readLogsBetween() {
        logFileWriter.writeLogs("log_file.txt",
                "2019-01-16 17:48:01.049 INFO 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message\n2019-01-16 17:38:01.049 INFO 60674 --- [nio-8080-exec-1] com."
                        + "Application : This is an info message2");
        assertEquals("2019-01-16 17:48:01.049 INFO 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message\n2019-01-16 17:38:01.049 INFO 60674 --- [nio-8080-exec-1] com.A"
                        + "pplication : This is an info message2",
                bufferedLogFileReader.readLogsBetween("log_file.txt", LocalDateTime.MIN, LocalDateTime.MAX));
        assertEquals("2019-01-16 17:48:01.049 INFO 60674 --- [nio-8080-exec-1] com.Application : "
                        + "This is an info message\n2019-01-16 17:38:01.049 INFO 60674 --- [nio-8080-exec-1] com."
                        + "Application : This is an info message2",
                filesLogFileReader.readLogsBetween("log_file.txt", LocalDateTime.MIN, LocalDateTime.MAX));
        boolean thrown = false;
        try {
            filesLogFileReader.readLogsBetween("fileNO.txt", LocalDateTime.MIN, LocalDateTime.MAX);
        } catch (LogFileReaderException e) {
            thrown = true;
        }
        assertTrue(thrown);
        boolean thrown2 = false;
        try {
            bufferedLogFileReader.readLogsBetween("fileNO2.txt", LocalDateTime.MIN, LocalDateTime.MAX);
        } catch (LogFileReaderException e) {
            thrown2 = true;
        }
        assertTrue(thrown2);

        boolean thrown3 = false;
        try {
            logFileWriter.writeLogs("CON.txt", "fileNO2.txt");
        } catch (LogFileWriterException e) {
            thrown3 = true;
        }
        assertTrue(thrown3);
    }
}
