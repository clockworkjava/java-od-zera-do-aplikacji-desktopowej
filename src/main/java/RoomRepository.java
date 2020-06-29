public class RoomRepository {
    public Room createNewRoom(int number, BedType[] bedTypes) {
        return new Room(number, bedTypes);
    }
}
