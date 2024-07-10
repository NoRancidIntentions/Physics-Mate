package com.example.physicsmate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.physicsmate.Custom_methods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class Custom_methodsTest {

    public static String solveEquationSymbolic(ArrayList<String> equation, ArrayList<String> variables) {
        try {
            //store the eqns in string form: "{eqn1, eqn2, eqn3, ...}"
            String eqns = "{";
            for (int i = 0; i < equation.size(); i++) {
                eqns = eqns + equation.get(i) + ",";
            }
            eqns = eqns.substring(0, eqns.length() - 1) + "}";

            //replace all "=" with "=="
            eqns = eqns.replaceAll("=", "==");
            System.out.println("eqns:" + eqns);

            //store the variables in string form: "{x, y, z, ...}"
            String vars = "{";
            for (int i = 0; i < variables.size(); i++) {
                vars = vars + variables.get(i) + ",";
            }
            vars = vars.substring(0, vars.length() - 1) + "}";
            System.out.println("vars:" + vars);

            //solve the eqns
            ExprEvaluator evaluator = new ExprEvaluator();
            IExpr result = evaluator.eval("Solve(" + eqns + "," + vars + ")");
            return result.toString();

        } catch (SyntaxError e) {
            // catch Symja parser errors here
            return "Syntax error";
        } catch (MathException me) {
            // catch Symja math errors here
            return "Math error";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";

    }

    @Test
    public void formatTextTest() {
        String input = "Curvature at t = 0.0 is 1.0";
        String result = Custom_methods.formatText(input);
        System.out.println(result);
    }

    @Test
    public void solveEquationSymbolicTester() {
        ArrayList<String> eqns = new ArrayList<>();
        eqns.add("x^2 + y^2 + z^2 = 2");
        eqns.add("x + y = 2");
        eqns.add("x + z = 3");
        ArrayList<String> vars = new ArrayList<>();
        vars.add("x");
        vars.add("y");
        vars.add("z");
        System.out.println(solveEquationSymbolic(eqns, vars));
    }

    public static String solveEquationNumeric(ArrayList<String> equation, ArrayList<String> variables) {
        try {
            //store the eqns in string form: "{eqn1, eqn2, eqn3, ...}"
            String eqns = "{";
            for (int i = 0; i < equation.size(); i++) {
                eqns = eqns + equation.get(i) + ",";
            }
            eqns = eqns.substring(0, eqns.length() - 1) + "}";
            eqns = eqns.replaceAll("=", "==");
            System.out.println("eqns:" + eqns);

            //store the variables in string form: "{x, y, z, ...}"
            String vars = "{";
            for (int i = 0; i < variables.size(); i++) {
                vars = vars + variables.get(i) + ",";
            }
            vars = vars.substring(0, vars.length() - 1) + "}";
            System.out.println("vars:" + vars);

            //solve the eqns
            ExprEvaluator evaluator = new ExprEvaluator();
            IExpr result = evaluator.eval("NSolve(" + eqns + "," + vars + ")");
            return String.valueOf(result);

        } catch (SyntaxError e) {
            // catch Symja parser errors here
            return "Syntax error";
        } catch (MathException me) {
            // catch Symja math errors here
            return "Math error";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";

    }

    @Test
    public void solveEquationNumericTester() {

        ArrayList<String> eqns = new ArrayList<>();
        eqns.add("z+x = 2");
        eqns.add("x + y = 3");
        eqns.add("y + z = 4");
        ArrayList<String> vars = new ArrayList<>();
        vars.add("x");
        vars.add("y");
        vars.add("z");
        System.out.println(solveEquationNumeric(eqns, vars));
    }

    @Test
    public void tempsolve() {
        //v1*sin(b1)==u1*sin(a1)
        //v2*sin(b2)==u2*sin(a2)
        //v2*cos(b2) - v1*cos(b1) == e*(u1*cos(a1) + u2*cos(a2))
        //m1*v1*cos(b1)+m2*v2*cos(b2)==m1*u1*cos(a1)+m2*u2*cos(a2)

        ArrayList<String> eqns = new ArrayList<>();
        eqns.add("v1*sin(b1)==u1*sin(a1)");
        eqns.add("v2*sin(b2)==u2*sin(a2)");
        eqns.add("v2*cos(b2) - v1*cos(b1) == e*(u1*cos(a1) - u2*cos(a2))");
        eqns.add("m1*v1*cos(b1) + m2*v2*cos(b2) == m1*u1*cos(a1) + m2*u2*cos(a2)");
        ArrayList<String> vars = new ArrayList<>();
        vars.add("v1");
        vars.add("v2");
        vars.add("b1");
        vars.add("b2");

        System.out.println(solveEquationNumeric(eqns, vars));

    }

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

    @Test
    public void evalf() {
        String str = "sin(7*pi)";
        System.out.println(Custom_methods.evalf(str));
    }

    @Test
    public void evalf2() {
        String str = "sin(180+90)";
        System.out.println(Custom_methods.evalf(str));
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
        double result = Custom_methods.evalf(expression);

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
        double result = Custom_methods.evalf(expression);
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
        double result = Custom_methods.evalf(expression);

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