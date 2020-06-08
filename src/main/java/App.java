import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        /* To jest wiadomość powitalna */
        String hotelName = "Overlook";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;


        System.out.print("Witam w systemie rezerwacji dla hotelu");
        System.out.println(hotelName);
        System.out.println("Aktualna wersja systemu: ");
        System.out.println(systemVersion);
        System.out.println("Wersja developerska: ");
        System.out.println(isDeveloperVersion);

        Scanner input = new Scanner(System.in);

        System.out.println("Napisz coś:");

        String text = input.next();

        System.out.println(text);
    }
}
