package br.com.tommiranda.exceptions;

public class FieldUndefinedException extends RuntimeException {
    public FieldUndefinedException() {
    }

    public FieldUndefinedException(String message) {
        super(message);
    }

    public FieldUndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldUndefinedException(Throwable cause) {
        super(cause);
    }

    public FieldUndefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
