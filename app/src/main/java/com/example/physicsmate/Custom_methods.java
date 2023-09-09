package com.example.physicsmate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.airbnb.paris.Paris;
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import io.github.kexanie.library.MathView;
import org.jetbrains.annotations.NotNull;
import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.eval.TeXUtilities;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.expression.S;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.lab4inf.math.util.Accuracy.round;

public class Custom_methods {
    /**
     * Returns the number of fraction digits in the given number.
     *
     * @param number The number to check.
     * @return The number of fraction digits.
     */
    public static int getNumberOfFractionDigits(@org.jetbrains.annotations.Nullable Number number) {
        if (number == null) {
            return 0; // If the number is null, there are no fraction digits.
        }

        if (number.doubleValue() == 0.0d) {
            return 0; // If the number is zero, there are no fraction digits.
        }

        BigDecimal bd = new BigDecimal(number.toString());
        bd = bd.stripTrailingZeros(); // Remove trailing zeros to get the correct scale.
        return bd.scale();
    }


    //method to round a number to the least number of decimal places from all the edit texts
    /**
     * Returns a rounded string representation of the given number, rounded to the least number
     * of decimal places from all the EditTexts.
     *
     * @param t         the number to round
     * @param editTexts the EditTexts containing the numbers
     * @return a rounded string representation of the number
     */
    public static String rounded_String(double t, EditText... editTexts) {
        int[] precision = new int[editTexts.length];
        for (int i = 0; i < editTexts.length; i++) {
            precision[i] = getNumberOfFractionDigits(Double.parseDouble(editTexts[i].getText().toString()));
        }
        int min = precision[0];
        for (int j : precision) {
            if (j < min) min = j;
        }
        return String.valueOf(round(t, min));
    }

    /**
     * Evaluates a mathematical expression and returns the result as a double.
     *
     * @param  str  the mathematical expression to be evaluated
     * @return      the result of the evaluation as a double
     */
    public static double eval(@NotNull final String str) {
        ExprEvaluator util = new ExprEvaluator();
        try {
            return Double.parseDouble(String.valueOf(util.eval(IExprConverter(str))));
        } catch (MathException e) {
            return 1536.786;
        }
    }

