package design.prateek.parking;

public class CabSharingException extends Exception {
    public CabSharingException(String message) {
        super(message);
    }

    public CabSharingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CabSharingException(Throwable cause) {
        super(cause);
    }
}
