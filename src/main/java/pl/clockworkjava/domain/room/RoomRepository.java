package pl.clockworkjava.domain.room;

import java.util.List;

public interface RoomRepository {

    void saveAll();

    void readAll();

    void remove(int id);

    void edit(int id, int number, List<BedType> bedTypes);

    Room getById(int id);

    Room createNewRoom(int number, List<BedType> bedTypes);

    List<Room> getAllRooms();
}
