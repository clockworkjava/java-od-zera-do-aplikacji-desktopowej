package pl.clockworkjava.ui.text;

import pl.clockworkjava.Guest;
import pl.clockworkjava.GuestService;
import pl.clockworkjava.exceptions.OnlyNumberException;
import pl.clockworkjava.exceptions.WrongOptionException;
import pl.clockworkjava.domain.room.Room;
import pl.clockworkjava.domain.room.RoomService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    GuestService guestService = new GuestService();
    RoomService roomService = new RoomService();

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

            if (genderOption != 1 && genderOption != 2) {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, genderOption);
            System.out.println(newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender");
        }
    }

    public void readNewRoomData(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");

        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            int[] bedTypes = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(number, bedTypes);
            System.out.println(newRoom.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room");
        }
    }

    private int[] chooseBedType(Scanner input) {
        System.out.println("Ile łóżek w pokoju?: ");
        int bedNumber = input.nextInt();

        int[] bedTypes = new int[bedNumber];

        for (int i = 0; i < bedNumber; i = i + 1) {

            System.out.println("Typy łóżek: ");
            System.out.println("\t1. Pojedyncze");
            System.out.println("\t2. Podwójne");
            System.out.println("\t3. Królewskie");

            int bedTypeOption = input.nextInt();

            bedTypes[i] = bedTypeOption;
        }

        return bedTypes;
    }

    public void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVersion) {

        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {

        Scanner input = new Scanner(System.in);

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Wychodzę z aplikacji");
        }
    }

    private void performAction(Scanner input) {

        int option = getActionFromUser(input);

        if (option == 1) {
            readNewGuestData(input);
        } else if (option == 2) {
            readNewRoomData(input);
        } else if (option == 3) {
            System.out.println("Wybrano opcję 3.");
        } else {
            throw new WrongOptionException("Wrong option in main menu");
        }
    }

    public static int getActionFromUser(Scanner in) {

        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Wyszukaj gościa.");
        System.out.println("Wybierz opcję: ");

        int option = 0;

        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu");
        }

        return option;
    }
}
