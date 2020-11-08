package br.com.tommiranda.lang.functions;

import br.com.tommiranda.interpreter.ExprFormater;
import br.com.tommiranda.lang.GlobalFunction;

import java.util.List;
import java.util.stream.Collectors;

public class IOFunctions {

    @GlobalFunction
    public static Object println(List<Object> objects) {
        String string = objects.stream()
                               .map(ExprFormater::format)
                               .collect(Collectors.joining(" "));

        System.out.println(string);

        return null;
    }

    @GlobalFunction
    public static Object print(List<Object> objects) {
        String string = objects.stream()
                               .map(ExprFormater::format)
                               .collect(Collectors.joining(" "));

        System.out.print(string);

        return null;
    }
}
