package pl.clockworkjava.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;

import java.util.List;

public class RoomsTab {

    private Tab roomTab;
    private RoomService roomService = ObjectPool.getRoomService();

    public RoomsTab(Stage primaryStage) {

        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Numer");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Typy łóżek");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Ilość łóżek") ;
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Rozmiar pokoju") ;
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        tableView.getColumns().addAll(numberColumn, roomSizeColumn, bedsCountColumn, bedsColumn);

        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);

        Button btn = new Button("Dodaj nowy");

        btn.setOnAction(actionEvent -> {
            Stage stg = new Stage();
            stg.initModality(Modality.WINDOW_MODAL);
            stg.initOwner(primaryStage);

            Label numberLabel = new Label("Numer");
            TextField numberField = new TextField();

            HBox roomNumber = new HBox(numberLabel, numberField);
            Scene newRoomScene = new Scene(roomNumber, 740, 580);

            stg.setScene(newRoomScene);
            stg.setTitle("Dodaj nowy pokój");

            stg.showAndWait();
        });

        VBox layout = new VBox(btn, tableView);

        this.roomTab = new Tab("Pokoje", layout);
        this.roomTab.setClosable(false);
    }

    Tab getRoomTab() {
        return roomTab;
    }
}
