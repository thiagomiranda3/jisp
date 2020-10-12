package br.com.tommiranda.lang.functions;

import br.com.tommiranda.lang.GlobalFunction;

import java.util.List;

public class CoreFunctions {

    @GlobalFunction("null")
    public static Object nullFunc(List<Object> objects) {
        return null;
    }
}
