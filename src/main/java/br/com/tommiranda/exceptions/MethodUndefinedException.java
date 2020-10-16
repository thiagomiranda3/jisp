package br.com.tommiranda.exceptions;

public class MethodUndefinedException extends RuntimeException {
    public MethodUndefinedException() {
    }

    public MethodUndefinedException(String message) {
        super(message);
    }

    public MethodUndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodUndefinedException(Throwable cause) {
        super(cause);
    }

    public MethodUndefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
