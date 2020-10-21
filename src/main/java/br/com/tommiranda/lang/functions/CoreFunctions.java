package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.Util;

import java.util.List;

public class CoreFunctions {

    @GlobalFunction("null")
    public static Object nullFunc(List<Object> objects) {
        return null;
    }

    @GlobalFunction("true")
    public static Object trueFunc(List<Object> objects) {
        return true;
    }

    @GlobalFunction("false")
    public static Object falseFunc(List<Object> objects) {
        return false;
    }

    @GlobalFunction
    public static Object type(List<Object> objects) {
        if(objects.size() != 1) {
            throw new WrongParamsException("type function has only 1 required param");
        }

        return objects.get(0).getClass().getName();
    }

    @GlobalFunction
    public static Object pass(List<Object> objects) {
        if (Util.safeList(objects).size() == 1) {
            return objects.get(0);
        }

        return objects;
    }
}
