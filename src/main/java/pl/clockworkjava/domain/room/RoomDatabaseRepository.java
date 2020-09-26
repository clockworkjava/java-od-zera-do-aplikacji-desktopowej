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
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ROOMS");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int roomNumber = resultSet.getInt(2);
                List<BedType> beds = new ArrayList<>();
                this.rooms.add(new Room(id, roomNumber, beds));
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM BEDS");

            while (rs.next()) {
                long id = rs.getLong(2);
                String bedType = rs.getString(3);
                BedType bedTypeAsEnum = BedType.SINGLE;
                if(SystemUtils.DOUBLE_BED.equals(bedType)) {
                    bedTypeAsEnum = BedType.DOUBLE;
                } else if(SystemUtils.KING_SIZE.equals(bedType)) {
                    bedTypeAsEnum = BedType.KING_SIZE;
                }
                this.getById(id).addBed(bedTypeAsEnum);
            }

        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu danych z bazy");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {

    }

    @Override
    public Room getById(long id) {
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
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
            while (rs.next()) {
                newId = rs.getLong(1);
            }

            String insertBedTemplate = "INSERT INTO BEDS(ROOM_ID, BED) VALUES(%d, '%s')";

            for (BedType bedType : bedTypes) {
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
