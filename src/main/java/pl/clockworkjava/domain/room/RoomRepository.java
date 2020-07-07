package pl.clockworkjava.domain.room;

import pl.clockworkjava.domain.guest.Gender;
import pl.clockworkjava.domain.guest.Guest;

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

        Path file = Paths.get(
                System.getProperty("user.home"),
                "reservation_system",
                name);

        StringBuilder sb = new StringBuilder("");

        for(Room room : this.rooms) {
            sb.append(room.toCSV());
        }

        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if(!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAll() {
        String name = "rooms.csv";

        Path file = Paths.get(
                System.getProperty("user.home"),
                "reservation_system",
                name);

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
            System.out.println("Nie udało się odczytać pliku z danymi");
            e.printStackTrace();
        }

    }
}
