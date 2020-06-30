package pl.clockworkjava.domain.room;

public class Room {

    private int number;
    private BedType[] beds;

    Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.beds = bedTypes;
    }

    public String getInfo() {

        String bedInfo = "Rodzaje łóżek w pokoju:\n";
        for(BedType bed : beds) {
            bedInfo = bedInfo + "\t" + bed + "\n";
        }

        return String.format("Dodano nowy pokoj - numer %d %s", this.number, bedInfo);
    }
}
