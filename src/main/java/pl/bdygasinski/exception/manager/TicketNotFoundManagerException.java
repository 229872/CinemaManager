package pl.bdygasinski.exception.manager;


public class TicketNotFoundManagerException extends Exception {
    public TicketNotFoundManagerException() {
    }

    public TicketNotFoundManagerException(String message) {
        super(message);
    }

    public TicketNotFoundManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketNotFoundManagerException(Throwable cause) {
        super(cause);
    }

    public TicketNotFoundManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
