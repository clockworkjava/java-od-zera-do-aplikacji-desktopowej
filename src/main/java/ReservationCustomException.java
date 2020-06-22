abstract public class ReservationCustomException extends RuntimeException {

    abstract int getCode();

    public ReservationCustomException(String message) {
        super(message);
    }
}
