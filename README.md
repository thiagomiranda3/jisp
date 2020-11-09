# Jisp
Small Lisp expression interpreter made in Java

---

I created this project as a hobby to learn how to build an Interpreter. Right now the project has around 2400 lines of code.

As Lisp is one of the easiest languages to create, because of it's simple syntax, I decided to create a Scheme dialect in Java

I made this interpreter basing on [two][lispy] [articles][lispy2] from [Peter Norvig][Peter Norvig], where he creates a version of the Scheme in Python. 

The features of Jisp are:

1) You can execute the REPL or write your code in a file
2) Support for comments that begin with the character ;
2) Support for numeric types: Long, Double, BigInteger e BigDecimal
3) Automatic Long promotion for BigInteger and Double for BigDecimal if necessary
4) Tail Call Elimination if you use accumulators in recursive functions
5) Java Iterop: You can create and execute Java methods
6) Macro support

## Examples

### Math operations

```scheme
(+ 1 2 3 4) ==> 10

(/ (* 3.0 2) (- 10.0 5)) ==> 1.2

; PI with arbitrary number of decimal places
(pi 50) => 3.1415926535897932384626433832795028841971693993751
```

### Self promotion of numeric types

```scheme
(+ 3 (bigdec 2.5)) ==> 5.5

(define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))
(type (fact 10)) ==> class java.lang.Long
(type (fact 175)) ==> class java.math.BigInteger
```

### Syntax error warnings

```scheme
(define (mulDiv x y z) (/ (* x y) z))

(mulDiv 10 2 4) ==> 5.0
(mulDiv 1 2 3 4 5) ==> WrongArguments: mulDiv function expected (x y z), found (1 2 3 4 5)
```

###  Invoking Java classes and methods

```scheme
(define dados (new-object "java.util.HashMap" '()))
(invoke dados "put" '("nome" "thiago"))
(invoke dados "put" '("profissão" "programador"))

dados ==> {"nome": "thiago, "profissão": "programador"}
```

### Function Composition

```scheme
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
```

### Macros
```scheme
(define-macro unless (lambda args `(if (not ,(car args)) (begin ,@(cdr args)))))

(unless (= 4 (+ 1 1)) 3 4) ==> 4
```

## Running

To test Jisp, just compile it with maven and run jisp.jar. A REPL will appear for you to place Lisp expressions.

[Peter Norvig]: https://norvig.com/
[lispy]: https://norvig.com/lispy.html
[lispy2]: https://norvig.com/lispy2.html
