package pl.clockworkjava.domain.room.dto;

public class RoomDTO {

    private long id;
    private int number;
    private String beds;
    private int bedsCount;
    private int roomSize;

    public RoomDTO(long id, int number, String beds, int bedsCount, int roomSize) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.bedsCount = bedsCount;
        this.roomSize = roomSize;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getBeds() {
        return beds;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    public int getRoomSize() {
        return roomSize;
    }
}
