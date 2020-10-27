package br.com.tommiranda.lang;

import br.com.tommiranda.interpreter.Env;
import br.com.tommiranda.utils.Util;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class Global {

    private static final Env env = getGlobals();

    private static Env getGlobals() {
        Map<String, Object> mapEnv = new LinkedHashMap<>();

        Reflections reflections = new Reflections("br.com.tommiranda.lang", new MethodAnnotationsScanner());
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(GlobalFunction.class);

        for (Method method : annotatedMethods) {
            String funcName = method.getAnnotation(GlobalFunction.class).value();
            if (Util.isNullOrEmpty(funcName)) {
                funcName = method.getName();
            }

            mapEnv.put(funcName, (Func) values -> {
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

        return new Env(mapEnv);
    }

    public static Env getEnv() {
        return env;
    }

    public void addSymbol(String name, Object object) {
        env.addSymbol(name, object);
    }

    public boolean removeSymbol(String name) {
        return env.removeSymbol(name);
    }
}
