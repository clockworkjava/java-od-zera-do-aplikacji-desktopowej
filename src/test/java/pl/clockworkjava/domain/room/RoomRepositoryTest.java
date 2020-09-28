package pl.clockworkjava.domain.room;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomRepositoryTest {

    @Test
    public void getAllRoomsTest() {

        //given
        RoomDatabaseRepository repository = new RoomDatabaseRepository();
        repository.setConnector(new MockDatabaseRoomConnector());

        //when
        repository.readAll();
        List<Room> allRooms = repository.getAllRooms();

        //then
        assertEquals(2, allRooms.size());
        assertEquals(2, allRooms.get(0).getBeds().size());
        assertEquals(1, allRooms.get(1).getBeds().size());
    }

    @Test
    public void removeByIdTest() {
        //given
        RoomDatabaseRepository repository = new RoomDatabaseRepository();
        repository.setConnector(new MockDatabaseRoomConnector());

        //when
        repository.readAll();
        List<Room> allRooms = repository.getAllRooms();
        repository.remove(1);

        //then
        assertEquals(1, allRooms.size());
        assertEquals(2l, allRooms.get(0).getId());
    }
}
