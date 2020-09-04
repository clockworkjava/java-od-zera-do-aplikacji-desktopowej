package pl.clockworkjava.ui.gui;

public class RoomSelectionItem {

    private int number;
    private int id;

    public RoomSelectionItem(int number, int id) {
        this.number = number;
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("%d", this.number);
    }
}
