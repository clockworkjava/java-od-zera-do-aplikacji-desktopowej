package pl.clockworkjava.domain.guest;

import pl.clockworkjava.domain.guest.dto.GuestDTO;

public class Guest {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Gender gender;

    Guest(long id, String firstName, String lastName, int age, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public String getInfo() {
        return String.format("%d %s %s (%d) (%s)",this.id, this.firstName, this.lastName, this.age, this.gender);
    }

    String toCSV() {
        return String.format("%s,%s,%s,%d,%s%s",
                this.id,
                this.firstName,
                this.lastName,
                this.age,
                this.gender,
                System.getProperty("line.separator"));
    }

    public GuestDTO getAsDTO() {
        String gender = "Mężczyzna";
        if (this.gender.equals(Gender.FEMALE)) {
            gender = "Kobieta";
        }

        return new GuestDTO(this.id, this.firstName, this.lastName, this.age, gender);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}