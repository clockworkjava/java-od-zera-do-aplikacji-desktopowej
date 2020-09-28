package pl.clockworkjava.domain.room;

import pl.clockworkjava.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRoomConnector implements DatabaseRoomConnector {


    private final String removeBedsTemplate = "DELETE FROM BEDS WHERE ROOM_ID = %d";
    private final String createBedTemplate = "INSERT INTO BEDS(ROOM_ID, BED) VALUES(%d, '%s')";

    @Override
    public List<Room> getAllRooms() {

        List<Room> result = new ArrayList<>();

        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ROOMS");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int roomNumber = resultSet.getInt(2);
                List<BedType> beds = new ArrayList<>();
                result.add(new Room(id, roomNumber, beds));
            }
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu poki");
            throw new RuntimeException(ex);
        }

        return result;
    }

    @Override
    public List<Object[]> getAllBeds() {

        List<Object[]> result = new ArrayList<>();

        try {

            Statement statement = SystemUtils.connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM BEDS");

            while (rs.next()) {
                long id = rs.getLong(2);
                String bedType = rs.getString(3);
                BedType bedTypeAsEnum = BedType.SINGLE;
                if (SystemUtils.DOUBLE_BED.equals(bedType)) {
                    bedTypeAsEnum = BedType.DOUBLE;
                } else if (SystemUtils.KING_SIZE.equals(bedType)) {
                    bedTypeAsEnum = BedType.KING_SIZE;
                }

                Object[] touple = new Object[]{id, bedTypeAsEnum};

                result.add(touple);

            }

            statement.close();

        } catch (SQLException throwables) {
            System.out.println("Błąd przy wczytywaniu danych z bazy");
            throw new RuntimeException(throwables);
        }

        return result;
    }

    @Override
    public void remove(long id) {

        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeBedsQuery = String.format(removeBedsTemplate, id);
            statement.execute(removeBedsQuery);
            String removeRoomTemplate = "DELETE FROM ROOMS WHERE ID = %d";
            String removeRoomQuery = String.format(removeRoomTemplate, id);
            statement.execute(removeRoomQuery);
            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu danych");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();

            String updateRoomTemplate = "UPDATE ROOMS SET ROOM_NUMBER=%d WHERE ID=%d";
            String updateQuery = String.format(updateRoomTemplate, number, id);
            statement.executeUpdate(updateQuery);


            String deleteBedsQuery = String.format(removeBedsTemplate, id);
            statement.execute(deleteBedsQuery);

            for (BedType bedType : bedTypes) {
                String createBedQuery = String.format(createBedTemplate, id, bedType.toString());
                statement.execute(createBedQuery);
            }

            statement.close();

        } catch (SQLException throwables) {
            System.out.println("Błąd przy modyfikacji danych");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Room createNewRoom(int number, List<BedType> bedTypes) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String insertRoomTemplate = "INSERT INTO ROOMS(ROOM_NUMBER) VALUES(%d)";
            String query = String.format(insertRoomTemplate, number);
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();

            long newId = -1;
            while (rs.next()) {
                newId = rs.getLong(1);
            }

            for (BedType bedType : bedTypes) {
                statement.execute(String.format(createBedTemplate, newId, bedType.toString()));
            }

            statement.close();

            Room newRoom = new Room(newId, number, bedTypes);
            return newRoom;

        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu nowego pokoju");
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

}