    /**
     * Sets up an EditText change listener for a given Button and multiple EditTexts.
     *
     * @param  button      the Button that will be enabled or disabled based on the EditTexts' contents
     * @param  editTexts   the EditTexts to be monitored for changes
     */
    public static void setupEditTextChangeListener(Button button, EditText ...editTexts) {

        // Check if any of the EditTexts are empty
        boolean anyEditTextEmpty = false;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                anyEditTextEmpty = true;
                break;
            }
        }

        // Enable or disable the button based on EditTexts' contents
        button.setEnabled(!anyEditTextEmpty);

        if (anyEditTextEmpty) {
            Paris.styleBuilder(button).add(R.style.custom_button_disabled).apply();
        } else {
            //use paris style builder to set the background
            Paris.styleBuilder(button).add(R.style.custom_button_enabled).apply();
        }

        for (EditText editText : editTexts) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Check if any of the EditTexts are empty
                    boolean anyEditTextEmpty = false;
                    for (EditText editText : editTexts) {
                        if (editText.getText().toString().isEmpty()) {
                            anyEditTextEmpty = true;
                            break;
                        }
                    }

                    // Enable or disable the button based on EditTexts' contents
                    button.setEnabled(!anyEditTextEmpty);

                    if (anyEditTextEmpty) {
                        Paris.styleBuilder(button).add(R.style.custom_button_disabled).apply();
                    } else {
                        //use paris style builder to set the background
                        Paris.styleBuilder(button).add(R.style.custom_button_enabled).apply();
                    }

                }
            });
        }
    }

    /**
     * Replaces constants in the input string with their corresponding values.
     *
     * @param  input   the input string to be processed
     * @return         the processed string with constants replaced
     */
    public static String replaceConstants(String input) {
        // Replace "pi" with "Pi"
        input = input.replaceAll("pi", String.valueOf(Math.PI));

        // Replace "e" with "E"
        input = input.replaceAll("e", String.valueOf(Math.E));

        // Replace phi with Phi
        input = input.replaceAll("phi", String.valueOf(1.61803398874989484820458683436563811772030917980576286213544862270526046281890));

        //replace log10 with ln equivalent
        input = input.replaceAll("log10\\((.*?)\\)", "ln($1)/ln(10)");

        return input;

    }

    //set up the edittext to use the custom keyboard
    public static void setupEditTextForCustomKeyboard(custom_keyboard1_initializer custom_keyboard1, EditText ...editText) {
        for (EditText editText1 : editText) {
            editText1.setShowSoftInputOnFocus(false);
            editText1.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    custom_keyboard1.setTargetEditText(editText1);
                    custom_keyboard1.showKeyboard();
                } else {
                    custom_keyboard1.hideKeyboard();
                }
            });
        }
    }

    /**
     * Removes null values from the given array and returns a new array
     * without the null values.
     *
     * @param  array  the array from which to remove null values
     * @return        the new array without null values
     */
    public static String[] removeNullValues(String[] array) {
        List<String> resultList = new ArrayList<>();

        for (String element : array) {
            if (element != null) {
                resultList.add(element);
            }
        }

        String[] resultArray = new String[resultList.size()];
        return resultList.toArray(resultArray);
    }

    //method to find number of extremas of a polynomial
    /**
     * This method finds the number of extrema of a polynomial.
     *
     * @param function the polynomial function
     * @param var the variable of the polynomial function
     * @return an array of strings representing the extremas as (x, y) pairs
     */
    public static String[] ExtremasFinder(String function, String var) {
        ExprEvaluator evaluator = new ExprEvaluator();
        try {
            IExpr inputExpr = evaluator.parse(function);
            IExpr x = evaluator.parse(var);

            IExpr firstDerivative = F.D(inputExpr, x);

            //create IAST equation for first derivative = 0
            IAST equation1 = F.Equal(firstDerivative, 0);

            IExpr[] Extremas = F.solve(equation1, S.x);

            //check if x is point of inflection
            IExpr secondDerivative = F.D(firstDerivative, x);
            IAST equation2 = F.Equal(secondDerivative, 0);
            IExpr[] InflectionPoints = F.solve(equation2, S.x);

            //remove inflection points from extremas
            for (IExpr inflectionPoint : InflectionPoints) {
                for (int j = 0; j < Extremas.length; j++) {
                    if (inflectionPoint.equals(Extremas[j])) {
                        Extremas[j] = null;
                    }
                }
            }

            //convert IExpr[] to String[]
            String[] ExtremasString = new String[Extremas.length];

            for (int i = 0; i < Extremas.length; i++) {
                ExtremasString[i] = Extremas[i].toString();
            }

            //remove null values from ExtremasString
            ExtremasString = removeNullValues(ExtremasString);

            if (ExtremasString.length == 0) {
                return null;
            } else {

                //get the x values of the extremas
                double[] ExtremasX = new double[ExtremasString.length];
                for (int i = 0; i < ExtremasString.length; i++) {
                    ExtremasX[i] = F.eval(ExtremasString[i]).evalDouble();
                }

                double[] ExtremasY = new double[ExtremasString.length];

                for (int i = 0; i < ExtremasX.length; i++) {
                    double ExtremaX = ExtremasX[i];
                    ExtremasY[i] = evalAtPointX(function, ExtremaX);
                }

                String[] pairs = new String[ExtremasX.length];

                for (int i = 0; i < ExtremasX.length; i++) {
                    pairs[i] = "(" + ExtremasX[i] + ", " + ExtremasY[i] + ")";
                }

                return pairs;
            }

        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] findNumberOfExtremas(String function, String var) {  //tested well
        ExprEvaluator evaluator = new ExprEvaluator();
        try {
            IExpr inputExpr = evaluator.parse(function);
            IExpr x = evaluator.parse(var);

            IExpr firstDerivative = F.D(inputExpr, x);
            IExpr secondDerivative = F.D(firstDerivative, x);

            String stringSecondDerivative = secondDerivative.toString();

            //create IAST equation for first derivative = 0
            IAST equation1 = F.Equal(firstDerivative, 0);

            IExpr[] Extremas = F.solve(equation1, S.x);
            //convert IExpr[] to String[]
            String[] ExtremasString = new String[Extremas.length];

            for (int i = 0; i < Extremas.length; i++) {
                if (Extremas[i] != null) {
                    ExtremasString[i] = Extremas[i].toString();
                }
            }

            //get the x values of the extremas
            double[] ExtremasX = new double[ExtremasString.length];
            for (int i = 0; i < ExtremasString.length; i++) {
                ExtremasX[i] = F.eval(ExtremasString[i]).evalDouble();
            }

            double[] SecDerY = new double[ExtremasString.length];

            for (int i = 0; i < ExtremasX.length; i++) {
                double ExtremaX = ExtremasX[i];
                SecDerY[i] = evalAtPointX(stringSecondDerivative, ExtremaX);
            }

            int[] numOfExtremas = new int[ExtremasString.length];
            int Maximas = 0;
            int Minimas = 0;
            for (int i = 0; i < ExtremasString.length; i++) {
                if (Double.parseDouble(String.valueOf(F.eval(String.valueOf(SecDerY[i])))) > 0) {
                    Minimas++;
                } else if (Double.parseDouble(String.valueOf(F.eval(String.valueOf(SecDerY[i])))) < 0) {
                    Maximas++;
                }
            }
            numOfExtremas[0] = Maximas;
            numOfExtremas[1] = Minimas;

            return numOfExtremas;


        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double evalAtPointX(@NotNull final String str, Double val) {
        ExprEvaluator util = new ExprEvaluator(false, (short) 10);
        try {
            IExpr variableSetter = util.eval("x=" + val);
            double result = util.evalf(str);

            return result;

        } catch (SyntaxError e) {
            // catch Symja parser errors here
            return 1536.786;
        } catch (MathException me) {
            // catch Symja math errors here
            return 1536.786;
        } catch (Exception e) {
            return 1536.786;
        }
    }

    public static String evalAtPointXSymbolic(@NotNull final String str, CharSequence var, Double val) {
        //replace x with val. If val is in decimal, convert it to fraction
        String frac = convertDecimalToFraction(val);
        String str1 = str.replace(var, frac);

        return ((ExprSimplifier(str1))).toLowerCase();
    }

    public static String replaceFractionalValues(CharSequence expression) {
        Pattern pattern = Pattern.compile("\\d+/\\d+");
        Matcher matcher = pattern.matcher(expression);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String fraction = matcher.group();
            String[] parts = fraction.split("/");
            double numerator = Double.parseDouble(parts[0]);
            double denominator = Double.parseDouble(parts[1]);
            double decimalValue = numerator / denominator;
            String replacement = Double.toString(decimalValue);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }


    //method to convert decimal to fraction
    public static String convertDecimalToFraction(double decimal) {
        int precision = getNumberOfFractionDigitsSym(decimal);
        int denominator = (int) Math.pow(10, precision);
        int numerator = (int) (decimal * denominator);
        int gcd = getGCD(numerator, denominator);
        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    public static int getNumberOfFractionDigitsSym(double number) {
        String numberString = Double.toString(Math.abs(number));
        int indexOfDecimal = numberString.indexOf(".");

        if (indexOfDecimal >= 0 && indexOfDecimal < numberString.length() - 1) {
            return numberString.length() - indexOfDecimal - 1;
        } else {
            return 0;
        }
    }

    public static String reduceLogTerms(String expression) {
        // Regular expression pattern to match log terms
        String pattern = "log\\(([^\\)]+)\\)";

        // Create a pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a matcher object with the input expression
        Matcher matcher = regex.matcher(expression);

        StringBuffer output = new StringBuffer();

        // Iterate through the matches
        while (matcher.find()) {
            // Get the log term
            String logTerm = matcher.group(1);

            // Split the log term by multiplication
            String[] terms = logTerm.split("\\*");

            // Simplify the log terms
            double result = 0;
            for (String term : terms) {
                result += Math.log(Double.parseDouble(term));
            }

            // Replace the log term with the simplified version in the output
            String replacement = "log(" + result + ")";
            matcher.appendReplacement(output, replacement);
        }

        // Append the remaining part of the input to the output
        matcher.appendTail(output);

        return output.toString();
    }

    public static String simplifyFractionsInsideLog(String input) {
        // Regular expression pattern to match the fraction inside log
        String pattern = "log\\((\\d+)/(\\d+)\\)";

        // Create a pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a matcher object with the input string
        Matcher matcher = regex.matcher(input);

        StringBuffer output = new StringBuffer();

        // Iterate through the matches
        while (matcher.find()) {
            // Get the numerator and denominator
            int numerator = Integer.parseInt(matcher.group(1));
            int denominator = Integer.parseInt(matcher.group(2));

            // Simplify the fraction
            int gcd = getGCD(numerator, denominator);
            int simplifiedNumerator = numerator / gcd;
            int simplifiedDenominator = denominator / gcd;

            // Replace the fraction with the simplified version in the output
            String replacement = "log(" + simplifiedNumerator + "/" + simplifiedDenominator + ")";
            matcher.appendReplacement(output, replacement);
        }

        // Append the remaining part of the input to the output
        matcher.appendTail(output);

        return output.toString();
    }

    public static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGCD(b, a % b);
        }
    }

    @SafeVarargs
    public static void addArrayListEntries(String input, float xmin, float xmax, ArrayList<Entry>... inputLineArrays) {


        for (ArrayList<Entry> inputLineArray : inputLineArrays) {

            int numThreads = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            for (float lineChartX = xmin; lineChartX < xmax; lineChartX = lineChartX + ((xmax - xmin) / 1000)) {
                float finalLineChartX = lineChartX;
                float finalLineChartX1 = lineChartX;
                executor.submit(() -> {
                    Entry entry = new Entry(finalLineChartX, (float) evalAtPointX(input, (double) finalLineChartX1));
                    synchronized (inputLineArray) {
                        //add entry to the array only if y is not equal to 1536.786 +- 0.1
                        if (Math.abs(entry.getY() - 1536.786) > 0.1) {
                            inputLineArray.add(entry);
                        }

                        inputLineArray.sort((e1, e2) -> Float.compare(e1.getX(), e2.getX()));

                    }
                });
            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static String calculateNthDerivative(String function, String var, int n) {
        ExprEvaluator evaluator = new ExprEvaluator();
        try {
            IExpr inputExpr = evaluator.parse(function);
            IExpr x = evaluator.parse(var);

            for (int i = 0; i < n; i++) {
                inputExpr = F.D(inputExpr, x);
            }

            IExpr result = evaluator.eval(inputExpr);
            return result.toString();
        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String calculateNthIntegral(String function, String var, int n) {
        ExprEvaluator evaluator = new ExprEvaluator();
        try {
            IExpr inputExpr = evaluator.parse(function);
            IExpr x = evaluator.parse(var);

            for (int i = 0; i < n; i++) {
                inputExpr = F.Integrate(inputExpr, x);
            }

            IExpr result = evaluator.eval(inputExpr);
            return result.toString();
        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String ExprSimplifier(String function) {
        ExprEvaluator evaluator = new ExprEvaluator();
        IExpr result = evaluator.eval("Simplify(" + function + ")");
        return result.toString();
    }

    public static IExpr IExprConverter(String function) {
        ExprEvaluator evaluator = new ExprEvaluator();
        return evaluator.parse(function);

    }

    //method to calculate the nth integral of a function

    /**
     * Retrieves the LaTeX representation of a mathematical function.
     *
     * @param  function  the mathematical function to convert to LaTeX
     * @return           the LaTeX representation of the function
     */
    public static String getTex(String function) {
        try {
            // false -> switch to Mathematica syntax mode:
            EvalEngine engine = new EvalEngine(false);
            //
            TeXUtilities texUtil = new TeXUtilities(engine, false);

            StringWriter stw = new StringWriter();
            texUtil.toTeX(IExprConverter(function), stw);
            // print: \sum_{i = 1}^{n}i
            return "$$ \\color{#D0D0D0}{" + stw + "}$$";
            //return latex.replace("\\", "\\\\");

        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static void MathJaxConfig(MathView... mathViews) {

        for (MathView mathView : mathViews) {
            mathView.config(
                    "MathJax.Hub.Config({\n" +
                            "  \"HTML-CSS\" : {\n" +
                            "        webFont : \"Latin-Modern\",\n" +
                            "        linebreaks: { automatic: true },\n" +
                            "    }" +
                            "});");
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    public static void WebViewLoader(@NotNull WebView webView, String url) {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    public static double DoubleNanChecker(double ACangle) {
        if (Double.isNaN(ACangle)) {
            return 0.0;
        }
        return ACangle;
    }

    @SafeVarargs
    public static void mChartDataSetter(@NotNull LineChart mChart, @NotNull Context context, Boolean ShouldBeFilled, @NotNull ArrayList<Entry>... yValues) {

        for (ArrayList<Entry> yVals : yValues) {
            LineDataSet set1 = new LineDataSet(yVals, "");

            set1.setFillAlpha(110);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.7f);
            set1.setColor(Color.WHITE);
            set1.setValueTextColor(Color.WHITE);
            set1.setValueTextSize(15f);

            set1.setDrawFilled(ShouldBeFilled);
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.rgb(30, 30, 30));
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            mChart.setData(data);
            mChart.invalidate();
        }

        mChart.setVisibility(View.VISIBLE);
    }

    @NotNull
    public static String rounded_StringNLOG(double t) {
        if (getNumberOfFractionDigits(t) <= 4) {
            return String.valueOf(t);
        } else {
            DecimalFormat df = new DecimalFormat("###.#########E0");
            df.setRoundingMode(RoundingMode.CEILING);
            return (t == (int) t ? String.valueOf((int) t) : df.format(t));
        }

    }

    public static void TextChangeListeners(@Nullable TextView tvresult, @NotNull EditText... editTexts) {

        for (EditText editText : editTexts) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    assert tvresult != null;
                    tvresult.setVisibility(View.GONE);
                    tvresult.setText(null);
                }
            });
        }
    }

    //eval function
        /* public static double eval(final String str)
        {
            return new Object()
            {
                int pos = -1, ch;

                void nextChar()
                {
                    ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                }

                boolean eat(int charToEat)
                {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat)
                    {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse()
                {
                    nextChar();
                    double x = parseExpression();
                    if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)`
                //        | number | functionName factor | factor `^` factor

                double parseExpression()
                {
                    double x = parseTerm();
                    for (; ; )
                    {
                        if (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm()
                {
                    double x = parseFactor();
                    for (; ; )
                    {
                        if (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else if (eat('%')) x %= parseFactor(); // remainder
                        else return x;
                    }
                }

                double parseFactor()
                {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('('))
                    { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.')
                    { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(str.substring(startPos, this.pos));
                    } else if (ch >= 'a' && ch <= 'z')
                    { // functions
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = str.substring(startPos, this.pos);
                        x = parseFactor();
                        switch (func)
                        {
                            case "sqrt":
                                x = Math.sqrt(x);
                                break;
                            case "sin":
                                x = Math.sin(Math.toRadians(x));
                                break;
                            case "cos":
                                x = Math.cos(Math.toRadians(x));
                                break;
                            case "tan":
                                x = Math.tan(Math.toRadians(x));
                                break;
                            case "log":
                                x = Math.log10(x);
                                break;
                            case "ln":
                                x = Math.log(x);
                                break;
                            default:
                                throw new RuntimeException("Unknown function: " + func);
                        }
                    } else
                    {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }
                    return x;
                }
            }.parse();
        } */

    public static void et_Scroller(@NotNull EditText etAi, Context context) {
        etAi.setScroller(new Scroller(context));
        etAi.setMaxLines(1);
        etAi.setHorizontalScrollBarEnabled(true);
        etAi.setHorizontallyScrolling(true);
        etAi.setMovementMethod(new ScrollingMovementMethod());

    }

    public static void CB_Incrementer_Till_Max(@NotNull CheckBox CB_acc, int[] a, int max_CB_Required) {
        if (CB_acc.isChecked() && a[0] < max_CB_Required) {
            a[0]++;
        }
    }

    public static void CB_LessThanMaxThen(@NotNull Button btn_next, @NotNull Button btn_clear, int @NotNull [] a, int max_CB_Required, @NotNull CheckBox... checkBoxes) {
        if (a[0] < max_CB_Required) {
            for (CheckBox CB : checkBoxes) {
                CB.setClickable(true);
            }
            btn_next.setClickable(false);
            btn_next.setBackgroundResource(R.drawable.btn_disabled);
            btn_clear.setVisibility(View.VISIBLE);
        }
    }

    public static void CB_disable_if3(@NotNull CheckBox CB_acc) {
        if (!CB_acc.isChecked()) {
            CB_acc.setClickable(false);
        }
    }

    public static void CB_MainMethod(@NotNull Button btn_next, @NotNull Button btn_clear, int @NotNull [] a, int max_CB_Required, @NotNull CheckBox... CheckBoxes) {

        for (CheckBox CB : CheckBoxes) {
            CB_Incrementer_Till_Max(CB, a, max_CB_Required);
        }

        if (a[0] == max_CB_Required) {
            btn_clear.setVisibility(View.VISIBLE);
            btn_next.setClickable(true);
            btn_next.setBackgroundResource(R.drawable.btn_enabled);

            for (CheckBox CB : CheckBoxes) {
                CB_disable_if3(CB);
            }
        }

        if (a[0] == 0) {
            btn_clear.setVisibility(View.GONE);
        }

        for (CheckBox CB : CheckBoxes) {
            CB_LessThanMaxThen(btn_next, btn_clear, a, max_CB_Required, CB);
        }

        a[0] = 0;
    }

    @NotNull
    public static ArrayList<Entry> mChartProperties(@NotNull LineChart mChart) {
        mChart.setHardwareAccelerationEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(15f);

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        yAxis.setTextSize(15f);

        mChart.setHighlightPerDragEnabled(false);
        mChart.setHighlightPerTapEnabled(false);
        mChart.setGridBackgroundColor(Color.WHITE);

        return new ArrayList<>();
    }
}
