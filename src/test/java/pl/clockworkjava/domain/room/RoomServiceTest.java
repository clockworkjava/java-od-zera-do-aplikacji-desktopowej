package pl.clockworkjava.domain.room;

import javafx.beans.property.ReadOnlyMapProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.reservation.Reservation;
import pl.clockworkjava.domain.reservation.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomServiceTest {

    @Test
    public void convertFromIntOptionsIntoBedTypesTest() {

        //given
        RoomService roomService = ObjectPool.getRoomService();
        int[] bedTypeOptions = new int[]{1, 2, 3};

        //when
        List<BedType> bedTypes = roomService.getBedTypes(bedTypeOptions);

        //then
        assertEquals(3, bedTypes.size());
        assertEquals(BedType.SINGLE, bedTypes.get(0));
        assertEquals(BedType.DOUBLE, bedTypes.get(1));
        assertEquals(BedType.KING_SIZE, bedTypes.get(2));

    }

    @Test
    public void getAvailableRoomsShouldThrowExceptionWhenNullParam() {

        //given
        RoomService roomService = new RoomService();

        //when, then
        assertThrows(IllegalArgumentException.class,
                () -> roomService.getAvailableRooms(null, null));
    }

    @Test
    public void getAvailableRoomsShouldThrowExceptionWhenToDateIsBeforeFrom() {
        //given
        RoomService roomService = new RoomService();
        LocalDate from = LocalDate.parse("2020-12-03");
        LocalDate to = LocalDate.parse("2020-11-30");

        //when, then
        assertThrows(IllegalArgumentException.class, () -> {
            roomService.getAvailableRooms(from, to);
        });
    }

    @Test
    public void getAvailableRoomsShouldReturnEmptyListWhenNoRoomsInSystem() {

        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.getAllRooms()).thenReturn(new ArrayList<>());
        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);

        LocalDate from = LocalDate.parse("2020-11-30");
        LocalDate to = LocalDate.parse("2020-12-03");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(0, availableRooms.size());
    }

    @Test
    public void getAvailableRoomsShouldReturnAllRoomsWhenNoReservations() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room room = new Room(1, 302, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        ReservationService reservationService = mock(ReservationService.class);
        when(reservationService.getAllReservations()).thenReturn(new ArrayList<>());

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-11-30");
        LocalDate to = LocalDate.parse("2020-12-03");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
    }

}
