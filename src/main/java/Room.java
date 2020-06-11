public class Room {

    int number;
    int beds;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
        System.out.println("Utworzono pokój o numerze " + this.number);
    }
}
