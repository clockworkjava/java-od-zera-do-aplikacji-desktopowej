package pl.clockworkjava.domain.room;

import org.junit.jupiter.api.Test;
import pl.clockworkjava.domain.ObjectPool;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void convertFromIntOptionsIntoBedTypesTest() {

        //given
        RoomService roomService = ObjectPool.getRoomService();
        int[] bedTypeOptions = new int[]{1, 2, 3};

        //when
        List<BedType> bedTypes = roomService.getBedTypes(bedTypeOptions);

        //then
        assertEquals(3, bedTypes.size());
        assertEquals(BedType.SINGLE, bedTypes.get(0));
        assertEquals(BedType.DOUBLE, bedTypes.get(1));
        assertEquals(BedType.KING_SIZE, bedTypes.get(2));

    }
}
