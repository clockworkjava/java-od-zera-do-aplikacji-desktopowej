package pl.clockworkjava.ui.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.clockworkjava.util.Properties;

public class PrimaryStage {

    public void initialize(Stage primaryStage) {
        String hotelName = Properties.HOTEL_NAME;
        int systemVersion = Properties.SYSTEM_VERSION;

        MainTabView mainTabView = new MainTabView();

        Scene scene = new Scene(mainTabView.getMainTabs(), 640, 480);
        String title = String.format("System rezerwacji hotelu %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
