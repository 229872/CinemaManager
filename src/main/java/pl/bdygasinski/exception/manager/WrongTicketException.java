package pl.bdygasinski.exception.manager;

public class WrongTicketException extends Exception {
    public WrongTicketException() {
    }

    public WrongTicketException(String message) {
        super(message);
    }

    public WrongTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTicketException(Throwable cause) {
        super(cause);
    }

    public WrongTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
