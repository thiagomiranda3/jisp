package br.com.tommiranda;

import br.com.tommiranda.ast.ASTGenerator;
import br.com.tommiranda.ast.Node;
import com.google.gson.Gson;

public class Main {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("\nuser:==> ");

                Node node = new ASTGenerator().createAST();

                System.out.println(gson.toJson(node));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
