package pl.clockworkjava;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.ui.gui.PrimaryStage;
import pl.clockworkjava.ui.text.TextUI;
import pl.clockworkjava.util.Properties;

import java.io.IOException;

public class App extends Application {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new PersistenceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }
        Application.launch(args);
//        textUI.showSystemInfo();
//        textUI.showMainMenu();
    }

    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }
}
