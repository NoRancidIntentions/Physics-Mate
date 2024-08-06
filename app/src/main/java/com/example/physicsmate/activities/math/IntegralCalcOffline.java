package com.example.physicsmate.activities.math;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;
import org.matheclipse.core.eval.ExprEvaluator;

import static com.example.physicsmate.Custom_methods.*;
import static org.matheclipse.core.expression.F.HoldForm;

public class IntegralCalcOffline extends AppCompatActivity {
    EditText inputFunction, inputVar, nthDer, minX, maxX, etLowerBound, etUpperBound;
    CheckBox cbDefiniteIntegral, cbNumericEvaluation;
    TextView result, simpResult, derInttvMax, derInttvMin, DerInttv3, DerInttv2, DerInttv1, tvLowerBound, tvUpperBound, tvDefInt;

    Button calculateDerivative, btnGraph, btnGraphInfo, btnCopy, btnCopyDefInt;

    MathView latexView1, latexView2, latexView3;
    ScrollView derIntSymjaSV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative_integral_calc_offline);

        inputFunction = findViewById(R.id.input_function);
        nthDer = findViewById(R.id.nth_der);
        inputVar = findViewById(R.id.input_var);

        calculateDerivative = findViewById(R.id.calculate_derivative);
        btnGraph = findViewById(R.id.btnGraph);
        btnGraphInfo = findViewById(R.id.btnGraphInfo);
        btnCopy = findViewById(R.id.btnCopy);
        result = findViewById(R.id.result);
        simpResult = findViewById(R.id.simp_result);
        latexView1 = findViewById(R.id.latexView1);
        latexView2 = findViewById(R.id.latexView2);
        latexView3 = findViewById(R.id.latexView3);
        derIntSymjaSV = findViewById(R.id.derIntSymjaSV);
        minX = findViewById(R.id.etXMinDerInt);
        maxX = findViewById(R.id.etXMaxDerInt);
        derInttvMax = findViewById(R.id.derInttvMax);
        derInttvMin = findViewById(R.id.derInttvMin);
        DerInttv3 = findViewById(R.id.DerInttv3);
        DerInttv2 = findViewById(R.id.DerInttv2);
        DerInttv1 = findViewById(R.id.DerInttv1);
        tvLowerBound = findViewById(R.id.tvLowerBound);
        tvUpperBound = findViewById(R.id.tvUpperBound);
        etLowerBound = findViewById(R.id.etLowerBound);
        etUpperBound = findViewById(R.id.etUpperBound);
        cbDefiniteIntegral = findViewById(R.id.checkBox6);
        cbNumericEvaluation = findViewById(R.id.checkBox7);
        tvDefInt = findViewById(R.id.textView18);
        btnCopyDefInt = findViewById(R.id.button3);

        setClickability();

        latexView2.setVisibility(View.GONE);
        latexView3.setVisibility(View.GONE);
        simpResult.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
        btnGraph.setVisibility(View.GONE);
        minX.setVisibility(View.GONE);
        maxX.setVisibility(View.GONE);
        btnGraphInfo.setVisibility(View.GONE);
        derInttvMax.setVisibility(View.GONE);
        derInttvMin.setVisibility(View.GONE);
        btnCopy.setVisibility(View.GONE);
        tvLowerBound.setVisibility(View.GONE);
        tvUpperBound.setVisibility(View.GONE);
        etLowerBound.setVisibility(View.GONE);
        etUpperBound.setVisibility(View.GONE);
        cbNumericEvaluation.setVisibility(View.GONE);
        tvDefInt.setVisibility(View.GONE);
        btnCopyDefInt.setVisibility(View.GONE);

        DerInttv3.setText("Number of times to integrate");
        DerInttv2.setText("Enter the integrating variable");
        DerInttv1.setText("Enter the function to integrate");
        calculateDerivative.setText("Calculate Integral");

        MathJaxConfig(latexView1, latexView2, latexView3);

        customTextChangeListener(inputFunction, inputVar, nthDer, minX, maxX, etLowerBound, etUpperBound);

        cbDefiniteIntegral.setOnClickListener(view -> {
            if (cbDefiniteIntegral.isChecked()) {
                tvLowerBound.setVisibility(View.VISIBLE);
                tvUpperBound.setVisibility(View.VISIBLE);
                etLowerBound.setVisibility(View.VISIBLE);
                etUpperBound.setVisibility(View.VISIBLE);
                cbNumericEvaluation.setVisibility(View.VISIBLE);
                setClickability();
            } else {
                tvLowerBound.setVisibility(View.GONE);
                tvUpperBound.setVisibility(View.GONE);
                etLowerBound.setVisibility(View.GONE);
                etUpperBound.setVisibility(View.GONE);
                cbNumericEvaluation.setVisibility(View.GONE);
                setClickability();
            }
        });

        /*IntegralTextChangeListener(btnGraph, btnGraphInfo, btnCopy, latexView1, latexView2, latexView3, inputFunction, inputVar, nthDer);



    }

    //see if the button is clickable, when the edittext is empty, the button is not clickable
    private void buttonIsClickable() {
        if (inputFunction.getText().toString().trim().isEmpty() || inputVar.getText().toString().trim().isEmpty() || nthDer.getText().toString().trim().isEmpty() || (tvLowerBound.getVisibility() == View.VISIBLE && tvUpperBound.getVisibility() == View.VISIBLE && etLowerBound.getText().toString().trim().isEmpty() || etUpperBound.getText().toString().trim().isEmpty())) {
            calculateDerivative.setClickable(false);
            calculateDerivative.setBackgroundResource(R.drawable.btn_disabled);
            calculateDerivative.setTextColor(getResources().getColor(R.color.app_bg));
            calculateDerivative.setText("Enter all the fields");
        }
        else {
            calculateDerivative.setClickable(true);
            //set style of the button
            Paris.styleBuilder(calculateDerivative).add(R.style.custom_button).apply();
            calculateDerivative.setText("Calculate integral");
        }
    }

    private void IntegralTextChangeListener(Button btnGraph, Button btnGraphInfo, Button btnCopy, MathView latexView1, MathView latexView2, MathView latexView3, EditText... editTexts) {

        for (EditText a : editTexts) {
            a.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    buttonIsClickable();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    buttonIsClickable();

                    if (calculateDerivative.isClickable()) {

                        System.out.println("Clickable");

                        calculateDerivative.setOnClickListener(v ->

                        {
                            System.out.println("btn set");

                            String function = inputFunction.getText().toString().trim();
                            String StrInputVar = inputVar.getText().toString().trim();
                            double n = eval(nthDer.getText().toString().trim());
                            double lowerBound = eval(etLowerBound.getText().toString().trim());
                            double upperBound = eval(etUpperBound.getText().toString().trim());

                            //if inputs are empty, show toast
                            if (inputFunction.getText().toString().trim().isEmpty() || inputVar.getText().toString().trim().isEmpty() || nthDer.getText().toString().trim().isEmpty()) {
                                Toast.makeText(IntegralCalcOffline.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                            } else if (n < 0) {
                                Toast.makeText(IntegralCalcOffline.this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                            } else if (n % 1 != 0) {
                                Toast.makeText(IntegralCalcOffline.this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                            } else {

                                if (function.isEmpty() || StrInputVar.isEmpty() || nthDer.getText().toString().trim().isEmpty()) {
                                    Toast.makeText(IntegralCalcOffline.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                                } else if (n < 0) {
                                    Toast.makeText(IntegralCalcOffline.this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                                } else if (n % 1 != 0) {
                                    Toast.makeText(IntegralCalcOffline.this, "Please enter a positive integer", Toast.LENGTH_SHORT).show();
                                } else {
                                    String integral = calculateNthIntegral(function, StrInputVar, (int) eval(nthDer.getText().toString().trim())).toLowerCase();
                                    String finalStringResult = "<font color=#6BBBE1>The integral is </font> <font color=#CDDC39>" + integral;

                                    result.setText(Html.fromHtml(finalStringResult, Html.FROM_HTML_MODE_LEGACY));
                                    result.setVisibility(View.VISIBLE);

                                    latexView2.setText(getTex(integral));
                                    latexView2.setVisibility(View.VISIBLE);
                                    btnCopy.setVisibility(View.VISIBLE);
                                    btnGraphInfo.setVisibility(View.VISIBLE);

                                    if (cbDefiniteIntegral.isChecked()) {

                                        String finalDefiniteIntegral;

                                        if (cbNumericEvaluation.isChecked()) {
                                            double definiteIntegral = evalDefiniteIntegral(integral, lowerBound, upperBound);
                                            finalDefiniteIntegral = "<font color=#6BBBE1>The definite integral is </font> <font color=#CDDC39>" + definiteIntegral;

                                            tvDefInt.setText(Html.fromHtml(finalDefiniteIntegral, Html.FROM_HTML_MODE_LEGACY));
                                            tvDefInt.setVisibility(View.VISIBLE);

                                            latexView2.setText(getTex(String.valueOf(definiteIntegral)));
                                        } else {
                                            String definiteIntegral = evalDefiniteIntegralSymbolic(integral, lowerBound, upperBound);
                                            finalDefiniteIntegral = "<font color=#6BBBE1>The definite integral is </font> <font color=#CDDC39>" + definiteIntegral;

                                            tvDefInt.setText(Html.fromHtml(finalDefiniteIntegral, Html.FROM_HTML_MODE_LEGACY));
                                            tvDefInt.setVisibility(View.VISIBLE);

                                            latexView2.setText(getTex(definiteIntegral));
                                        }
                                        latexView2.setVisibility(View.VISIBLE);
                                        btnCopy.setVisibility(View.VISIBLE);
                                        btnGraphInfo.setVisibility(View.VISIBLE);

                                        btnCopyDefInt.setOnClickListener(view -> {
                                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                            ClipData clip = ClipData.newPlainText("Definite Integral", finalDefiniteIntegral);
                                            clipboard.setPrimaryClip(clip);
                                            Toast.makeText(getApplicationContext(), "Definite Integral copied to clipboard", Toast.LENGTH_SHORT).show();
                                        });

                                    }

                                    btnCopy.setOnClickListener(v1 -> {
                                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData clip = ClipData.newPlainText("Integral", integral);
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(getApplicationContext(), "Integral copied to clipboard", Toast.LENGTH_SHORT).show();
                                    });

                                    derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraphInfo.getBottom()));

                                    btnGraphInfo.setOnClickListener(v12 ->
                                    {
                                        derInttvMax.setVisibility(View.VISIBLE);
                                        derInttvMin.setVisibility(View.VISIBLE);
                                        minX.setVisibility(View.VISIBLE);
                                        maxX.setVisibility(View.VISIBLE);
                                        btnGraph.setVisibility(View.VISIBLE);

                                        //create a toast to display message that smaller ranges will give more  accurate graph
                                        Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();

                                        derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraph.getBottom()));

                                        btnGraph.setOnClickListener(v1 -> {
                                            if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Intent intent = new Intent(IntegralCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                                intent.putExtra("input", function);
                                                intent.putExtra("DerInt", integral);
                                                intent.putExtra("minX", eval(minX.getText().toString().trim()));
                                                intent.putExtra("maxX", eval(maxX.getText().toString().trim()));
                                                startActivity(intent);
                                            }

                                        });
                                    });


                                    // Execute simplification in a separate thread
                                    new Thread(() -> {
                                        String simplifiedDerivative = ExprSimplifier(integral);
                                        String finalSimplifiedStringResult = "<font color=#6BBBE1>The simplified derivative is </font> <font color=#CDDC39>" + integral;


                                        // Update UI with simplified derivative
                                        runOnUiThread(() -> {

                                            latexView3.setText(getTex(simplifiedDerivative));
                                            latexView3.setVisibility(View.VISIBLE);
                                            Toast.makeText(getApplicationContext(), "The integral has been simplified", Toast.LENGTH_SHORT).show();
                                            simpResult.setText(Html.fromHtml(finalSimplifiedStringResult, Html.FROM_HTML_MODE_LEGACY));
                                            simpResult.setVisibility(View.VISIBLE);
                                            btnCopy.setVisibility(View.VISIBLE);

                                            derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraphInfo.getBottom()));

                                            btnCopy.setOnClickListener(v1 -> {
                                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                                ClipData clip = ClipData.newPlainText("Simplified integral", simplifiedDerivative);
                                                clipboard.setPrimaryClip(clip);
                                                Toast.makeText(getApplicationContext(), "Simplified integral copied to clipboard", Toast.LENGTH_SHORT).show();
                                            });


                                            btnGraphInfo.setOnClickListener(v12 ->
                                            {
                                                derInttvMax.setVisibility(View.VISIBLE);
                                                derInttvMin.setVisibility(View.VISIBLE);
                                                minX.setVisibility(View.VISIBLE);
                                                maxX.setVisibility(View.VISIBLE);
                                                btnGraph.setVisibility(View.VISIBLE);

                                                derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraph.getBottom()));

                                                //create a toast to display message that smaller ranges will give more  accurate graph
                                                Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();


                                                btnGraph.setOnClickListener(v1 -> {
                                                    if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                                                        Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Intent intent = new Intent(IntegralCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                                        intent.putExtra("input", function);
                                                        intent.putExtra("DerInt", integral);
                                                        intent.putExtra("minX", eval(minX.getText().toString().trim()));
                                                        intent.putExtra("maxX", eval(maxX.getText().toString().trim()));
                                                        startActivity(intent);
                                                    }

                                                });
                                            });
                                        });
                                    }).start();
                                }

                            }
                        });

                    }

                    if(!inputFunction.getText().toString().isEmpty()) {
                        latexView1.setVisibility(View.VISIBLE);
                    }


                    /*if (inputFunction.getText().toString().isEmpty() || nthDer.getText().toString().isEmpty()) {
                        calculateDerivative.setClickable(false);
                        calculateDerivative.setBackgroundResource(R.drawable.btn_disabled);
                        calculateDerivative.setTextColor(getResources().getColor(R.color.app_bg));
                        latexView1.setVisibility(View.GONE);
                    } else {
                        latexView1.setText(getTex(inputFunction.getText().toString().trim()));
                        calculateDerivative.setClickable(true);
                        //set style of the button
                        Paris.styleBuilder(calculateDerivative).add(R.style.custom_button).apply();
                        latexView1.setVisibility(View.VISIBLE);
                    }
                }
            });
        }*/

    }

    private double evalDefiniteIntegral(String integral, double lowerBound, double upperBound) {

        double upperValue = evalAtPointX(integral, upperBound, "x");
        double lowerValue = evalAtPointX(integral, lowerBound, "x");
        return upperValue - lowerValue;

    }

    private String evalDefiniteIntegralSymbolic(String integral, CharSequence var, double lowerBound, double upperBound) //working
    {
        ExprEvaluator util = new ExprEvaluator();

        String upperval = evalAtPointXSymbolic(integral, var, upperBound);
        String lowerval = evalAtPointXSymbolic(integral, var, lowerBound);

        String expr = upperval + "-" + lowerval;

        String expanded = util.eval("Expand(" + expr + ")").toString();
        return util.eval("Simplify(ReplaceAll(" + expanded + ", {log(x_)+log(y_) -> log(x*y), log(x_)-log(y_) -> log(x/y), log(x_^y_) -> y*log(x_)}))").toString();
    }

    //method to set clickability of the calculate button
    private void setClickability() {
        /*first check the edittexts if cbDefiniteIntegral is checked
         * also check if n is a natural number*/
        if (inputFunction.getText().toString().isEmpty() || nthDer.getText().toString().isEmpty() || (cbDefiniteIntegral.isChecked() && (etLowerBound.getText().toString().isEmpty() || etUpperBound.getText().toString().isEmpty()))) {
            calculateDerivative.setClickable(false);
            calculateDerivative.setBackgroundResource(R.drawable.btn_disabled);
            calculateDerivative.setTextColor(getResources().getColor(R.color.app_bg));
        } else {
            double number = Double.parseDouble(nthDer.getText().toString().trim());
            if (number >= 0 && Math.floor(number) == number) {
                latexView1.setText(getTex(inputFunction.getText().toString().trim()));
                calculateDerivative.setClickable(true);
                //set style of the button
                Paris.styleBuilder(calculateDerivative).add(R.style.custom_button_enabled).apply();
                latexView1.setVisibility(View.VISIBLE);
            } else {
                calculateDerivative.setClickable(false);
                calculateDerivative.setBackgroundResource(R.drawable.btn_disabled);
                calculateDerivative.setTextColor(getResources().getColor(R.color.app_bg));
            }

        }
    }

    private void customTextChangeListener(EditText... editText) {

        for (EditText editText1 : editText) {
            editText1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    setClickability();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    setClickability();
                    //result, simp_result and textView18 are cleared
                    result.setVisibility(View.GONE);
                    simpResult.setVisibility(View.GONE);
                    tvDefInt.setVisibility(View.GONE);
                    btnCopy.setVisibility(View.GONE);
                    btnCopyDefInt.setVisibility(View.GONE);
                    latexView2.setVisibility(View.GONE);
                    latexView3.setVisibility(View.GONE);
                    latexView1.setText(getTex(inputFunction.getText().toString().trim()));

                    if (!inputFunction.getText().toString().isEmpty()) {
                        latexView1.setVisibility(View.VISIBLE);
                    }

                    if (calculateDerivative.isClickable()) {
                        calculateDerivative.setOnClickListener(v -> {

                            double lowerBound = 0;
                            double upperBound = 0;

                            //get lower and upper bounds if cbDefiniteIntegral is checked
                            if (cbDefiniteIntegral.isChecked()) {
                                lowerBound = evalf(etLowerBound.getText().toString().trim());
                                upperBound = evalf(etUpperBound.getText().toString().trim());
                            }

                            String function = inputFunction.getText().toString().trim();
                            String StrInputVar = inputVar.getText().toString().trim();

                            String integral = calculateNthIntegral(function, StrInputVar, (int) evalf(nthDer.getText().toString().trim())).toLowerCase();
                            String finalStringResult = "<font color=#6BBBE1>The integral is </font> <font color=#CDDC39>" + integral;

                            result.setText(Html.fromHtml(finalStringResult, Html.FROM_HTML_MODE_LEGACY));
                            result.setVisibility(View.VISIBLE);

                            latexView2.setText(getTex(integral));
                            latexView2.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);
                            btnGraphInfo.setVisibility(View.VISIBLE);

                            if (cbDefiniteIntegral.isChecked()) {

                                String finalDefiniteIntegral, definiteIntegral;

                                if (cbNumericEvaluation.isChecked()) {
                                    definiteIntegral = String.valueOf(evalDefiniteIntegral(integral, lowerBound, upperBound));

                                } else {
                                    definiteIntegral = evalDefiniteIntegralSymbolic(integral, StrInputVar, lowerBound, upperBound);

                                }
                                finalDefiniteIntegral = "<font color=#6BBBE1>The definite integral is </font> <font color=#CDDC39>" + definiteIntegral;
                                tvDefInt.setText(Html.fromHtml(finalDefiniteIntegral, Html.FROM_HTML_MODE_LEGACY));
                                tvDefInt.setVisibility(View.VISIBLE);


                                btnCopyDefInt.setOnClickListener(view -> {
                                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clip = ClipData.newPlainText("Definite Integral", definiteIntegral);
                                    clipboard.setPrimaryClip(clip);
                                    Toast.makeText(getApplicationContext(), "Definite Integral copied to clipboard", Toast.LENGTH_SHORT).show();
                                });

                            }

                            btnCopy.setVisibility(View.VISIBLE);
                            btnGraphInfo.setVisibility(View.VISIBLE);

                            btnCopy.setOnClickListener(view -> {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Integral", integral);
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(getApplicationContext(), "Integral copied to clipboard", Toast.LENGTH_SHORT).show();
                            });


                            btnGraphInfo.setOnClickListener(v12 ->
                            {
                                derInttvMax.setVisibility(View.VISIBLE);
                                derInttvMin.setVisibility(View.VISIBLE);
                                minX.setVisibility(View.VISIBLE);
                                maxX.setVisibility(View.VISIBLE);
                                btnGraph.setVisibility(View.VISIBLE);

                                //create a toast to display message that smaller ranges will give more  accurate graph
                                Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();

                                derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraph.getBottom()));

                                btnGraph.setOnClickListener(v1 -> {
                                    if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(IntegralCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                        intent.putExtra("input", function);
                                        intent.putExtra("DerInt", integral);
                                        intent.putExtra("minX", evalf(minX.getText().toString().trim()));
                                        intent.putExtra("maxX", evalf(maxX.getText().toString().trim()));
                                        startActivity(intent);
                                    }

                                });
                            });

                            // Execute simplification in a separate thread
                            new Thread(() -> {
                                String simplifiedIntegral = ExprSimplifier(integral);
                                String finalSimplifiedStringResult = "<font color=#6BBBE1>The simplified integral is </font> <font color=#CDDC39>" + integral;


                                // Update UI with simplified derivative
                                runOnUiThread(() -> {

                                    latexView3.setText(getTex(simplifiedIntegral));
                                    latexView3.setVisibility(View.VISIBLE);
                                    Toast.makeText(getApplicationContext(), "The integral has been simplified", Toast.LENGTH_SHORT).show();
                                    simpResult.setText(Html.fromHtml(finalSimplifiedStringResult, Html.FROM_HTML_MODE_LEGACY));
                                    simpResult.setVisibility(View.VISIBLE);
                                    btnCopy.setVisibility(View.VISIBLE);

                                    derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraphInfo.getBottom()));

                                    btnCopy.setOnClickListener(v1 -> {
                                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData clip = ClipData.newPlainText("Simplified integral", simplifiedIntegral);
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(getApplicationContext(), "Simplified integral copied to clipboard", Toast.LENGTH_SHORT).show();
                                    });


                                    btnGraphInfo.setOnClickListener(v12 ->
                                    {
                                        derInttvMax.setVisibility(View.VISIBLE);
                                        derInttvMin.setVisibility(View.VISIBLE);
                                        minX.setVisibility(View.VISIBLE);
                                        maxX.setVisibility(View.VISIBLE);
                                        btnGraph.setVisibility(View.VISIBLE);

                                        derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraph.getBottom()));

                                        //create a toast to display message that smaller ranges will give more  accurate graph
                                        Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();


                                        btnGraph.setOnClickListener(v1 -> {
                                            if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Intent intent = new Intent(IntegralCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                                intent.putExtra("input", function);
                                                intent.putExtra("DerInt", integral);
                                                intent.putExtra("minX", evalf(minX.getText().toString().trim()));
                                                intent.putExtra("maxX", evalf(maxX.getText().toString().trim()));
                                                startActivity(intent);
                                            }

                                        });
                                    });
                                });
                            }).start();

                        });

                    }
                }
            });
        }
    }
}


