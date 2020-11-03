package br.com.tommiranda;

import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.Parser;
import br.com.tommiranda.interpreter.Tokenizer;
import br.com.tommiranda.lang.Global;

public class TestUtils {

    public static Object evalExpr(String input) {
        return Evaluator.evalExpanded(Parser.parse(new Tokenizer().tokenize(input)), Global.getEnv());
    }
}
