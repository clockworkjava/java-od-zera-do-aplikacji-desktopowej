package pl.clockworkjava.domain.room;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    private DatabaseRoomConnector connector = new JDBCRoomConnector();

    private static RoomRepository instance = new RoomDatabaseRepository();

    public static RoomRepository getInstance() {
        return instance;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {

        this.rooms.addAll(connector.getAllRooms());
        List<Object[]> allBeds = connector.getAllBeds();

        for(Object[] touple : allBeds) {
            this.getById((long)touple[0]).addBed((BedType)touple[1]);
        }

    }

    @Override
    public void remove(long id) {
        connector.remove(id);
        this.removeById(id);
    }

    private void removeById(long id) {
        int indexToBeRemoved = -1;
        for(int i = 0; i<this.rooms.size(); i++) {
            if(this.rooms.get(i).getId()==id) {
                indexToBeRemoved = i;
            }
        }

        this.rooms.remove(indexToBeRemoved);
    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {

        connector.edit(id,number,bedTypes);

        Room roomToBeUpdated = getById(id);
        roomToBeUpdated.setNumber(number);
        roomToBeUpdated.setBeds(bedTypes);
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

        Room newRoom = connector.createNewRoom(number, bedTypes);
        this.rooms.add(newRoom);
        return newRoom;
    }

    @Override
    public List<Room> getAllRooms() {
        return this.rooms;
    }

    void setConnector(DatabaseRoomConnector connector) {
        this.connector = connector;
    }
}
