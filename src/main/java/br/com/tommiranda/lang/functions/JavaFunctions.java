package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArguments;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;
import br.com.tommiranda.utils.Util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JavaFunctions {

    @GlobalFunction
    public static Object invoke(List<Object> objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (objects.size() != 3) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("invoke", 1, objects.size()));
        }

        Object obj = objects.get(0);
        String methodName = (String) objects.get(1);
        List<Object> args = (List<Object>) objects.get(2);

        if (Util.isGenericType(obj.getClass())) {
            return obj.getClass()
                      .getMethod(methodName, args.stream()
                                                 .map(a -> Object.class)
                                                 .toArray(Class[]::new))
                      .invoke(obj, args.stream().toArray());
        }

        return obj.getClass()
                  .getMethod(methodName, args.stream()
                                             .map(Object::getClass)
                                             .toArray(Class[]::new))
                  .invoke(obj, args.stream().toArray());

    }

    @GlobalFunction("invoke-static")
    public static Object invokeStatic(List<Object> objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (objects.size() != 3) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("invoke-static", 1, objects.size()));
        }

        String className = (String) objects.get(0);
        String methodName = (String) objects.get(1);
        List<Object> args = (List<Object>) objects.get(2);

        Class<?> clazz = Class.forName(className);

        if (Util.isGenericType(clazz)) {
            return clazz.getClass()
                        .getMethod(methodName, args.stream()
                                                   .map(a -> Object.class)
                                                   .toArray(Class[]::new))
                        .invoke(null, args.stream().toArray());
        }

        return clazz.getClass()
                    .getMethod(methodName, args.stream()
                                               .map(Object::getClass)
                                               .toArray(Class[]::new))
                    .invoke(null, args.stream().toArray());
    }

    @GlobalFunction("new-object")
    public static Object newObject(List<Object> objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (objects.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("invoke", 1, objects.size()));
        }

        String className = (String) objects.get(0);
        List<Object> args = (List<Object>) objects.get(1);

        Class<?> clazz = Class.forName(className);

        return clazz.getConstructor(args.stream()
                                        .map(Object::getClass)
                                        .toArray(Class[]::new))
                    .newInstance(args.toArray());
    }
}
