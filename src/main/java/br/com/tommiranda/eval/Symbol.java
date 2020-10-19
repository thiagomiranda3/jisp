package br.com.tommiranda.eval;

import java.io.Serializable;
import java.util.Objects;

public class Symbol implements Serializable {

    private String name;
    private Object value;

    public Symbol(String name) {
        this.name = name;
    }

    public Symbol(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Symbol symbol = (Symbol) o;

        return name.equals(symbol.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
