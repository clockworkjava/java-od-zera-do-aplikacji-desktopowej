package pl.clockworkjava.domain.room;

public class Room {

    private final int number;
    private final BedType[] beds;

    Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.beds = bedTypes;
    }

    public String getInfo() {

        StringBuilder bedInfo = new StringBuilder("Rodzaje łóżek w pokoju:\n");
        for(BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("\n");
        }

        return String.format("Dodano nowy pokoj - numer %d %s", this.number, bedInfo.toString());
    }
}
