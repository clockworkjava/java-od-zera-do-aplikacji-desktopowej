package pl.clockworkjava.ui.gui.reservations;

public class RoomSelectionItem {

    private int number;
    private long id;

    public RoomSelectionItem(int number, long id) {
        this.number = number;
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return String.format("%d", this.number);
    }
}
