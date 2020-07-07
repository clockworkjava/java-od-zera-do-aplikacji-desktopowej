package pl.clockworkjava.domain.room;

import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    Room createNewRoom(int number, BedType[] bedTypes) {
        Room newRoom = new Room(number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    List<Room> getAllRooms() {
        return this.rooms;
    }

    public void saveAll() {
        String name = "rooms.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");

        for(Room room : this.rooms) {
            sb.append(room.toCSV());
        }

        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "save", "room data");
        }
    }

    public void readAll() {
        String name = "rooms.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));

            for(String guestAsString : roomsAsString) {
                String[] roomData = guestAsString.split(",");
                int number = Integer.parseInt(roomData[0]);
                String bedTypesData = roomData[1];
                String[] bedsTypesAsString =  bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedsTypesAsString.length];
                for(int i=0;i<bedTypes.length;i++) {
                    bedTypes[i]=BedType.valueOf(bedsTypesAsString[i]);
                }
                createNewRoom(number,bedTypes);
            }

        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "room data");
        }

    }
}
