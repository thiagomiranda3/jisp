package br.com.tommiranda.exceptions;

public class DuplicateParamsException extends RuntimeException {
    public DuplicateParamsException() {
    }

    public DuplicateParamsException(String message) {
        super(message);
    }

    public DuplicateParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateParamsException(Throwable cause) {
        super(cause);
    }

    public DuplicateParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
