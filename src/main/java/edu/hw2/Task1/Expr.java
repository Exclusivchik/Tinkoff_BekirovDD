package edu.hw2.Task1;

import static edu.hw2.Task1.Expr.Addition;
import static edu.hw2.Task1.Expr.Constant;
import static edu.hw2.Task1.Expr.Exponent;
import static edu.hw2.Task1.Expr.Multiplication;
import static edu.hw2.Task1.Expr.Negate;

public sealed interface Expr permits Addition, Constant, Exponent, Multiplication, Negate {
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
            double res = 1;
            for (int i = 0; i < pow; i++) {
                res *= a;
            }
            return res;
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
