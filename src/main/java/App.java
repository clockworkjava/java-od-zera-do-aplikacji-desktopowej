import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String hotelName = "Overlook";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;

        showSystemInfo(hotelName, systemVersion, isDeveloperVersion);

        Scanner input = new Scanner(System.in);

        int option = getActionFromUser(input);

        if (option == 1) {
            Guest newGuest = createNewGuest(input);
        } else if (option == 2) {
            Room newRoom = createNewRoom(input);
        } else if (option == 3) {
            System.out.println("Wybrano opcję 3.");
        } else {
            System.out.println("Wybrano niepoprawną akcję.");
        }
    }

    public static void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVersion) {

        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public static int getActionFromUser(Scanner in) {

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

        return option;
    }

    public static Guest createNewGuest(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");
        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            Guest newGuest = new Guest(firstName, lastName, age);
            System.out.println(newGuest.getInfo());
            return newGuest;
        } catch (Exception e) {
            System.out.println("Zły wiek, używaj liczb.");
            return null;
        }
    }

    public static Room createNewRoom(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");
        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            BedType bedType =  chooseBedType(input);
            Room newRoom = new Room(number, bedType);
            System.out.println(newRoom.getInfo());
            return newRoom;
        } catch (Exception e) {
            System.out.println("Używaj liczb.");
            e.printStackTrace();
            return null;
        }
    }

    static private BedType chooseBedType(Scanner input) {
        System.out.println("Typy łóżek: ");
        System.out.println("\t1. Pojedyncze");
        System.out.println("\t2. Podwójne");
        System.out.println("\t3. Królewskie");
        BedType bedType = BedType.SINGLE;

        int bedTypeOption = input.nextInt();

        if(bedTypeOption == 1) {
            bedType = BedType.SINGLE;
        } else if(bedTypeOption == 2) {
            bedType = BedType.DOUBLE;
        } else if(bedTypeOption == 3) {
            bedType = BedType.KING_SIZE;
        }

        return bedType;
    }
}
