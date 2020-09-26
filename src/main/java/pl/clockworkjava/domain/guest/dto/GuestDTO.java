package pl.clockworkjava.domain.guest.dto;

public class GuestDTO {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;

    public GuestDTO(long id, String firstName, String lastName, int age, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
