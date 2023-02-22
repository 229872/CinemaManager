package pl.bdygasinski.exception.repository;

public class MovieNotFoundRepositoryException extends EntityNotFoundException {
    public MovieNotFoundRepositoryException() {
    }

    public MovieNotFoundRepositoryException(String message) {
        super(message);
    }

    public MovieNotFoundRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundRepositoryException(Throwable cause) {
        super(cause);
    }

    public MovieNotFoundRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
