package pl.clockworkjava.domain.guest;

public class Guest {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    Guest(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public String getInfo() {
        return String.format("Dodano nowego gościa: %s %s (%d) ", this.firstName, this.lastName, this.age);
    }
}