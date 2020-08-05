package pl.clockworkjava.domain.room;

public enum BedType {
    SINGLE(1),
    DOUBLE(2),
    KING_SIZE(2);

    private int size;

    BedType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
