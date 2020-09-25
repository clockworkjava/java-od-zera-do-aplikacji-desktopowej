package pl.clockworkjava.domain.room;

import java.util.List;

public interface RoomRepository {

    void saveAll();

    void readAll();

    void remove(long id);

    void edit(long id, int number, List<BedType> bedTypes);

    Room getById(long id);

    Room createNewRoom(int number, List<BedType> bedTypes);

    List<Room> getAllRooms();
}
