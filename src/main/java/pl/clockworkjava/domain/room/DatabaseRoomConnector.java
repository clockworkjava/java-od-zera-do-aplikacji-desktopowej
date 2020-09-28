package pl.clockworkjava.domain.room;

import java.util.List;

public interface DatabaseRoomConnector {
    List<Room> getAllRooms();

    List<Object[]> getAllBeds();

    void remove(long id);

    void edit(long id, int number, List<BedType> bedTypes);

    Room createNewRoom(int number, List<BedType> bedTypes);
}
