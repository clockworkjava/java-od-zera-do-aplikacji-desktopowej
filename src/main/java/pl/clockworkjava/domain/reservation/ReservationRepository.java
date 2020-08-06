package pl.clockworkjava.domain.reservation;

import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.guest.Gender;
import pl.clockworkjava.domain.guest.Guest;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.domain.room.Room;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    List<Reservation> reservations = new ArrayList<>();
    RoomService roomService = ObjectPool.getRoomService();
    GuestService guestService = ObjectPool.getGuestService();

    private final static ReservationRepository instance = new ReservationRepository();

    private ReservationRepository() {

    }

    public static ReservationRepository getInstance() {
        return instance;
    }

    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(findNewId(), room, guest, from, to);
        this.reservations.add(res);
        return res;
    }


    private int findNewId() {
        int max = 0;
        for (Reservation reservation : this.reservations) {
            if (reservation.getId() > max) {
                max = reservation.getId();
            }
        }
        return max + 1;
    }

    public void readAll() {
        String name = "reservations.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        if(!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] reservationsAsString = data.split(System.getProperty("line.separator"));

            for (String reservationAsString : reservationsAsString) {
                String[] reservationData = reservationAsString.split(",");
                int id = Integer.parseInt(reservationData[0]);
                int roomId = Integer.parseInt(reservationData[1]);
                int guestId = Integer.parseInt(reservationData[2]);
                String fromAsString = reservationData[3];
                String toAsString = reservationData[4];
                //TODO handle null guest/room
                addExistingReservation(id, this.roomService.getRoomById(roomId), this.guestService.getGuestById(guestId), LocalDateTime.parse(fromAsString), LocalDateTime.parse(toAsString));
            }

        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "guests data");
        }
    }

    private void addExistingReservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(id, room, guest, from, to);
        this.reservations.add(res);
    }

    public void saveAll() {
        String name = "reservations.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");

        for (Reservation reservation : this.reservations) {
            sb.append(reservation.toCSV());
        }

        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "reservation data");
        }
    }

    public List<Reservation> getAll() {
        return this.reservations;
    }
}
