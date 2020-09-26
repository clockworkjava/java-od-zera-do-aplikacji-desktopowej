package pl.clockworkjava.domain.guest;

import java.util.List;

public interface GuestRepository {
    Guest createNewGuest(String firstName, String lastName, int age, Gender gender);

    List<Guest> getAll();

    void saveAll();

    void readAll();

    void remove(long id);

    void edit(long id, String firstName, String lastName, int age, Gender gender);

    Guest findById(long id);
}
