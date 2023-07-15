package com.example.physicsmate;

import org.junit.Test;
import org.matheclipse.core.eval.ExprEvaluator;

import static com.example.physicsmate.Custom_methods.*;
import static org.junit.Assert.assertEquals;
import static org.matheclipse.core.expression.F.HoldForm;

public class Custom_methodsTest {


    /**
     * Should simplify the expression after replacing 'x'
     */
    @Test
    public void evalAtPointXSymbolicSimplifiesExpression() {
        String input = "x^2 + 2*x + 1";
        double val = 2.0;
        String expected = "9";

        String result = Custom_methods.evalAtPointXSymbolic(input, "x", val);

        assertEquals(expected, result);
    }

    /**
     * Should replace 'x' with the fractional representation of the given value
     */
    @Test
    public void evalAtPointXSymbolicReplacesXWithFraction() {
        String function = "x^2 + 2*x + 1";
        double val = 0.5;

        String result = Custom_methods.evalAtPointXSymbolic(function, "x", val);

        assertEquals("9/4", result);
    }

    /**
     * Should return the symbolic evaluation at a given point
     */
    @Test
    public void evalAtPointXSymbolicReturnsSymbolicEvaluation() {
        String function = "x^2 + 2*x + 1";
        double val = 2.0;
        String expected = "9";
        String actual = Custom_methods.evalAtPointXSymbolic(function, "x", val);

        assertEquals(expected, actual);
    }

    /**
     * Should return correct fraction when decimal has no fraction digits
     */
    @Test
    public void convertDecimalToFractionWithNoFractionDigits() {
        double decimal = 5.0;
        String expectedFraction = "5";

        String actualFraction = Custom_methods.convertDecimalToFraction(decimal);

        assertEquals(expectedFraction, actualFraction);
    }

    /**
     * Should return correct fraction when decimal has multiple fraction digits
     */
    @Test
    public void convertDecimalToFractionWithMultipleFractionDigits() {
        double decimal = 3.14159;

        String result = Custom_methods.convertDecimalToFraction(decimal);

        assertEquals("314159/100000", result);
    }

    /**
     * Should convert decimal to fraction correctly
     */
    @Test
    public void convertDecimalToFraction() {        // Test case 1: Decimal number with 2 decimal places
        double decimal1 = 0.75;
        String expectedFraction1 = "3/4";
        String actualFraction1 = Custom_methods.convertDecimalToFraction(decimal1);
        assertEquals(expectedFraction1, actualFraction1);

        // Test case 2: Decimal number with 3 decimal places
        double decimal2 = 0.125;
        String expectedFraction2 = "1/8";
        String actualFraction2 = Custom_methods.convertDecimalToFraction(decimal2);
        assertEquals(expectedFraction2, actualFraction2);

        // Test case 3: Decimal number with 4 decimal places
        double decimal3 = 0.3333;
        String expectedFraction3 = "3333/10000";
        String actualFraction3 = Custom_methods.convertDecimalToFraction(decimal3);
        assertEquals(expectedFraction3, actualFraction3);

        // Test case 4: Decimal number with 0 decimal places
        double decimal4 = 2.0;
        String expectedFraction4 = "2";
        String actualFraction4 = Custom_methods.convertDecimalToFraction(decimal4);
        assertEquals(expectedFraction4, actualFraction4);

        // Test case 5: Decimal number with negative value
        double decimal5 = -0.5;
        String expectedFraction5 = "-1/2";
        String actualFraction5 = Custom_methods.convertDecimalToFraction(decimal5);
        assertEquals(expectedFraction5, actualFraction5);
    }

    @Test
    public void evalDefiniteIntegralSymbolicTester()
    {
        String res = evalDefiniteIntegralSymbolic("(log(x))^2 + log(x)", "x", 1.5, 2.5);
        System.out.println(res);
    }

    public String evalDefiniteIntegralSymbolic(String integral, CharSequence var, double lowerBound, double upperBound) //working
    {
        ExprEvaluator util = new ExprEvaluator();

        String upperval = evalAtPointXSymbolic(integral, var, upperBound);
        String lowerval = evalAtPointXSymbolic(integral, var, lowerBound);

        String expr = upperval + "-" + lowerval;

        String expanded = util.eval("Expand(" + expr + ")").toString();
        return util.eval("Simplify(ReplaceAll(" + expanded + ", {log(x_)+log(y_) -> log(x*y), log(x_)-log(y_) -> log(x/y), log(x_^y_) -> y*log(x_)}))").toString();
    }


}