package br.com.tommiranda.exceptions;

public class WrongArguments extends RuntimeException {
    public WrongArguments() {
    }

    public WrongArguments(String message) {
        super(message);
    }

    public WrongArguments(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongArguments(Throwable cause) {
        super(cause);
    }

    public WrongArguments(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
