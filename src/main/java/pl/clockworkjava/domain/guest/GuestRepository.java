package pl.clockworkjava.domain.guest;

public class GuestRepository {

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        return new Guest(firstName, lastName, age, gender);
    }
}
