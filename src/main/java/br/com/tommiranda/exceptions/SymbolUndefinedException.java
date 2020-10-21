package br.com.tommiranda.exceptions;

public class SymbolUndefinedException extends RuntimeException {
    public SymbolUndefinedException() {
    }

    public SymbolUndefinedException(String message) {
        super(message);
    }

    public SymbolUndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolUndefinedException(Throwable cause) {
        super(cause);
    }

    public SymbolUndefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
