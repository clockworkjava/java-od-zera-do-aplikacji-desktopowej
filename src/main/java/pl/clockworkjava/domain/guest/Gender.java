package pl.clockworkjava.domain.guest;

import pl.clockworkjava.util.Properties;

public enum Gender {
    MALE(Properties.MALE),
    FEMALE(Properties.FEMALE);

    private String asStr;

    Gender(String asStr) {
        this.asStr = asStr;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}
