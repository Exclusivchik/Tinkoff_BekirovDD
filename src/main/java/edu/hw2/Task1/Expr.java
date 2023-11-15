package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double a) implements Expr {
        public double evaluate() {
            return a;
        }
    }

    record Negate(Constant a) implements Expr {
        public double evaluate() {
            return (-1) * a.evaluate();
        }
    }

    record Exponent(double a, int pow) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(a, pow);
        }
    }

    record Addition(double a, double b) implements Expr {
        public double evaluate() {
            return a + b;
        }
    }

    record Multiplication(double a, double b) implements Expr {
        public double evaluate() {
            return a * b;
        }
    }
}
