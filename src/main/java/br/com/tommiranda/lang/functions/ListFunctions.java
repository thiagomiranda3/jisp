package br.com.tommiranda.lang.functions;

import br.com.tommiranda.lang.GlobalFunction;

import java.util.ArrayList;
import java.util.List;

public class ListFunctions {

    @GlobalFunction
    public static List<Object> list(List<Object> numbers) {
        return new ArrayList<>(numbers);
    }
}
