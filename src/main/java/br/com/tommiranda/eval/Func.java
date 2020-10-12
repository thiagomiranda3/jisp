package br.com.tommiranda.eval;

import java.util.List;

@FunctionalInterface
public interface Func {

    Object exec(List<Object> params) throws Exception;
}
