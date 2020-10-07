package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Value;

@FunctionalInterface
public interface Func {

    Value exec(Value... params);
}
