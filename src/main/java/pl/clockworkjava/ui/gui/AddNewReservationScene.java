package pl.clockworkjava.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.clockworkjava.domain.reservation.dto.ReservationDTO;

public class AddNewReservationScene {

    private Scene mainScene;

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

        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader()
                .getResource("hotelReservation.css").toExternalForm());
    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}
