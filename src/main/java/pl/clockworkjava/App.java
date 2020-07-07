package pl.clockworkjava;

import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.ui.text.TextUI;
import pl.clockworkjava.util.Properties;

import java.io.IOException;

public class App {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new PersistenceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }
        textUI.showSystemInfo();
        textUI.showMainMenu();
    }
}
