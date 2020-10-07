package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Value;
import br.com.tommiranda.core.MathFunctions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class MappedFunctions {

    private static final Map<String, Func> mapFunc = createMap();

    private static Map<String, Func> createMap() {
        Map<String, Func> mapFunc = new HashMap<>();

        mapFunc.put("+", values -> new Value(MathFunctions.sum(getAsNumber(values))));
        mapFunc.put("-", values -> new Value(MathFunctions.substract(getAsNumber(values))));
        mapFunc.put("*", values -> new Value(MathFunctions.multiple(getAsNumber(values))));
        mapFunc.put("/", values -> new Value(MathFunctions.divide(getAsNumber(values))));
        mapFunc.put("%", values -> new Value(MathFunctions.remainder(getAsNumber(values))));
        mapFunc.put("max", values -> new Value(MathFunctions.max(getAsNumber(values))));
        mapFunc.put("min", values -> new Value(MathFunctions.min(getAsNumber(values))));

        return mapFunc;
    }

    public static void addFunction(String name, Func func) {
        mapFunc.put(name, func);
    }

    public static Func getFunc(String name) throws NoSuchMethodException {
        Func func = mapFunc.get(name);

        if (func != null) {
            return func;
        }

        throw new NoSuchMethodException(name + " method undefined");
    }

    private static Object[] getAsObjects(Value... values) {
        return Arrays.stream(values)
                     .map(v -> switch (v.getType()) {
                         case Number -> v.getAsNumber();
                         case String -> v.getAsString();
                         case Boolean -> v.getAsBoolean();
                     })
                     .toArray();
    }

    private static BigDecimal[] getAsNumber(Value... values) {
        return Arrays.stream(values)
                     .map(Value::getAsNumber)
                     .toArray(BigDecimal[]::new);
    }
}
