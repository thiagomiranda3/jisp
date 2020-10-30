package br.com.tommiranda.interpreter;

import br.com.tommiranda.exceptions.SymbolUndefined;
import br.com.tommiranda.exceptions.WrongArguments;
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

    public Env(Object params, List<Object> arguments, Env outer) {
        this.outer = outer;

        if (params instanceof Symbol) {
            Symbol sym = (Symbol) params;
            this.env = new LinkedHashMap<>();
            this.env.put(sym.getName(), arguments);
        } else {
            List<Symbol> symbols = (List<Symbol>) params;
            try {
                this.env = Util.createMapFromIterables((symbols).stream()
                                                                .map(Symbol::getName)
                                                                .collect(Collectors.toList()),
                                                       arguments,
                                                       LinkedHashMap::new);
            } catch (Exception e) {
                throw new WrongArguments("function expected " + ExprFormater.format(symbols) + ", found " + ExprFormater.format(arguments));
            }
        }
    }

    public Map<String, Object> getEnv() {
        return env;
    }

    public void addSymbol(String name, Object object) {
        env.put(name, object);
    }

    public boolean removeSymbol(String name) {
        return env.remove(name) != null;
    }

    public Object getSymbolValue(String name) {
        Object value = env.get(name);

        if (value != null) {
            return value;
        }

        if (outer != null) {
            return outer.getSymbolValue(name);
        }

        throw new SymbolUndefined(name + " symbol undefined");
    }

    public Map<String, Object> findEnv(String name) {
        if (env.containsKey(name)) {
            return env;
        }

        if (outer != null) {
            return outer.findEnv(name);
        }

        throw new SymbolUndefined(name + " symbol undefined");
    }
}
