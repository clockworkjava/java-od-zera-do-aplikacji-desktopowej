package pl.clockworkjava.domain.room.dto;

public class RoomDTO {

    private int id;
    private int number;
    private String beds;

    public RoomDTO(int id, int number, String beds) {
        this.id = id;
        this.number = number;
        this.beds = beds;
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
}
