package com.example.physicsmate.activities;

import org.junit.Test;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class airResistanceDE
{
    //NDSolve({x'(t) == 10*(y(t) - x(t)), y'(t) == x(t)*(28 - z(t)) - y(t), z'(t) == x(t)*y(t) - 8/3*z(t), x(0)== 0, y(0) == 1, z(0) == 0}, {x, y, z}, {t, 0, 20});

    @Test
    public void DE1() {
        ExprEvaluator evaluator = new ExprEvaluator();
        IExpr model = (evaluator.eval("NDSolve({x'(t)==vx(t), y'(t) == vy(t), vx'(t)== -1*vx(t)*(vx(t)^2+vy(t)^2)^(1/2), vy'(t)== -10-1*vy(t)*(vx(t)^2+vy(t)^2)^(1/2), x(0)==0, y(0)==0, vx(0) == 20, vy(0) == 10}, {x,y,vx,vy}, {t,0,0.3})"));
        String outputString = model.toString();
        System.out.println(outputString);
        System.out.println();

        //from the outputstrig, extract the part between 'x' and 'y'
        String resultX = outputString.substring(outputString.indexOf("x-")+1, outputString.indexOf("y-"));
        String resultY = outputString.substring(outputString.indexOf("y-")+1, outputString.indexOf("vx-"));
        String resultVx = outputString.substring(outputString.indexOf("vx-")+1, outputString.indexOf("vy-"));
        String resultVy = outputString.substring(outputString.indexOf("vy-")+1, outputString.indexOf(")}"));

        System.out.println(resultX);
        System.out.println(resultY);
        System.out.println(resultVx);
        System.out.println(resultVy);

        //from a string of structure ->Interpolation(
        //{{0.0,1.0537425454595866},
        // {0.1,1.5337200654883274},
        // {0.2,1.854156859561965}}),
        // try to extract 1.0537425454595866, 1.5337200654883274, 1.854156859561965 and store them in an array

        List<Double> values = extractAndFilterXValues(resultX);

        System.out.println("Extracted X Values: " + values);

    }

    private static List<Double> extractAndFilterXValues(String input) {
        List<Double> xValues = new ArrayList<>();

        // Define the pattern for extracting numbers
        String pattern = "\\d+\\.\\d+";
        Pattern regex = Pattern.compile(pattern);

        // Use a Matcher to find all matches in the input string
        Matcher matcher = regex.matcher(input);

        // Iterate through matches and add them to the list
        int index = 0;
        while (matcher.find()) {
            // Check if the index is even (odd-indexed values are skipped)
            if (index % 2 == 1) {
                double value = Double.parseDouble(matcher.group());
                xValues.add(value);
            }
            index++;
        }

        return xValues;
    }
}
