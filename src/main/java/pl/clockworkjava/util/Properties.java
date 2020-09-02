package pl.clockworkjava.util;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public class Properties {

    public static final String HOTEL_NAME = "Overlook";
    public static final int SYSTEM_VERSION = 1;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;

    public static final String SINGLE_BED = "Pojedyncze";
    public static final String DOUBLE_BED = "Podwójne";
    public static final String KING_SIZE = "Królewskie";

    public static final Path DATA_DIRECTORY = Paths.get(
            System.getProperty("user.home"),
            "reservation_system");

    public static void createDataDirectory() throws IOException {
        if(!Files.isDirectory(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
    }
}
