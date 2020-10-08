package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Value;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@FunctionalInterface
public interface Func {

    Value exec(List<Value> params) throws Exception;
}
