package br.com.tommiranda.lang.functions;

import br.com.tommiranda.lang.GlobalFunction;

import java.util.List;
import java.util.stream.Collectors;

public class StringFunctions {

    @GlobalFunction
    public static Object str(List<Object> objects) {
        return objects.stream()
                      .map(String::valueOf)
                      .collect(Collectors.joining());
    }
}
