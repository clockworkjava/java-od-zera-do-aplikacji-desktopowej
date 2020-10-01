package pl.clockworkjava.domain.room;

import org.junit.jupiter.api.Test;
import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.Gender;
import pl.clockworkjava.domain.guest.Guest;
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

    @Test
    public void getAvailableRoomsShouldRemoveRoomIfReservationStartDateIsTheSame() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-11-30T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-01T09:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-11-30");
        LocalDate to = LocalDate.parse("2020-12-03");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }

    @Test
    public void getAvailableRoomsShouldRemoveRoomIfReservationEndDateIsTheSame() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-11-30T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-01T10:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-11-20");
        LocalDate to = LocalDate.parse("2020-12-01");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }

    @Test
    public void getAvailableRoomsShouldRemoveRoomIfReservationStartDateIsBetween() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-12-01T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-02T10:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-11-20");
        LocalDate to = LocalDate.parse("2020-12-05");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }

    @Test
    public void getAvailableRoomsShouldRemoveRoomIfReservationEndDateIsBetween() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-12-01T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-05T10:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-12-04");
        LocalDate to = LocalDate.parse("2020-12-06");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }

    @Test
    public void getAvailableRoomsShouldRemoveRoomIfLookingBetweenExistingReservation() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-12-01T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-10T10:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-12-04");
        LocalDate to = LocalDate.parse("2020-12-06");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }

    @Test
    public void getAvailableRoomsTest() {
        //given
        RoomRepository roomRepository = mock(RoomRepository.class);
        Room reservedRoom = new Room(1, 302, null);
        Room availableRoom = new Room(2, 101, null);
        Room room = new Room(3, 204, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(reservedRoom);
        rooms.add(availableRoom);
        rooms.add(room);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        Guest guest = new Guest(1, "Paweł", "Cwik", 33, Gender.MALE);

        LocalDateTime fromReserved = LocalDateTime.parse("2020-12-01T15:00");
        LocalDateTime toReserved = LocalDateTime.parse("2020-12-10T10:00");

        LocalDateTime fromReserved2 = LocalDateTime.parse("2020-12-05T15:00");
        LocalDateTime toReserved2 = LocalDateTime.parse("2020-12-06T10:00");

        Reservation reservation = new Reservation(1, reservedRoom, guest, fromReserved, toReserved);
        Reservation reservation2 = new Reservation(2, room, guest, fromReserved2, toReserved2);

        ReservationService reservationService = mock(ReservationService.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation2);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setReservationService(reservationService);

        LocalDate from = LocalDate.parse("2020-12-04");
        LocalDate to = LocalDate.parse("2020-12-06");

        //when
        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        //then
        assertEquals(1, availableRooms.size());
        assertEquals(101, availableRooms.get(0).getNumber());
    }


}
