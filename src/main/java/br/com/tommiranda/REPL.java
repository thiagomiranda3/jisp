package br.com.tommiranda;

import br.com.tommiranda.interpreter.*;
import br.com.tommiranda.lang.Global;

import java.util.Deque;
import java.util.Scanner;

public class REPL {

    private final Scanner scanner = new Scanner(System.in);

    // (+
    // (hello)
    // (+ 1 2 3 4)
    // (/ (* 3 2) (- 10 5))
    // (* 2 3 (- 5 2))
    public void start() {
        Tokenizer tokenizer = new Tokenizer();

        while (true) {
            try {
                tokenizer.clean();

                System.out.print("user==> ");
                tokenizer.tokenize(scanner.nextLine());

                while (!tokenizer.isParseable()) {
                    tokenizer.tokenize(scanner.nextLine());
                }

                Deque<String> tokens = tokenizer.getTokens();
                Object expr = Parser.parse(tokens);
                Object result = Evaluator.eval(expr, Global.getGlobalEnv());

                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
