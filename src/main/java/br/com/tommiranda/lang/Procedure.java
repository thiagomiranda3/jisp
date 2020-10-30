package br.com.tommiranda.lang;

import br.com.tommiranda.interpreter.Env;
import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.Symbol;

import java.util.List;

public class Procedure implements Func {

    private Object params;
    private Object body;
    private Env env;

    public Procedure(Object params, Object body, Env env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    @Override
    public Object exec(List<Object> arguments) {
        Env funcEnv = new Env(params, arguments, env);

        return Evaluator.eval(body, funcEnv);
    }

    public Object getParams() {
        return params;
    }

    public Object getBody() {
        return body;
    }

    public Env getEnv() {
        return env;
    }
}
