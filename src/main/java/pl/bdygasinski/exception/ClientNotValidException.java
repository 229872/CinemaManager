package pl.bdygasinski.exception;

public class ClientNotValidException extends Exception {
    public ClientNotValidException() {
    }

    public ClientNotValidException(String message) {
        super(message);
    }

    public ClientNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotValidException(Throwable cause) {
        super(cause);
    }

    public ClientNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
