package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public class ListFunctions {

    @GlobalFunction
    public static List<Object> list(List<Object> objects) {
        return new ArrayList<>(objects);
    }

    @GlobalFunction
    public static Object first(List<Object> objects) {
        if(objects.size() != 1 || !(objects.get(0) instanceof List)) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("first", 1, objects.size()));
        }

        List list = (List) objects.get(0);

        return list.get(0);
    }

    @GlobalFunction
    public static Object rest(List<Object> objects) {
        if(objects.size() != 1 || !(objects.get(0) instanceof List)) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("rest", 1, objects.size()));
        }

        List list = (List) objects.get(0);

        if(list.size() > 1) {
            return list.subList(1, list.size());
        }

        return new ArrayList<>();
    }

    @GlobalFunction
    public static Object apply(List<Object> objects) {
        if(objects.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("apply", 2, objects.size()));
        }

        if(!(objects.get(0) instanceof Func) || !(objects.get(1) instanceof List)) {
            throw new WrongParamsException("rest function needs to receive a Function and a List as params");
        }

        Func func = (Func) objects.get(0);
        List params = (List) objects.get(1);

        return func.exec(params);
    }
}
