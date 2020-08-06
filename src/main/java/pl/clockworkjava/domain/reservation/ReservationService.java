package pl.clockworkjava.domain.reservation;

import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.Guest;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.reservation.dto.ReservationDTO;
import pl.clockworkjava.domain.room.Room;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.util.Properties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final RoomService roomService = ObjectPool.getRoomService();
    private final static GuestService guestService = ObjectPool.getGuestService();
    private final ReservationRepository repository = ObjectPool.getReservationRepository();

    private final static ReservationService instance = new ReservationService();

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        return instance;
    }

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException {

        //TODO: handle null room
        Room room = this.roomService.getRoomById(roomId);
        //TODO: handle null guest
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);

        if (toWithTime.isBefore(fromWithTime)) {
            throw new IllegalArgumentException();
        }
        ;

        return this.repository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public List<ReservationDTO> getReservationsAsDTO() {

        List<ReservationDTO> result = new ArrayList<>();

        List<Reservation> reservations = this.repository.getAll();

        for(Reservation reservation : reservations) {
            ReservationDTO dto = reservation.getAsDTO();
            result.add(dto);
        }

        return result;
    }
}
