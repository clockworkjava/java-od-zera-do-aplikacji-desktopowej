import java.util.InputMismatchException;
import java.util.Scanner;

public class GuestRepository {

    public GuestRepository() {

    }

    public Guest createNewGuest(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");
        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Podaj płeć (1. Mężczyzna, 2. Kobieta");
            int genderOption = input.nextInt();
            Gender gender = Gender.FEMALE;
            if (genderOption == 1) {
                gender = Gender.MALE;
            } else if (genderOption == 2) {
                gender = Gender.FEMALE;
            } else {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = new Guest(firstName, lastName, age, gender);
            System.out.println(newGuest.getInfo());
            return newGuest;
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender");
        }
    }
}
