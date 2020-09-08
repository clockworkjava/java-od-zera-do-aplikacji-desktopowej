package pl.clockworkjava;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.reservation.ReservationService;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.ui.gui.PrimaryStage;
import pl.clockworkjava.ui.text.TextUI;
import pl.clockworkjava.util.SystemUtils;

import java.io.IOException;

public class App extends Application {

    private static final TextUI textUI = new TextUI();
    private static final GuestService guestService = ObjectPool.getGuestService();
    private static final RoomService roomService = ObjectPool.getRoomService();
    private static final ReservationService reservationService = ObjectPool.getReservationService();

    public static void main(String[] args) {

        try {
            SystemUtils su = new SystemUtils();
            SystemUtils.createDataDirectory();
            System.out.println("Trwa ładowanie danych...");
            guestService.readAll();
            roomService.readAll();
            reservationService.readAll();
        } catch (IOException e) {
            throw new PersistenceToFileException(SystemUtils.DATA_DIRECTORY.toString(), "create", "directory");
        }
        Application.launch(args);
//        textUI.showSystemInfo();
//        textUI.showMainMenu();
    }

    @Override
    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }

    @Override
    public void stop() {
        System.out.println("Wychodzę z aplikacji. Zapisuję dane.");
        guestService.saveAll();
        roomService.saveAll();
        reservationService.saveAll();
    }


}
