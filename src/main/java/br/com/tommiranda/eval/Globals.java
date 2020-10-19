package br.com.tommiranda.eval;

import br.com.tommiranda.exceptions.FieldUndefinedException;
import br.com.tommiranda.exceptions.MethodUndefinedException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.Util;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Globals {

    private static final Map<String, Func> mapFunc = mapFunctions();
    private static final Map<String, Object> mapSymbol = mapSymbols();

    private static Map<String, Func> mapFunctions() {
        Map<String, Func> mapFunc = new HashMap<>();

        Reflections reflections = new Reflections("br.com.tommiranda.lang", new MethodAnnotationsScanner());
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(GlobalFunction.class);

        for (Method method : annotatedMethods) {
            String funcName = method.getAnnotation(GlobalFunction.class).value();
            if (Util.isNullOrEmpty(funcName)) {
                funcName = method.getName();
            }

            mapFunc.put(funcName, values -> {
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

        return mapFunc;
    }

    private static Map<String, Object> mapSymbols() {
        Map<String, Object> mapSymbol = new HashMap<>();

        return mapSymbol;
    }

    public static void addFunction(String name, Func func) {
        mapFunc.put(name, func);
    }

    public static void addSymbol(String name, Object object) {
        mapSymbol.put(name, object);
    }

    public static boolean removeFunction(String name) {
        return mapFunc.remove(name) != null;
    }

    public static boolean removeSymbol(String name) {
        return mapSymbol.remove(name) != null;
    }

    public static Func getFunc(String name) {
        Func func = mapFunc.get(name);

        if (func != null) {
            return func;
        }

        throw new MethodUndefinedException(name + " method undefined");
    }

    public static Object getSymbolValue(String name) {
        Object value = mapSymbol.get(name);

        if(value != null) {
            return value;
        }

        throw new FieldUndefinedException(name + " symbol undefined");
    }
}
