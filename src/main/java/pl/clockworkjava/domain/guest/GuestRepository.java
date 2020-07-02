package pl.clockworkjava.domain.guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    List<Guest> guests = new ArrayList<>();

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }
}
