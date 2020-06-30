package pl.clockworkjava.domain.guest;

public class GuestRepository {

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        return newGuest;
    }
}
