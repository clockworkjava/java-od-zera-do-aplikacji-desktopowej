public class Room {

    private int number;
    private BedType[] bedType;

    public Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.bedType = bedTypes;
    }

    public String getInfo() {
        return String.format("Dodano nowy pokoj - numer %d (%s)", this.number, this.bedType);
    }
}
