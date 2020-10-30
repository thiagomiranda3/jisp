package br.com.tommiranda.exceptions;

public class DefineMacroError extends RuntimeException {
    public DefineMacroError() {
    }

    public DefineMacroError(String message) {
        super(message);
    }

    public DefineMacroError(String message, Throwable cause) {
        super(message, cause);
    }

    public DefineMacroError(Throwable cause) {
        super(cause);
    }

    public DefineMacroError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
