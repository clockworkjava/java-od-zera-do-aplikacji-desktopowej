package pl.clockworkjava.domain;

import pl.clockworkjava.domain.guest.GuestRepository;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.reservation.ReservationRepository;
import pl.clockworkjava.domain.reservation.ReservationService;
import pl.clockworkjava.domain.room.RoomRepository;
import pl.clockworkjava.domain.room.RoomService;

public class ObjectPool {

    private ObjectPool() {

    }

    private static GuestService guestService = GuestService.getInstance();
    private static GuestRepository guestRepository = GuestRepository.getInstance();

    private static RoomService roomService = RoomService.getInstance();
    private static RoomRepository roomRepository = RoomRepository.getInstance();

    private static ReservationService reservationService = ReservationService.getInstance();
    private static ReservationRepository reservationRepository = ReservationRepository.getInstance();

    public static GuestService getGuestService() {
        return guestService;
    }

    public static GuestRepository getGuestRepository() {
        return guestRepository;
    }

    public static RoomService getRoomService() {
        return roomService;
    }

    public static RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public static ReservationRepository getReservationRepository() {
        return reservationRepository;
    }
}
