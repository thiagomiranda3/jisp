package br.com.tommiranda;

import br.com.tommiranda.ast2.Environment;
import br.com.tommiranda.ast2.Evaluator;
import br.com.tommiranda.ast2.Parser;
import br.com.tommiranda.ast2.Tokenizer;
import com.google.gson.Gson;

import java.util.Deque;
import java.util.Scanner;

public class REPL {

    private final Gson gson = new Gson();
    private final Scanner scanner = new Scanner(System.in);

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
                Object result = Evaluator.eval(expr, new Environment());

                System.out.println("AST: " + gson.toJson(expr));
                System.out.println("EVAL: " + gson.toJson(result));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
