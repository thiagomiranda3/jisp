package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArguments;
import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public class ListFunctions {

    @GlobalFunction
    public static List<Object> cons(List<Object> objects) {
        if (objects.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("cons", 2, objects.size()));
        }

        List<Object> list = new ArrayList<>();
        list.add(objects.get(0));
        list.addAll((List<Object>) objects.get(1));

        return list;
    }

    @GlobalFunction
    public static List<Object> append(List<Object> objects) {
        if (objects.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("append", 1, objects.size()));
        }

        List<Object> result = new ArrayList<>();
        result.addAll((List<Object>) objects.get(0));
        result.addAll((List<Object>) objects.get(1));

        return result;
    }

    @GlobalFunction
    public static double length(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("length", 1, objects.size()));
        }

        return ((List) objects.get(0)).size();
    }

    @GlobalFunction
    public static List<Object> list(List<Object> objects) {
        return new ArrayList<>(objects);
    }

    @GlobalFunction("empty?")
    public static Boolean isEmpty(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("empty?", 1, objects.size()));
        }

        List<Object> list = (List<Object>) objects.get(0);

        return list.isEmpty();
    }

    @GlobalFunction
    public static Object first(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("first", 1, objects.size()));
        }

        List<Object> list = (List<Object>) objects.get(0);

        return list.get(0);
    }

    @GlobalFunction
    public static Object rest(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("rest", 1, objects.size()));
        }

        List<Object> list = (List<Object>) objects.get(0);

        if (list.size() > 1) {
            return list.subList(1, list.size());
        }

        return new ArrayList<>();
    }

    @GlobalFunction
    public static Object car(List<Object> objects) {
        return first(objects);
    }

    @GlobalFunction
    public static Object cdr(List<Object> objects) {
        return rest(objects);
    }

    @GlobalFunction
    public static Object apply(List<Object> objects) {
        if (objects.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("apply", 2, objects.size()));
        }

        if (!(objects.get(0) instanceof Func) || !(objects.get(1) instanceof List)) {
            throw new WrongArguments("rest function needs to receive a Function and a List as arguments");
        }

        Func func = (Func) objects.get(0);
        List<Object> arguments = (List<Object>) objects.get(1);

        return func.exec(arguments);
    }
}
