package pl.clockworkjava.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.clockworkjava.domain.reservation.ReservationService;
import pl.clockworkjava.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;

public class ReservationsTab {

    private Tab reservationTab;
    private ReservationService reservationService = new ReservationService();

    public ReservationsTab() {

        TableView<ReservationDTO> tableView = new TableView<>();

        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("Od");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("Do");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, Integer> roomColumn = new TableColumn<>("Pokój");
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<ReservationDTO, Integer> guestColumn = new TableColumn<>("Rezerwujący");
        guestColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));

        tableView.getColumns().addAll(fromColumn, toColumn, roomColumn, guestColumn);

        tableView.getItems().addAll(reservationService.getReservationsAsDTO());

        this.reservationTab = new Tab("Rezerwacje", tableView);
        this.reservationTab.setClosable(false);
    }

    public Tab getReservationTab() {
        return reservationTab;
    }
}
