package pl.clockworkjava.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoomScene {

    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes= new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage stg, TableView<RoomDTO> tableView) {

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();
        HBox roomNumber = new HBox(roomNumberLabel, roomNumberField);

        Label bedTypeLabel = new Label("Typy łóżek:");

        Button addNewBedButton = new Button();

        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("add.jpg"));

        ImageView imgView = new ImageView(icon);

        imgView.setFitWidth(16);
        imgView.setFitHeight(16);

        addNewBedButton.setGraphic(imgView);

        addNewBedButton.setPadding(Insets.EMPTY);

        HBox bedTypeRow = new HBox(bedTypeLabel, addNewBedButton);

        VBox bedsVerticalLayout = new VBox(bedTypeRow, getComboBox());

        addNewBedButton.setOnAction( actionEvent -> {
            bedsVerticalLayout.getChildren().add(getComboBox());
        });

        Button addNewRoomButton = new Button("Dodaj nowy pokój");
        addNewRoomButton.setOnAction(actionEvent -> {
            int newRoomNumber = Integer.parseInt(roomNumberField.getText());
            List<String> bedTypes = new ArrayList<>();

            this.comboBoxes.forEach( comboBox -> {
                bedTypes.add(comboBox.getValue());
            });

            this.roomService.createNewRoom(newRoomNumber, bedTypes);

            tableView.getItems().clear();

            List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
            tableView.getItems().addAll(allAsDTO);

            stg.close();
        });

        VBox mainFormLayout = new VBox(roomNumber, bedsVerticalLayout, addNewRoomButton);

        this.mainScene = new Scene(mainFormLayout, 640, 480);
    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll("Pojedyncze", "Podwójne", "Królewskie");
        bedTypeField.setValue("Pojedyncze");
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
