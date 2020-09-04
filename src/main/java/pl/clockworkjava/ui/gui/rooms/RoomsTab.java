package pl.clockworkjava.ui.gui.rooms;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;
import pl.clockworkjava.ui.gui.rooms.AddNewRoomScene;

import java.util.List;

public class RoomsTab {

    private Tab roomTab;
    private RoomService roomService = ObjectPool.getRoomService();
    private VBox layout;

    public RoomsTab(Stage primaryStage) {

        TableView<RoomDTO> tableView = getRoomDTOTableView();

        Button btn = new Button("Dodaj nowy");

        btn.setOnAction(actionEvent -> {
            Stage stg = new Stage();
            stg.initModality(Modality.WINDOW_MODAL);
            stg.initOwner(primaryStage);
            stg.setScene(new AddNewRoomScene(stg, tableView).getMainScene());
            stg.setTitle("Dodaj nowy pokój");

            stg.showAndWait();
        });

        this.layout = new VBox(btn, tableView);

        this.roomTab = new Tab("Pokoje", layout);
        this.roomTab.setClosable(false);
    }

    private TableView<RoomDTO> getRoomDTOTableView() {
        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Numer");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Typy łóżek");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Ilość łóżek") ;
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Rozmiar pokoju") ;
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        TableColumn<RoomDTO, RoomDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory( value ->new ReadOnlyObjectWrapper(value.getValue()) );

        deleteColumn.setCellFactory( param -> new TableCell<>() {

            Button deleteButton = new Button("Usuń");

            @Override
            protected void updateItem(RoomDTO value, boolean empty) {
                super.updateItem(value, empty);

                if(value==null) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction( actionEvent -> {
                            roomService.removeRoom(value.getId());
                            tableView.getItems().remove(value);
                    });
                }

            }


        });

        tableView.getColumns().addAll(numberColumn,
                roomSizeColumn, bedsCountColumn, bedsColumn, deleteColumn);

        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);
        return tableView;
    }

    public Tab getRoomTab() {
        return roomTab;
    }
}
