package pl.bdygasinski.exception.manager;

public class MovieNotFoundManagerException extends Exception {
    public MovieNotFoundManagerException() {
    }

    public MovieNotFoundManagerException(String message) {
        super(message);
    }

    public MovieNotFoundManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundManagerException(Throwable cause) {
        super(cause);
    }

    public MovieNotFoundManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
