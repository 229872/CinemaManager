package pl.bdygasinski.exception.repository;

public class ClientNotFoundRepositoryException extends EntityNotFoundException {
    public ClientNotFoundRepositoryException() {
    }

    public ClientNotFoundRepositoryException(String message) {
        super(message);
    }

    public ClientNotFoundRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundRepositoryException(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
