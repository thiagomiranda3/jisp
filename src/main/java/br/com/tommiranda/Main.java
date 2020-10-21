package br.com.tommiranda;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            new REPL().start();
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
