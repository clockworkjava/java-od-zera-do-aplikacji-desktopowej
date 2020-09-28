package pl.clockworkjava.domain.room;

import org.junit.jupiter.api.Test;
import pl.clockworkjava.domain.room.dto.RoomDTO;

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

    @Test
    public void toDTOTest() {

        List<BedType> beds = Arrays.asList(BedType.values());

        Room room = new Room(1, 302, beds);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(),1);
        assertEquals(roomDTO.getNumber(), 302);
        assertEquals(roomDTO.getBedsCount(), 3);
        assertEquals(roomDTO.getRoomSize(), 5);
        assertEquals(roomDTO.getBeds(), "Pojedyncze,Podwójne,Królewskie");
    }

    @Test
    public void toDTOFromRoomWithNullBedsListTest() {

        Room room = new Room(1, 302, null);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(),1);
        assertEquals(roomDTO.getNumber(), 302);
        assertEquals(roomDTO.getBedsCount(), 0);
        assertEquals(roomDTO.getRoomSize(), 0);
        assertEquals(roomDTO.getBeds(), "");
    }
}
