package pl.clockworkjava.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private long id;
    private LocalDateTime from;
    private LocalDateTime to;
    private long roomId;
    private int roomNumber;
    private long guestId;
    private String guestName;

    public ReservationDTO(long id, LocalDateTime from, LocalDateTime to, long roomId,
                          int roomNumber, long guestId, String guestName) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.guestName = guestName;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public long getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public long getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }
}
