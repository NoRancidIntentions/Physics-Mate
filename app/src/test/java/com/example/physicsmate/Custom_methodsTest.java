package com.example.physicsmate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.parser.client.math.MathException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.physicsmate.Custom_methods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class Custom_methodsTest {

    @Test
    public void replaceNumbersInTrigWithRemainder() {

        String input = "sin(7*pi)";
        Pattern pattern = Pattern.compile("(sin|cos|tan)\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String trigFunction = matcher.group(1); // sin, cos, or tan
            String argValueString = matcher.group(2);
            System.out.println("Arg val string: " + argValueString);
            System.out.println("trig function: " + trigFunction);
            assert argValueString != null;
            String argValue = argValueString.replaceAll("[()]", ""); // Remove parentheses before parsing
            System.out.println("Arg val:" + Custom_methods.evalf(argValue));
            double remainder = mod(Custom_methods.evalf(argValue), 2 * Math.PI);
            String modifiedTrig = trigFunction + "(" + remainder + ")";
            System.out.println("modified trig: " + modifiedTrig);
            input = input.replaceFirst(Pattern.quote(matcher.group()), modifiedTrig);
        }

        System.out.println("input after replacing numbers in trig with remainder: " + input);
    }

    @Test
    public void eval() {
        String str = "sin(7*pi)";
        ExprEvaluator util = new ExprEvaluator();
        try {
            System.out.println(Double.parseDouble(String.valueOf(util.eval(IExprConverter(str)))));
        } catch (MathException e) {
            System.out.println("Error");
        }
    }

    // Existing tests...

    @Test
    public void testReplaceNumbersInTrigWithRemainderWhenNumericArgumentThenRemainder() { //passed, ignore this
        // Arrange
        String input = "sin(7*pi)";
        String expected = "sin(3.14159)";

        // Act
        String result = Custom_methods.replaceNumbersInTrigWithRemainder(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testReplaceNumbersInTrigWithRemainderWhenNoTrigFunctionsThenInputUnchanged() {
        // Arrange
        String input = "7*pi";
        String expected = "7*pi";

        // Act
        String result = Custom_methods.replaceNumbersInTrigWithRemainder(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testReplaceNumbersInTrigWithRemainderWhenNonNumericArgumentThenException() {
        // Arrange
        String input = "sin(pi*x)";

        try {
            // Act
            Custom_methods.replaceNumbersInTrigWithRemainder(input);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Assert
            assertEquals("Invalid argument for trigonometric function", e.getMessage());
        }
    }

    @Test
    public void testEvalWhenExpressionValidAndIsRadTrueThenReturnsCorrectResult2() {
        // Arrange
        String expression = "sin(pi)";
        Boolean isRad = true;
        double expected = 0.0;

        // Act
        double result = Custom_methods.evalf(expression, isRad);

        // Assert
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testEvalWhenExpressionValidAndIsRadFalseThenReturnsCorrectResult() {
        // Arrange
        String expression = "sin(180)";
        Boolean isRad = false;
        double expected = 0.0;

        // Act
        double result = Custom_methods.evalf(expression, isRad);
        System.out.println("result: " + result);

        // Assert
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testEvalWhenExpressionInvalidThenReturnsDefaultResult() {
        // Arrange
        String expression = "invalid expression";
        Boolean isRad = true;
        double expected = 1536.786;

        // Act
        double result = Custom_methods.evalf(expression, isRad);

        // Assert
        assertEquals(expected, result, 0.001);
    }

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
    public void evalDefiniteIntegralSymbolicTester() {
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