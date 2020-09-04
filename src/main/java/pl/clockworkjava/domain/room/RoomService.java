package pl.clockworkjava.domain.room;

import pl.clockworkjava.domain.ObjectPool;
import pl.clockworkjava.domain.room.dto.RoomDTO;
import pl.clockworkjava.exceptions.WrongOptionException;
import pl.clockworkjava.util.Properties;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private final RoomRepository repository = ObjectPool.getRoomRepository();

    private final static RoomService instance = new RoomService();

    private RoomService() {

    }

    public Room createNewRoom(int number, List<String> bedTypesAsString) {

        BedType[] bedTypes = getBedTypes(bedTypesAsString);

        return repository.createNewRoom(number, bedTypes);
    }

    public Room createNewRoom(int number, int[] bedTypesOptions) {

        BedType[] bedTypes = getBedTypes(bedTypesOptions);

        return repository.createNewRoom(number, bedTypes);
    }

    private BedType[] getBedTypes(int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];

        for (int i = 0; i < bedTypesOptions.length; i = i + 1) {

            BedType bedType;

            if (bedTypesOptions[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesOptions[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesOptions[i] == 3) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }

            bedTypes[i] = bedType;
        }
        return bedTypes;
    }

    public List<Room> getAllRooms() {
        return this.repository.getAllRooms();
    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void editRoom(int id, int number, List<String> bedTypesAsString) {

        BedType[] bedTypes = getBedTypes(bedTypesAsString);

        this.repository.edit(id,number,bedTypes);

    }

    private BedType[] getBedTypes(List<String> bedTypesAsString) {
        BedType[] bedTypes = new BedType[bedTypesAsString.size()];

        for (int i = 0; i < bedTypesAsString.size(); i = i + 1) {

            BedType bedType;

            if (bedTypesAsString.get(i).equals(Properties.SINGLE_BED)) {
                bedType = BedType.SINGLE;
            } else if (bedTypesAsString.get(i).equals(Properties.DOUBLE_BED)) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesAsString.get(i).equals(Properties.KING_SIZE)) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }

            bedTypes[i] = bedType;
        }
        return bedTypes;
    }

    public void editRoom(int id, int number, int[] bedTypesOptions) {

        BedType[] bedTypes = getBedTypes(bedTypesOptions);

        this.repository.edit(id,number,bedTypes);

    }

    public void removeRoom(int id) {
        this.repository.remove(id);
    }

    public Room getRoomById(int roomId) {
        return this.repository.getById(roomId);
    }

    public List<RoomDTO> getAllAsDTO() {

        List<RoomDTO> result = new ArrayList<>();

        List<Room> allRooms = repository.getAllRooms();

        for(Room room : allRooms) {
            RoomDTO dto = room.generateDTO();
            result.add(dto);
        }

        return result;

    }

    public static RoomService getInstance() {
        return instance;
    }
}
