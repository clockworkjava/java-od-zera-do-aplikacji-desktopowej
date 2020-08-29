package pl.clockworkjava.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AddNewRoomScene {

    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes= new ArrayList<>();

    public AddNewRoomScene() {

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();
        HBox roomNumber = new HBox(roomNumberLabel, roomNumberField);

        Label bedTypeLabel = new Label("Typy łóżek:");
        Button addNewBedButton = new Button("Dodaj kolejne łóżko");

        HBox bedTypeRow = new HBox(bedTypeLabel, addNewBedButton);

        VBox bedsVerticalLayout = new VBox(bedTypeRow, getComboBox());

        addNewBedButton.setOnAction( actionEvent -> {
            bedsVerticalLayout.getChildren().add(getComboBox());
        });


        VBox mainFormLayout = new VBox(roomNumber, bedsVerticalLayout);

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
