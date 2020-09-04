package pl.clockworkjava.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.reservation.dto.ReservationDTO;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class AddNewReservationScene {

    private Scene mainScene;
    private RoomService roomService = ObjectPool.getRoomService();
    private GuestService guestService = ObjectPool.getGuestService();

    public AddNewReservationScene(Stage modalStage, TableView<ReservationDTO> tableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);

        Label fromDateLabel = new Label("Data rozpoczęcia rezerwacji:");
        DatePicker fromDateField = new DatePicker();

        gridPane.add(fromDateLabel, 0, 0);
        gridPane.add(fromDateField, 1, 0);

        Label toDateLabel = new Label("Data zakończenia rezerwacji:");
        DatePicker toDateField = new DatePicker();

        gridPane.add(toDateLabel, 0, 1);
        gridPane.add(toDateField, 1, 1);

        List<RoomDTO> allAsDTO = this.roomService.getAllAsDTO();

        List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();

        allAsDTO.forEach( dto -> {
            roomSelectionItems.add(
                    new RoomSelectionItem(dto.getNumber(), dto.getId()));
        });

        List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();

        this.guestService.getGuestsAsDTO().forEach(dto -> {
            guestSelectionItems.add(
                    new GuestSelectionItem(
                            dto.getFirstName(), dto.getLastName(), dto.getId()));
        });

        Label roomLabel = new Label("Pokój:");
        ComboBox<RoomSelectionItem> roomField = new ComboBox<>();
        roomField.getItems().addAll(roomSelectionItems);

        gridPane.add(roomLabel, 0, 2);
        gridPane.add(roomField, 1, 2);

        Label guestLabel = new Label("Rezerwujący:");
        ComboBox<GuestSelectionItem> guestField = new ComboBox<>();
        guestField.getItems().addAll(guestSelectionItems);

        gridPane.add(guestLabel, 0, 3);
        gridPane.add(guestField, 1, 3);

        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader()
                .getResource("hotelReservation.css").toExternalForm());
    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}
