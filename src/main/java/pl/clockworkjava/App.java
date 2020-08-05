package pl.clockworkjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.clockworkjava.exceptions.PersistenceToFileException;
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
        String hotelName = Properties.HOTEL_NAME;
        int systemVersion = Properties.SYSTEM_VERSION;
        Label l = new Label("Hello JavaFX!");
        Scene scene = new Scene(l, 640, 480);
        String title = String.format("System rezerwacji hotelu %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
