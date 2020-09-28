package pl.clockworkjava.domain.room;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomTest {

    @Test
    public void toCSVTest() {

        List<BedType> beds = Arrays.asList(BedType.values());

        Room room = new Room(1, 302, beds);

        String csvTemplate = "1,302,Pojedyncze#Podwójne#Królewskie"+System.getProperty("line.separator");

        String createdCSV = room.toCSV();

        assertEquals(csvTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV");

    }

    @Test
    public void toCSVWithNullBedListTest() {
        Room room = new Room(1, 302, null);

        assertNotNull(room.getBeds());

        String csvTemplate = "1,302,"+System.getProperty("line.separator");

        String createdCSV = room.toCSV();

        assertEquals(csvTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV przy liście łózek == null");
    }
}
