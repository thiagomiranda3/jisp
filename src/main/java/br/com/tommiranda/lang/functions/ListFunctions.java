package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;

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
            throw new WrongParamsException("first function need to receive a list as param");
        }

        List list = (List) objects.get(0);

        return list.get(0);
    }
}
