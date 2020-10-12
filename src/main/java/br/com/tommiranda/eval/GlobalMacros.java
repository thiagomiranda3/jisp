package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.lang.GlobalMacro;
import br.com.tommiranda.utils.Util;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GlobalMacros {

    private static final Map<String, Macro> mapMacro = createMap();

    private static Map<String, Macro> createMap() {
        Map<String, Macro> mapMacro = new HashMap<>();

        Reflections reflections = new Reflections("br.com.tommiranda.lang", new MethodAnnotationsScanner());
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(GlobalMacro.class);

        for (Method method : annotatedMethods) {
            String macroName = method.getAnnotation(GlobalMacro.class).value();
            if (Util.isNullOrEmpty(macroName)) {
                macroName = method.getName();
            }

            mapMacro.put(macroName, values -> {
                try {
                    return (List<Node>) method.invoke(null, values);
                } catch (Exception e) {
                    if (e.getCause() != null) {
                        throw (Exception) e.getCause();
                    }

                    throw e;
                }
            });
        }

        return mapMacro;
    }

    public static void addMacro(String name, Macro macro) {
        mapMacro.put(name, macro);
    }

    public static Macro getMacro(String name) {
        return mapMacro.get(name);
    }
}
