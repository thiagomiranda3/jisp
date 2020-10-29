package br.com.tommiranda.lang;

import br.com.tommiranda.exceptions.WrongArgumentsException;
import br.com.tommiranda.interpreter.Env;
import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.Symbol;

import java.util.List;

public class Procedure implements Func {

    private List<Symbol> params;
    private Object body;
    private Env env;

    public Procedure(List<Symbol> params, Object body, Env env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    @Override
    public Object exec(List<Object> arguments) {
        if (params.size() != arguments.size()) {
            throw new WrongArgumentsException(arguments.size() + " arguments passed to function, but only " + params.size() + " allowed");
        }

        Env funcEnv = new Env(params, arguments, env);

        return Evaluator.eval(body, funcEnv);
    }

    public List<Symbol> getParams() {
        return params;
    }

    public Object getBody() {
        return body;
    }

    public Env getEnv() {
        return env;
    }
}
