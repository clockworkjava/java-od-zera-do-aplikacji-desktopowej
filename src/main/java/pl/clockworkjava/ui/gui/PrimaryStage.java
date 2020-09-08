package pl.clockworkjava.ui.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.clockworkjava.util.SystemUtils;

public class PrimaryStage {

    public void initialize(Stage primaryStage) {
        String hotelName = SystemUtils.HOTEL_NAME;
        String systemVersion = SystemUtils.SYSTEM_VERSION;

        MainTabView mainTabView = new MainTabView(primaryStage);

        Scene scene = new Scene(mainTabView.getMainTabs(), 1040, 780);
        scene.getStylesheets()
                .add(getClass().getClassLoader()
                        .getResource("hotelReservation.css")
                        .toExternalForm());
        String title = String.format("System rezerwacji hotelu %s (%s)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
