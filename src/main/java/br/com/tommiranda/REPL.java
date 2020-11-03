package br.com.tommiranda;

import br.com.tommiranda.interpreter.*;
import br.com.tommiranda.lang.Global;
import br.com.tommiranda.utils.Util;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class REPL {

    private final Scanner scanner = new Scanner(System.in);

    // (+
    // (hello)
    // (+ 1 2 3 4)
    // (/ (* 3 2) (- 10 5))
    // (* 2 3 (- 5 2))
    public void start() {
        Tokenizer2 tokenizer = new Tokenizer2();

        while (true) {
            try {
                tokenizer.clean();

                System.out.print("user==> ");
                tokenizer.tokenize(scanner.nextLine());

                while (!tokenizer.isParseable()) {
                    tokenizer.tokenize(scanner.nextLine());
                }

                Deque<String> tokens = tokenizer.getTokens();
                Object result = Evaluator.evalExpanded(Parser.parse(tokens), Global.getEnv());

                String stringExpr = ExprFormater.format(result);
                if (Util.stringOK(stringExpr)) {
                    System.out.println(stringExpr);
                }
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}
