import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        showSystemInfo();

        Scanner input = new Scanner(System.in);

        if (option == 1) {
            System.out.println("Tworzymy nowego gościa.");
            try {
                System.out.println("Podaj imię: ");
                String firstName = input.next();
                System.out.println("Podaj nazwisko: ");
                String lastName = input.next();
                System.out.println("Podaj wiek: ");
                int age = input.nextInt();
                Guest newGuest = new Guest(firstName, lastName, age);
                String info = String.format("Dodano nowego gościa: %s %s (%d) ", newGuest.firstName, newGuest.lastName, newGuest.age);
                System.out.println(info);
            } catch (Exception e) {
                System.out.println("Zły wiek, używaj liczb.");
            }
        } else if (option == 2) {
            System.out.println("Tworzymy nowy pokój.");
            try {
                System.out.println("Numer: ");
                int number = input.nextInt();
                System.out.println("Ilość łóżek: ");
                int beds = input.nextInt();
                Room newRoom = new Room(number, beds);
                String info = String.format("Dodano nowy pokoj - numer %d (%d)", newRoom.number, newRoom.beds);
                System.out.println(info);
            } catch (Exception e) {
                System.out.println("Używaj liczb.");
            }
        } else if (option == 3) {
            System.out.println("Wybrano opcję 3.");
        } else {
            System.out.println("Wybrano niepoprawną akcję.");
        }
    }

    public static void showSystemInfo() {
        String hotelName = "Overlook";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;


        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public static void getActionFromUser(Scanner in) {

        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Wyszukaj gościa.");
        System.out.println("Wybierz opcję: ");

        int option = 0;

        try {
            option = in.nextInt();
        } catch (Exception e) {
            System.out.println("Niepoprawne dane wejsciowe, wprowadz liczbę.");
            e.printStackTrace();
        }
    }
}
