package br.com.tommiranda;

import br.com.tommiranda.ast.ASTGenerator;
import br.com.tommiranda.ast.Node;
import br.com.tommiranda.eval.Evaluator;
import com.google.gson.Gson;

public class Main {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("\nuser==> ");

                Node node = new ASTGenerator().createAST();

                //System.out.println("AST: " + gson.toJson(node));

                Object value = new Evaluator().evaluateTree(node);

                System.out.println(value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
