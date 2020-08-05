package pl.clockworkjava.domain.room.dto;

public class RoomDTO {

    private int id;
    private int number;
    private String beds;
    private int bedsCount;

    public RoomDTO(int id, int number, String beds, int bedsCount) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.bedsCount = bedsCount;
    }

    public int getId() {
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
}
