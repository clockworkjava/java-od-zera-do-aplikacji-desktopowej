package pl.clockworkjava.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int roomId;
    private int roomNumber;
    private int guestId;
    private String guestName;

    public ReservationDTO(int id, LocalDateTime from, LocalDateTime to, int roomId,
                          int roomNumber, int guestId, String guestName) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.guestName = guestName;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }
}
