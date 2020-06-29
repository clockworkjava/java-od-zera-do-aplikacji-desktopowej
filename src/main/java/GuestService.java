import java.util.Scanner;

public class GuestService {

    GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(Scanner input) {
        return repository.createNewGuest(input);
    }
}
