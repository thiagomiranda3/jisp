package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArgumentsException;
import br.com.tommiranda.interpreter.Symbol;
import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoreFunctions {

    @GlobalFunction
    public static Object type(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("type", 1, objects.size()));
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
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("<=", 2, objects.size()));
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
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("list?", 1, objects.size()));
        }

        return objects.get(0) instanceof List;
    }

    @GlobalFunction("null?")
    public static Boolean isNull(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("null?", 1, objects.size()));
        }

        return objects.get(0) == null;
    }

    @GlobalFunction("symbol?")
    public static Boolean isSymbol(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("symbol?", 1, objects.size()));
        }

        return objects.get(0) instanceof Symbol;
    }

    @GlobalFunction("number?")
    public static Boolean isNumber(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("number?", 1, objects.size()));
        }

        return objects.get(0) instanceof Number;
    }

    @GlobalFunction("function?")
    public static Boolean isFunction(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("number?", 1, objects.size()));
        }

        return objects.get(0) instanceof Func;
    }
}
