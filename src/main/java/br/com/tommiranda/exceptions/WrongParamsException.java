package br.com.tommiranda.exceptions;

public class WrongParamsException extends RuntimeException {
    public WrongParamsException() {
    }

    public WrongParamsException(String message) {
        super(message);
    }

    public WrongParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongParamsException(Throwable cause) {
        super(cause);
    }

    public WrongParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
