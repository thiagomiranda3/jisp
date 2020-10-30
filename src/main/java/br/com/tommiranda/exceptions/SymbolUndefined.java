package br.com.tommiranda.exceptions;

public class SymbolUndefined extends RuntimeException {
    public SymbolUndefined() {
    }

    public SymbolUndefined(String message) {
        super(message);
    }

    public SymbolUndefined(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolUndefined(Throwable cause) {
        super(cause);
    }

    public SymbolUndefined(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
