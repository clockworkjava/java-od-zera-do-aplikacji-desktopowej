package pl.clockworkjava.exceptions;

public class PersistenceToFileException extends ReservationCustomException {

    private final int code = 103;

    public PersistenceToFileException(String fileName, String operation, String message) {
        super(String.format("Unable to perform %s on %s (%s)", operation, fileName, message));
    }

    @Override
    public int getCode() {
        return code;
    }
}
