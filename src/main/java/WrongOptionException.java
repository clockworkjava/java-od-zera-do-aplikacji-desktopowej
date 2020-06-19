public class WrongOptionException extends RuntimeException {

    private int code = 101;

    public WrongOptionException() {
        super();
    }

    public WrongOptionException(String message) {
        super(message);
    }

}
