package pl.clockworkjava.domain.guest;

import pl.clockworkjava.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDatabaseRepository implements GuestRepository {

    private final static GuestRepository instance = new GuestDatabaseRepository();

    private List<Guest> guests = new ArrayList<>();

    public static GuestRepository getInstance() {
        return instance;
    }

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String createGuestTemplate = "INSERT INTO GUESTS(FIRST_NAME, LAST_NAME, AGE, GENDER) VALUES('%s','%s',%d,'%s')";
            String createGuestQuery = String.format(createGuestTemplate, firstName, lastName, age, gender.toString());
            statement.execute(createGuestQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            long newId = -1;
            while (rs.next()) {
                newId = rs.getLong(1);
            }
            statement.close();

            Guest newGuest = new Guest(newId, firstName, lastName, age, gender);
            this.guests.add(newGuest);
            return newGuest;
        } catch (SQLException throwables) {
            System.out.println("Błąd przy dodawaniu pokoju");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM GUESTS");

            while (rs.next()) {
                long id = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int age = rs.getInt(4);

                String genderAsString = rs.getString(5);

                Gender gender = Gender.FEMALE;

                if (SystemUtils.MALE.equals(genderAsString)) {
                    gender = Gender.MALE;
                }

                Guest newGuest = new Guest(id, firstName, lastName, age, gender);
                this.guests.add(newGuest);
            }

            statement.close();

        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu danych");
            throw new RuntimeException(throwables);
        }


    }

    @Override
    public void remove(long id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeGuestTemplate = "DELETE FROM GUESTS WHERE ID=%d";
            String removeGuestQuery = String.format(removeGuestTemplate, id);
            statement.execute(removeGuestQuery);
            statement.close();
            this.removeById(id);
        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu gościa");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void edit(long id, String firstName, String lastName, int age, Gender gender) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String updateTemplate = "UPDATE GUESTS SET FIRST_NAME='%s', LAST_NAME='%s', AGE=%d, GENDER='%s' WHERE ID=%d";
            String updateQuery = String.format(updateTemplate, firstName, lastName, age, gender.toString(), id);
            statement.executeUpdate(updateQuery);
            statement.close();

            this.removeById(id);
            this.guests.add(new Guest(id, firstName, lastName, age, gender));
        } catch (SQLException throwables) {
            System.out.println("Błąd przy modyfikacji danych");
            throw new RuntimeException(throwables);
        }
    }

    public void removeById(long id) {
        int indexToBeRemoved = -1;

        for (int i = 0; i < this.guests.size(); i++) {
            if (this.guests.get(i).getId() == id) {
                indexToBeRemoved = i;
                break;
            }
        }

        if (indexToBeRemoved != -1) {
            this.guests.remove(indexToBeRemoved);
        }
    }

    @Override
    public Guest findById(long id) {
        for (Guest guest : this.guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }
}
