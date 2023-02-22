package pl.bdygasinski.exception.repository;

public class TicketNotFoundRepositoryException extends EntityNotFoundException {
    public TicketNotFoundRepositoryException() {
    }

    public TicketNotFoundRepositoryException(String message) {
        super(message);
    }

    public TicketNotFoundRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketNotFoundRepositoryException(Throwable cause) {
        super(cause);
    }

    public TicketNotFoundRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
