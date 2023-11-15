package edu.hw2;

import static edu.hw2.Task1.Expr.Addition;
import static edu.hw2.Task1.Expr.Constant;
import static edu.hw2.Task1.Expr.Exponent;
import static edu.hw2.Task1.Expr.Multiplication;
import static edu.hw2.Task1.Expr.Negate;
import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Тест1")
    void test(){
        //when
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Expr.Constant(1));
        var sumTwoFour = new Addition(two.evaluate(), four.evaluate());
        var mult = new Multiplication(sumTwoFour.evaluate(), negOne.evaluate());
        var exp = new Exponent(mult.evaluate(), 2);
        var res = new Addition(exp.evaluate(), new Expr.Constant(1).evaluate());
        //then
        assertThat(res.evaluate()).isEqualTo(37);
//        System.out.println(res + " = " + res.evaluate());
    }
}
