package br.com.tommiranda;

import br.com.tommiranda.ast.Tokenizer;
import com.google.gson.Gson;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("user==> ");
            String expr = scanner.nextLine();

            List<String> tokens = tokenizer.tokenize(expr);

            System.out.println(gson.toJson(tokens));;
        }
    }

//    public static void main(String[] args) {
//        while (true) {
//            try {
//                System.out.print("user==> ");
//
//                Node node = new ASTGenerator().createAST();
//
//                System.out.println("AST: " + gson.toJson(node));
//
//                node = new Transformer().expand(node);
//
//                System.out.println("Exp: " + gson.toJson(node));
//
//                Object value = new Evaluator().eval(node);
//
//                System.out.println(value);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
}
