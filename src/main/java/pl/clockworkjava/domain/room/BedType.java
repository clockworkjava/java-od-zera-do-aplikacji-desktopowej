package pl.clockworkjava.domain.room;

import pl.clockworkjava.util.SystemUtils;

public enum BedType {
    SINGLE(1, SystemUtils.SINGLE_BED),
    DOUBLE(2, SystemUtils.DOUBLE_BED),
    KING_SIZE(2, SystemUtils.KING_SIZE);

    private int size;
    private String asStr;

    BedType(int size, String asStr) {
        this.size = size;
        this.asStr = asStr;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}
