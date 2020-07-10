package pl.clockworkjava.domain.reservation;

import pl.clockworkjava.domain.guest.Guest;
import pl.clockworkjava.domain.room.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    List<Reservation> reservations = new ArrayList<>();

    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(room, guest, from, to);
        this.reservations.add(res);
        return res;
    }
}
