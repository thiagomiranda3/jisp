package br.com.tommiranda.interpreter;

import br.com.tommiranda.exceptions.SymbolUndefinedException;
import br.com.tommiranda.utils.Util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Env {

    private final Map<String, Object> env;
    private Env outer;

    public Env(Map<String, Object> env) {
        this.env = env;
    }

    public Env(List<Symbol> params, List<Object> values) {
        this.env = Util.createMapFromIterables(params.stream()
                                                     .map(Symbol::getName)
                                                     .collect(Collectors.toList()),
                                               values,
                                               LinkedHashMap::new);
    }

    public void addSymbol(String name, Object object) {
        env.put(name, object);
    }

    public boolean removeSymbol(String name) {
        return env.remove(name) != null;
    }

    public void setOuterEnv(Env outer) {
        this.outer = outer;
    }

    public Object getSymbolValue(String name) {
        Object value = env.get(name);

        if (value != null) {
            return value;
        }

        if (outer != null) {
            return outer.getSymbolValue(name);
        }

        throw new SymbolUndefinedException(name + " symbol undefined");
    }

    public Map<String, Object> findEnv(String name) {
        if (env.containsKey(name)) {
            return env;
        }

        if (outer != null) {
            return outer.findEnv(name);
        }

        throw new SymbolUndefinedException(name + " symbol undefined");
    }
}
