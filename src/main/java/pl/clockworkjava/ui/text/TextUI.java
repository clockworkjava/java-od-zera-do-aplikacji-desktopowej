package pl.clockworkjava.ui.text;

import pl.clockworkjava.domain.guest.Guest;
import pl.clockworkjava.domain.guest.GuestService;
import pl.clockworkjava.exceptions.OnlyNumberException;
import pl.clockworkjava.exceptions.PersistenceToFileException;
import pl.clockworkjava.exceptions.WrongOptionException;
import pl.clockworkjava.domain.room.Room;
import pl.clockworkjava.domain.room.RoomService;
import pl.clockworkjava.util.Properties;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    private void readNewGuestData(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");
        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Podaj płeć (1. Mężczyzna, 2. Kobieta)");

            int genderOption = input.nextInt();

            if (genderOption != 1 && genderOption != 2) {
                throw new WrongOptionException("Wrong option in gender selection");
            }

            boolean isMale = false;

            if(genderOption==1) {
                isMale = true;
            }

            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, isMale);
            System.out.println("Dodano nowego gościa: " + newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender");
        }
    }

    private void readNewRoomData(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");

        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            int[] bedTypes = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(number, bedTypes);
            System.out.println("Dodano nowy pokoj: " + newRoom.getInfo());
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

    public void showSystemInfo() {

        System.out.print("Witam w systemie rezerwacji dla hotelu " + Properties.HOTEL_NAME);
        System.out.println("Aktualna wersja systemu: " + Properties.SYSTEM_VERSION);
        System.out.println("Wersja developerska: " + Properties.IS_DEVELOPER_VERSION);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {

        System.out.println("Trwa ładowanie danych...");
        this.guestService.readAll();
        this.roomService.readAll();

        Scanner input = new Scanner(System.in);

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException | PersistenceToFileException e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void performAction(Scanner input) {

        int option = -1;

        while(option!=0) {

            option = getActionFromUser(input);

            if (option == 1) {
                readNewGuestData(input);
            } else if (option == 2) {
                readNewRoomData(input);
            } else if (option == 3) {
                showAllGuests();
            } else if (option == 4) {
                showAllRooms();
            } else if (option == 0) {
                System.out.println("Wychodzę z aplikacji. Zapisuję dane.");
                this.guestService.saveAll();
                this.roomService.saveAll();
            } else {
                throw new WrongOptionException("Wrong option in main menu");
            }
        }
    }

    private void showAllRooms() {
        List<Room> rooms = this.roomService.getAllRooms();

        for(Room room : rooms) {
            System.out.println(room.getInfo());
        }
    }

    private void showAllGuests() {
        List<Guest> guests = this.guestService.getAllGuests();

        for(Guest guest : guests) {
            System.out.println(guest.getInfo());
        }
    }

    private static int getActionFromUser(Scanner in) {

        System.out.println("1 - Dodaj nowego gościa.");
        System.out.println("2 - Dodaj nowy pokój.");
        System.out.println("3 - Wypisz gości.");
        System.out.println("4 - Wypisz pokoje.");
        System.out.println("0 - Wyjście z aplikacji");
        System.out.println("Wybierz opcję: ");

        int option;

        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu");
        }

        return option;
    }
}
