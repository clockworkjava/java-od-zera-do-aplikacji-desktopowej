import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    GuestService guestService = new GuestService();

    public void readNewGuestData(Scanner input) {
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

            if(genderOption != 1 && genderOption !=2) {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, genderOption);
            System.out.println(newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender");
        }
    }
}
