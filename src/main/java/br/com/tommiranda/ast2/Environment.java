package br.com.tommiranda.ast2;

import br.com.tommiranda.eval.Func;
import br.com.tommiranda.exceptions.SymbolUndefinedException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.Util;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Environment {

    private final Map<String, Object> env = mapFunctions();

    private Map<String, Object> mapFunctions() {
        Map<String, Object> env = new HashMap<>();

        Reflections reflections = new Reflections("br.com.tommiranda.lang", new MethodAnnotationsScanner());
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(GlobalFunction.class);

        for (Method method : annotatedMethods) {
            String funcName = method.getAnnotation(GlobalFunction.class).value();
            if (Util.isNullOrEmpty(funcName)) {
                funcName = method.getName();
            }

            env.put(funcName, (Func) values -> {
                try {
                    return method.invoke(null, values);
                } catch (Exception e) {
                    if (e.getCause() != null) {
                        throw (RuntimeException) e.getCause();
                    }

                    throw new RuntimeException(e);
                }
            });
        }

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

        throw new SymbolUndefinedException(name + " symbol undefined");
    }
}
