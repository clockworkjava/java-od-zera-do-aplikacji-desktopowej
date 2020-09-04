package pl.clockworkjava.ui.gui;

public class GuestSelectionItem {

    private String firstName;
    private String lastName;
    private int id;

    public GuestSelectionItem(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
