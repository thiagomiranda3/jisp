package br.com.tommiranda;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            new REPL().start();
        }
    }
}
