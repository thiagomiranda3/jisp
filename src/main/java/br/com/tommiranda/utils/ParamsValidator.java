package br.com.tommiranda.utils;

import br.com.tommiranda.exceptions.SyntaxError;
import br.com.tommiranda.exceptions.WrongArguments;
import br.com.tommiranda.interpreter.Symbol;
import br.com.tommiranda.lang.Func;

import java.util.List;

public class ParamsValidator {

    public static void requireSize(String op, List<Object> list, int size) {
        if (list.size() != size) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired(op, size, list.size()));
        }
    }

    public static void requireAtLeast(String op, List<Object> list, int size) {
        if (list.size() < size) {
            throw new WrongArguments(ErrorMessages.wrongParamAtLeast(op, size, list.size()));
        }
    }

    public static void requireList(String op, Object obj) {
        if (!(obj instanceof List)) {
            throw new WrongArguments(op + " contains illegal argument list");
        }
    }

    public static void requireSymbol(String op, Object params) {
        if (params instanceof List) {
            List<Object> list = (List<Object>) params;

            for (Object obj : list) {
                requireSymbol(op, obj);
            }
        } else if (!(params instanceof Symbol)) {
            throw new SyntaxError(params.toString() + " in " + op + " is not a Symbol");
        }
    }

    public static void requireFunc(Object func) {
        if (func instanceof Func) {
            return;
        }

        throw new SyntaxError("macro must be a procedure");
    }
}
