package br.com.tommiranda.lang;

import java.util.List;

@FunctionalInterface
public interface Func {

    Object exec(List<Object> params);
}
