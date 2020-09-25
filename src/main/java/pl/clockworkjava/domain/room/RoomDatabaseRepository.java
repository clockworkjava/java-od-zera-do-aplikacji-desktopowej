package pl.clockworkjava.domain.room;

import pl.clockworkjava.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    private static RoomRepository instance = new RoomDatabaseRepository();

    public static RoomRepository getInstance() {
        return instance;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {

    }

    @Override
    public Room getById(long id) {
        return null;
    }

    @Override
    public Room createNewRoom(int number, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String insertRoomTemplate = "INSERT INTO ROOMS(ROOM_NUMBER) VALUES(%d)";
            String query = String.format(insertRoomTemplate, number);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();

            long newId = -1;
            while(rs.next()) {
                newId = rs.getLong(1);
            }

            String insertBedTemplate = "INSERT INTO BEDS(ROOM_ID, BED) VALUES(%d, '%s')";

            for(BedType bedType : bedTypes) {
                statement.execute(String.format(insertBedTemplate, newId, bedType.toString()));
            }

            statement.close();

            Room newRoom = new Room(newId, number, bedTypes);
            this.rooms.add(newRoom);
            return newRoom;

        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu nowego pokoju");
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public List<Room> getAllRooms() {
        return this.rooms;
    }
}
