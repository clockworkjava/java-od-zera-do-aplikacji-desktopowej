package pl.clockworkjava.ui.gui.rooms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;
import pl.clockworkjava.util.Properties;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoomScene {

    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage stg, TableView<RoomDTO> tableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();

        roomNumberField.textProperty().addListener((observableValue, oldValue, newValue) -> {

            if(!newValue.matches("\\d*")) {
                roomNumberField.setText(oldValue);
            }

        });

        gridPane.add(roomNumberLabel, 0, 0);
        gridPane.add(roomNumberField, 1, 0);

        Label bedTypeLabel = new Label("Typy łóżek:");

        Button addNewBedButton = new Button();
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("add.jpg"));
        ImageView imgView = new ImageView(icon);
        imgView.setFitWidth(16);
        imgView.setFitHeight(16);
        addNewBedButton.setGraphic(imgView);
        addNewBedButton.setPadding(Insets.EMPTY);

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBedButton, 1, 1);

        VBox bedsVerticalLayout = new VBox(getComboBox());

        addNewBedButton.setOnAction(actionEvent -> {
            bedsVerticalLayout.getChildren().add(getComboBox());
        });

        Button addNewRoomButton = new Button("Dodaj nowy pokój");
        addNewRoomButton.setOnAction(actionEvent -> {
            int newRoomNumber = Integer.parseInt(roomNumberField.getText());
            List<String> bedTypes = new ArrayList<>();

            this.comboBoxes.forEach(comboBox -> {
                bedTypes.add(comboBox.getValue());
            });

            this.roomService.createNewRoom(newRoomNumber, bedTypes);

            tableView.getItems().clear();

            List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
            tableView.getItems().addAll(allAsDTO);

            stg.close();
        });

        addNewBedButton.setPadding(new Insets(5, 5, 5, 5));
        addNewRoomButton.setPadding(new Insets(5, 5, 5, 5));

        gridPane.add(bedsVerticalLayout, 1, 2);
        gridPane.add(addNewRoomButton, 0, 3);


        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(getClass().getClassLoader()
                .getResource("hotelReservation.css").toExternalForm());
    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll(
                Properties.SINGLE_BED,
                Properties.DOUBLE_BED,
                Properties.KING_SIZE);
        bedTypeField.setValue(Properties.SINGLE_BED);
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
