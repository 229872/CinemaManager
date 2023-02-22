package pl.bdygasinski.exception.manager;

public class ClientNotFoundManagerException extends Exception {
    public ClientNotFoundManagerException() {
    }

    public ClientNotFoundManagerException(String message) {
        super(message);
    }

    public ClientNotFoundManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundManagerException(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
