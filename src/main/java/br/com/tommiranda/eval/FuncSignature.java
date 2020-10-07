package br.com.tommiranda.eval;

import java.util.List;
import java.util.Objects;

public final class FuncSignature {

    private final String op;
    private final List<String> types;

    public FuncSignature(String op, List<String> types) {
        this.op = op;
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FuncSignature that = (FuncSignature) o;

        return op.equals(that.op) && types.equals(that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(op, types);
    }
}
