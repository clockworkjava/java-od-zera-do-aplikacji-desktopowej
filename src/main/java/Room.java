public class Room {

    private int number;
    private int beds;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    public String getInfo() {
        return String.format("Dodano nowy pokoj - numer %d (%d)", this.number, this.beds);
    }
}
