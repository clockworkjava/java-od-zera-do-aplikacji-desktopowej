package pl.clockworkjava.domain.reservation;

import pl.clockworkjava.domain.guest.Guest;
import pl.clockworkjava.domain.room.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDatabaseRepository implements ReservationRepository {

    private final static ReservationRepository instance = new ReservationDatabaseRepository();
    private List<Reservation> reservations = new ArrayList<>();

    public static ReservationRepository getInstance() {
        return instance;
    }

    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public void readAll() {

    }

    @Override
    public void saveAll() {

    }

    @Override
    public List<Reservation> getAll() {
        return this.reservations;
    }

    @Override
    public void remove(long id) {

    }
}
