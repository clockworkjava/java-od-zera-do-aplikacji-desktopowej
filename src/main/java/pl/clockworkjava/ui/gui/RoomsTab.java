package pl.clockworkjava.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.domain.room.dto.RoomDTO;

import java.util.List;

public class RoomsTab {

    private Tab roomTab;
    private RoomService roomService = new RoomService();

    public RoomsTab() {

        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Numer");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Typy łóżek");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Ilość łóżek") ;
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        tableView.getColumns().addAll(numberColumn, bedsCountColumn, bedsColumn);

        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        tableView.getItems().addAll(allAsDTO);

        this.roomTab = new Tab("Pokoje", tableView);
        this.roomTab.setClosable(false);
    }

    Tab getRoomTab() {
        return roomTab;
    }
}
