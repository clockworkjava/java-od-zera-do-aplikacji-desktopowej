package pl.clockworkjava.ui.gui.guests;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.guest.dto.GuestDTO;
import pl.clockworkjava.domain.room.dto.RoomDTO;

public class GuestsTab {

    private Tab guestTab;
    private GuestService guestService = ObjectPool.getGuestService();

    public GuestsTab(Stage primaryStage) {
        TableView<GuestDTO> tableView = getGuestDTOTableView();

        Button btn = new Button("Dodaj nowego gościa");

        btn.setOnAction(actionEvent -> {
            Stage stg = new Stage();
            stg.initModality(Modality.WINDOW_MODAL);
            stg.initOwner(primaryStage);
            stg.setScene(new AddNewGuestScene(stg, tableView).getMainScene());
            stg.setTitle("Dodaj nowego gościa");

            stg.showAndWait();
        });

        VBox layout = new VBox(btn, tableView);

        this.guestTab = new Tab("Goście", layout);
        this.guestTab.setClosable(false);
    }

    private TableView<GuestDTO> getGuestDTOTableView() {
        TableView<GuestDTO> tableView = new TableView<>();

        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("Imię");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<GuestDTO, Integer> ageColumn = new TableColumn<>("Wiek");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Płeć");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<GuestDTO, GuestDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory( value ->new ReadOnlyObjectWrapper(value.getValue()) );

        deleteColumn.setCellFactory( param -> new TableCell<>() {

            Button deleteButton = new Button("Usuń");

            @Override
            protected void updateItem(GuestDTO value, boolean empty) {
                super.updateItem(value, empty);

                if(value==null) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction( actionEvent -> {
                        guestService.removeGuest(value.getId());
                        tableView.getItems().remove(value);
                    });
                }

            }
        });

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn,
                ageColumn, genderColumn, deleteColumn);

        tableView.getItems().addAll(guestService.getGuestsAsDTO());
        return tableView;
    }

    public Tab getGuestTab() {
        return guestTab;
    }
}
