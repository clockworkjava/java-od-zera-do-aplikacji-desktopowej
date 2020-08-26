package pl.clockworkjava.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainTabView {

    private TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        RoomsTab roomsTab = new RoomsTab(primaryStage);
        ReservationsTab reservationTab = new ReservationsTab();
        GuestsTab guestsTab = new GuestsTab();

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }
}
