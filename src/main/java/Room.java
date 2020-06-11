public class Room {

    private int number;
    private BedType bedType;

    public Room(int number, BedType bed) {
        this.number = number;
        this.bedType = bed;
    }

    public String getInfo() {
        return String.format("Dodano nowy pokoj - numer %d (%s)", this.number, this.bedType);
    }
}
