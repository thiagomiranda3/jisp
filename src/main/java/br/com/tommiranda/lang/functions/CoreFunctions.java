package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;

import java.util.List;

public class CoreFunctions {

    @GlobalFunction
    public static Object type(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongParamsException("type function has only 1 required param");
        }

        return objects.get(0).getClass();
    }
}
