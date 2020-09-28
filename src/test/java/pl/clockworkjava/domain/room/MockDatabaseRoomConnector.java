package pl.clockworkjava.domain.room;

import java.util.ArrayList;
import java.util.List;

public class MockDatabaseRoomConnector implements DatabaseRoomConnector {

    @Override
    public List<Room> getAllRooms() {
        List<Room> result = new ArrayList<>();
        result.add(new Room(1l, 302, null));
        result.add(new Room(2l, 303, null));
        return result;
    }

    @Override
    public List<Object[]> getAllBeds() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{1l, BedType.DOUBLE});
        result.add(new Object[]{1l, BedType.SINGLE});
        result.add(new Object[]{2l, BedType.KING_SIZE});
        return result;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {

    }

    @Override
    public Room createNewRoom(int number, List<BedType> bedTypes) {
        return null;
    }
}
