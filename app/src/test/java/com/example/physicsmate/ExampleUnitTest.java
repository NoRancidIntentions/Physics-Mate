package com.example.physicsmate;

import org.junit.Test;
import org.matheclipse.core.eval.ExprEvaluator;

import static com.example.physicsmate.Custom_methods.ExprSimplifier;

import static org.junit.Assert.assertEquals;
import static org.matheclipse.core.expression.F.eval;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void evalDefiniteIntegralSymbolicTester()
    {
        String a = evalDefiniteIntegralSymbolic("log(x)", "x", 1, 10);
        System.out.println("result: " + a);

    }

    private String evalDefiniteIntegralSymbolic(String integral, String var, double lowerBound, double upperBound) //working
    {
        String upperval = integral.replace(var, String.valueOf(upperBound));
        String lowerval = integral.replace(var, String.valueOf(lowerBound));

        System.out.println(ExprSimplifier(upperval + "-" + lowerval));
        return (ExprSimplifier(upperval + "-" + lowerval));
    }
}