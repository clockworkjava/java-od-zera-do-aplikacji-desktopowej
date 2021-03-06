package pl.clockworkjava.domain;

import pl.clockworkjava.domain.guest.GuestDatabaseRepository;
import pl.clockworkjava.domain.guest.GuestRepository;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.reservation.ReservationDatabaseRepository;
import pl.clockworkjava.domain.reservation.ReservationFileRepository;
import pl.clockworkjava.domain.reservation.ReservationRepository;
import pl.clockworkjava.domain.reservation.ReservationService;
import pl.clockworkjava.domain.room.RoomDatabaseRepository;
import pl.clockworkjava.domain.room.RoomRepository;
import pl.clockworkjava.domain.room.RoomService;

public class ObjectPool {

    private final static RoomService roomService = new RoomService();
    private final static ReservationService reservationService = new ReservationService();

    private ObjectPool() {

    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {

        return GuestDatabaseRepository.getInstance();
    }

    public static RoomService getRoomService() {

        return roomService;
    }

    public static RoomRepository getRoomRepository() {
//        return RoomFileRepository.getInstance();
        return RoomDatabaseRepository.getInstance();
    }

    public static ReservationService getReservationService() {

        return reservationService;
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationDatabaseRepository.getInstance();
    }
}
