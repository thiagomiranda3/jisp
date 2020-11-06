package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArguments;
import br.com.tommiranda.interpreter.ExprFormater;
import br.com.tommiranda.interpreter.Symbol;
import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoreFunctions {

    @GlobalFunction
    public static Object println(List<Object> objects) {
        String string = objects.stream()
                                .map(ExprFormater::format)
                                .collect(Collectors.joining(" "));

        System.out.println(string);

        return null;
    }

    @GlobalFunction
    public static Object print(List<Object> objects) {
        String string = objects.stream()
                               .map(ExprFormater::format)
                               .collect(Collectors.joining(" "));

        System.out.print(string);

        return null;
    }

    @GlobalFunction
    public static Object type(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("type", 1, objects.size()));
        }

        return objects.get(0).getClass();
    }

    @GlobalFunction
    public static Object begin(List<Object> objects) {
        return objects.get(objects.size() - 1);
    }

    @GlobalFunction
    public static List<Object> map(List<Object> objects) {
        if (objects.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("<=", 2, objects.size()));
        }

        Func func = (Func) objects.get(0);
        List<Object> params = (List<Object>) objects.get(1);

        return params.stream()
                     .map(p -> func.exec(Collections.singletonList(p)))
                     .collect(Collectors.toList());
    }

    @GlobalFunction("list?")
    public static Boolean isList(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("list?", 1, objects.size()));
        }

        return objects.get(0) instanceof List;
    }

    @GlobalFunction("null?")
    public static Boolean isNull(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("null?", 1, objects.size()));
        }

        return objects.get(0) == null;
    }

    @GlobalFunction("symbol?")
    public static Boolean isSymbol(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("symbol?", 1, objects.size()));
        }

        return objects.get(0) instanceof Symbol;
    }

    @GlobalFunction("number?")
    public static Boolean isNumber(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("number?", 1, objects.size()));
        }

        return objects.get(0) instanceof Number;
    }

    @GlobalFunction("bigdec?")
    public static Boolean isBigDec(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("bigdec?", 1, objects.size()));
        }

        return objects.get(0) instanceof BigDecimal;
    }

    @GlobalFunction("bigint?")
    public static Boolean isBigInt(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("bigint?", 1, objects.size()));
        }

        return objects.get(0) instanceof BigInteger;
    }

    @GlobalFunction("long?")
    public static Boolean isLong(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("long?", 1, objects.size()));
        }

        return objects.get(0) instanceof Long;
    }

    @GlobalFunction("double?")
    public static Boolean isDouble(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("double?", 1, objects.size()));
        }

        return objects.get(0) instanceof Double;
    }

    @GlobalFunction("string?")
    public static Boolean isString(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("string?", 1, objects.size()));
        }

        return objects.get(0) instanceof String;
    }

    @GlobalFunction("function?")
    public static Boolean isFunction(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("number?", 1, objects.size()));
        }

        return objects.get(0) instanceof Func;
    }
}
