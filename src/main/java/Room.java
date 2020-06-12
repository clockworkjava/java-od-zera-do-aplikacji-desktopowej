public class Room {

    private int number;
    private BedType[] beds;

    public Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.beds = bedTypes;
    }

    public String getInfo() {

        int numberOfBeds = beds.length;

        System.out.println("Rodzaje łóżek w pokoju:");
        for(int i=0;i<numberOfBeds;i=i+1) {
            System.out.println(beds[i]);
        }

        return String.format("Dodano nowy pokoj - numer %d (%s)", this.number, this.beds);
    }
}
