package pl.clockworkjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.ui.text.TextUI;
import pl.clockworkjava.util.Properties;

import javax.swing.plaf.TabbedPaneUI;
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

        TabPane tabPane = new TabPane();

        Tab reservationTab = new Tab("Rezerwacje", new Label("Obsługa rezerwacji"));
        Tab guestTab = new Tab("Goście", new Label("Obsługa gośći"));
        Tab roomTab = new Tab("Pokoje", new Label("Obsługa pokoi"));

        reservationTab.setClosable(false);
        guestTab.setClosable(false);
        roomTab.setClosable(false);

        tabPane.getTabs().addAll(reservationTab, guestTab, roomTab);

        Scene scene = new Scene(tabPane, 640, 480);
        String title = String.format("System rezerwacji hotelu %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
