package pl.clockworkjava.domain.room;

import java.util.Arrays;
import java.util.Collections;

public class Room {

    private final int number;
    private final BedType[] beds;

    Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.beds = bedTypes;
    }

    public String getInfo() {

        StringBuilder bedInfo = new StringBuilder("Rodzaje łóżek w pokoju:\n");
        for (BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("\n");
        }

        return String.format("Numer: %d %s", this.number, bedInfo.toString());
    }

    String toCSV() {

        String[] bedsAsString = new String[this.beds.length];

        for (int i = 0; i < this.beds.length; i++) {
            bedsAsString[i] = this.beds[i].toString();
        }

        String bedTypes = String.join("#", bedsAsString);

        return String.format("%d,%s%s", this.number, bedTypes, System.getProperty("line.separator"));

    }
}
