package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.List;

public class CoreFunctions {

    @GlobalFunction
    public static Object type(List<Object> objects) {
        if (objects.size() != 1) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("type", 1, objects.size()));
        }

        return objects.get(0).getClass();
    }

    @GlobalFunction
    public static Object begin(List<Object> objects) {
        return objects.get(objects.size() - 1);
    }
}
