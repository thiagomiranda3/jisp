package br.com.tommiranda;

import br.com.tommiranda.ast.ASTGenerator;
import br.com.tommiranda.ast.Node;
import com.google.gson.Gson;

import java.util.Scanner;

public class Main {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String exp = scanner.nextLine();

        var generator = new ASTGenerator();

        Node node = generator.initialeNodes(exp);

        System.out.println(gson.toJson(node));
    }

    public static long sum(long... valores) {
        if (valores == null || valores.length == 0) {
            throw new IllegalStateException("É necessário passar ao menos um parâmetro para o operador +");
        }

        long resultado = 0;
        for (long valor : valores) {
            resultado += valor;
        }

        return resultado;
    }
}
