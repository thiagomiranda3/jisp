# Jisp
Pequeno interpretador de expressões Lisp

---

Esse é um projeto que eu fiz como hobby com a intenção de aprender como criar um interpretador.

Como um Lisp é uma das linguagens mais simples de se implementar, pela sua sintaxe simples, decidi criar uma versão da linguagem Scheme.

Fiz esse interpretador me baseando em [dois][lispy] [artigos][lispy2] do [Peter Norvig][Peter Norvig], onde ele cria uma versão do Scheme em Python:

As funcionalidades do Jisp são:

1) REPL ou leitura de um arquivo com as expressões
2) Suporte a comentários que começam com o caracter ;
2) Suporte aos tipos númericos: Long, Double, BigInteger e BigDecimal
3) Promoção automática de Long para BigInteger e Double para BigDecimal caso seja necessário
4) Tail Call Elimination se utilizar acumuladores nas funções recursivas
5) Java Iterop: Pode criar objetos e invokar métodos
6) Suporte a Macros

## Exemplos

```scheme
; Soma
(+ 1 2 3 4) ==> 10

; Auto promoção de tipos numéricos
(+ 3 (bigdec 2.5)) ==> 5.5

(define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))
(type (fact 10)) ==> class java.lang.Long
(type (fact 175)) ==> class java.math.BigInteger

; PI com número de casas arbitrárias
(pi 50) => 3.1415926535897932384626433832795028841971693993751

; Avisos de erro de sintaxe
(define (mulDiv x y z) (/ (* x y) z))

(mulDiv 10 2 4) ==> 5.0
(mulDiv 1 2 3 4 5) ==> WrongArguments: mulDiv function expected (x y z), found (1 2 3 4 5)

; Invocando classes e métodos do Java
(define dados (new-object "java.util.HashMap" '()))
(invoke dados "put" '("nome" "thiago"))
(invoke dados "put" '("profissão" "programador"))

dados ==> ; {"nome": "thiago, "profissão": "programador"}

; Composição de Funções
(define compose (lambda (f g) (lambda (x) (f (g x)))))

(define repeat (lambda (f) (compose f f)))

(define combine (lambda (f)
    (lambda (x y)
      (if (empty? x) (quote ())
          (f (list (car x) (car y))
             ((combine f) (cdr x) (cdr y)))))))

(define zip (combine cons))

(define riff-shuffle (lambda (deck) (begin
    (define take (lambda (n seq) (if (<= n 0) (quote ()) (cons (car seq) (take (- n 1) (cdr seq))))))
    (define drop (lambda (n seq) (if (<= n 0) seq (drop (- n 1) (cdr seq)))))
    (define mid (lambda (seq) (/ (length seq) 2)))
    ((combine append) (take (mid deck) deck) (drop (mid deck) deck)))))

((repeat riff-shuffle) (list 1 2 3 4 5 6 7 8)) ==> (1 3 5 7 2 4 6 8)

; Macros
(define-macro unless (lambda args `(if (not ,(car args)) (begin ,@(cdr args)))))

(unless (= 4 (+ 1 1)) 3 4) ==> 4
```

## Rodando

Para testar o Jisp, basta compilá-lo com o maven e rodar o jisp.jar. Um REPL vai aparecer para que você coloque expressões Lisp.

[Peter Norvig]: https://norvig.com/
[lispy]: https://norvig.com/lispy.html
[lispy2]: https://norvig.com/lispy2.html
