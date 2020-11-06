package br.com.tommiranda;

import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.Parser;
import br.com.tommiranda.interpreter.Tokenizer;
import br.com.tommiranda.lang.Global;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.List;

public class Reader {

    public static void read(String strPath) {
        try {
            Path path = Paths.get(strPath);

            List<String> lines = Files.readAllLines(path);

            Tokenizer tokenizer = new Tokenizer();

            for (String line : lines) {
                tokenizer.tokenize(line);

                if (tokenizer.isParseable()) {
                    Deque<String> tokens = tokenizer.getTokens();
                    Evaluator.evalExpanded(Parser.parse(tokens), Global.getEnv());
                    tokenizer.clean();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
