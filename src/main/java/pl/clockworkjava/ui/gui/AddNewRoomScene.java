package pl.clockworkjava.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddNewRoomScene {

    private Scene mainScene;

    public AddNewRoomScene() {

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();
        HBox roomNumber = new HBox(roomNumberLabel, roomNumberField);

        Label bedTypeLabel = new Label("Typ łóżka:");
        ComboBox bedTypeField = new ComboBox();
        bedTypeField.getItems().addAll("Pojedyncze", "Podwójne", "Królewskie");
        bedTypeField.setValue("Pojedyncze");
        HBox bedType = new HBox(bedTypeLabel, bedTypeField);

        VBox vbox = new VBox(roomNumber, bedType);

        this.mainScene = new Scene(vbox, 640, 480);
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
