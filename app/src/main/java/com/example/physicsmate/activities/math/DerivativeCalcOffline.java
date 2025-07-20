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
import com.agog.mathdisplay.MTMathView;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;
import com.example.physicsmate.ui.main.CustomKeyboard;

import static com.example.physicsmate.Custom_methods.*;

public class DerivativeCalcOffline extends BaseActivity {
    CustomKeyboard customKeyboard;
    EditText inputFunction, inputVar, nthDer, minX, maxX, etLowerBound, etUpperBound;
    CheckBox cbDefiniteIntegral, cbNumericEvaluation;
    TextView result, simpResult, derInttvMax, derInttvMin, DerInttv3, DerInttv2, DerInttv1, tvLowerBound, tvUpperBound, tvDefInt;
    MTMathView latexView1, latexView2, latexView3;

    Button calculateDerivative, btnGraph, btnGraphInfo, btnCopy, btnCopyDefInt;

    //MathView latexView1, latexView2, latexView3;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        sv = findViewById(R.id.derIntSymjaSV);
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

        latexView1.setVisibility(View.GONE);
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
        cbDefiniteIntegral.setVisibility(View.GONE);


        DerInttv3.setText("Number of times to differentiate");
        DerInttv2.setText("Enter the differentiating variable");
        DerInttv1.setText("Enter the function to differentiate");
        calculateDerivative.setText("Calculate derivative");

        //MathJaxConfig(latexView1, latexView2, latexView3);

        customTextChangeListener(inputFunction, inputVar, nthDer, minX, maxX, etLowerBound, etUpperBound);

        base_constraint_layout.post(() -> {
            customKeyboard = getKeyboard();
            if (customKeyboard != null) {
                customKeyboard.hideKeyboard();
                setupEditTextForCustomKeyboard(customKeyboard, sv, inputFunction, inputVar, nthDer, minX, maxX, etLowerBound, etUpperBound);
            }
        });

        setLinkedScrollView(sv);


        Paris.styleBuilder(result).add(R.style.answer_textView).apply();
        Paris.styleBuilder(simpResult).add(R.style.answer_textView).apply();

        setupButtons();

    }

    //method to set clickability of the calculate button
    private void setClickability() {
        /*first check the edittexts if cbDefiniteIntegral is checked
         * also check if n is a natural number*/
        if (inputVar.getText().toString().trim().isEmpty() || inputFunction.getText().toString().isEmpty() || nthDer.getText().toString().isEmpty() || inputVar.getText().toString().isEmpty()) {
            calculateDerivative.setClickable(false);
            calculateDerivative.setBackgroundResource(R.drawable.btn_disabled);
            calculateDerivative.setTextColor(getResources().getColor(R.color.app_bg));
        } else {
            double number = Double.parseDouble(nthDer.getText().toString().trim()); //TODO
            if (number >= 0 && Math.floor(number) == number) {
                latexView1.setLatex(getTex(inputFunction.getText().toString().trim(), latexView1));
                calculateDerivative.setClickable(true);
                //set style of the button
                Paris.styleBuilder(calculateDerivative).add(R.style.custom_button_enabled).apply();
                //latexView1.setVisibility(View.VISIBLE);
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
                    setClickability();
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
                    latexView1.setLatex(getTex(inputFunction.getText().toString().trim(), latexView1));

                    if (!inputFunction.getText().toString().isEmpty()) {
                        latexView1.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void setupButtons() {

        calculateDerivative.setOnClickListener(v ->
        {
            hideKeyboardOnClick(calculateDerivative, DerivativeCalcOffline.this);
            customKeyboard.hideKeyboard();

            String function = inputFunction.getText().toString().trim();
            String StrInputVar = inputVar.getText().toString().trim();
            final String[] derivative = new String[1];

            //get derivative in a new thread
            Thread thread = new Thread(() -> derivative[0] = calculateNthDerivative(function, StrInputVar, (int) evalf(nthDer.getText().toString().trim())).toLowerCase());

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }

            String finalStringResult = "The derivative is " + HtmlColoriser(DerivativeCalcOffline.this, derivative[0]);

            result.setText(Html.fromHtml(finalStringResult, Html.FROM_HTML_MODE_LEGACY));
            result.setVisibility(View.VISIBLE);

            latexView2.setLatex(getTex(derivative[0], latexView2));
            latexView2.setVisibility(View.VISIBLE);
            btnCopy.setVisibility(View.VISIBLE);
            btnGraphInfo.setVisibility(View.VISIBLE);


            btnCopy.setVisibility(View.VISIBLE);
            btnGraphInfo.setVisibility(View.VISIBLE);

            btnCopy.setOnClickListener(view -> {
                customKeyboard.hideKeyboard();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Derivative", derivative[0]);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Derivative copied to clipboard", Toast.LENGTH_SHORT).show();
            });


            btnGraphInfo.setOnClickListener(v12 ->
            {
                customKeyboard.hideKeyboard();
                derInttvMax.setVisibility(View.VISIBLE);
                derInttvMin.setVisibility(View.VISIBLE);
                minX.setVisibility(View.VISIBLE);
                maxX.setVisibility(View.VISIBLE);
                btnGraph.setVisibility(View.VISIBLE);

                //create a toast to display message that smaller ranges will give more  accurate graph
                Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();

                sv.post(() -> sv.smoothScrollTo(0, btnGraph.getBottom()));

                btnGraph.setOnClickListener(v1 -> {
                    customKeyboard.hideKeyboard();
                    if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(DerivativeCalcOffline.this, DerivativeCalcOfflineGraph.class);
                        intent.putExtra("input", function);
                        intent.putExtra("DerInt", derivative[0]);
                        intent.putExtra("minX", evalf(minX.getText().toString().trim()));
                        intent.putExtra("maxX", evalf(maxX.getText().toString().trim()));
                        startActivity(intent);
                    }

                });
            });

            // Execute simplification in a separate thread
            new Thread(() -> {
                String simplifiedDerivative = ExprSimplifier(derivative[0]);
                String finalSimplifiedStringResult = "The simplified derivative is " + HtmlColoriser(DerivativeCalcOffline.this, simplifiedDerivative);


                // Update UI with simplified derivative
                runOnUiThread(() -> {

                    latexView3.setLatex(getTex(simplifiedDerivative, latexView3));
                    latexView3.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "The derivative has been simplified", Toast.LENGTH_SHORT).show();
                    simpResult.setText(Html.fromHtml(finalSimplifiedStringResult, Html.FROM_HTML_MODE_LEGACY));
                    simpResult.setVisibility(View.VISIBLE);
                    btnCopy.setVisibility(View.VISIBLE);

                    sv.post(() -> sv.smoothScrollTo(0, btnGraphInfo.getBottom()));

                    btnCopy.setOnClickListener(v1 -> {
                        customKeyboard.hideKeyboard();
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Simplified derivative", simplifiedDerivative);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), "Simplified derivative copied to clipboard", Toast.LENGTH_SHORT).show();
                    });


                    btnGraphInfo.setOnClickListener(v12 ->
                    {
                        customKeyboard.hideKeyboard();
                        derInttvMax.setVisibility(View.VISIBLE);
                        derInttvMin.setVisibility(View.VISIBLE);
                        minX.setVisibility(View.VISIBLE);
                        maxX.setVisibility(View.VISIBLE);
                        btnGraph.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnGraph.getBottom()));

                        //create a toast to display message that smaller ranges will give more  accurate graph
                        Toast.makeText(getApplicationContext(), "Smaller ranges will give more accurate graph", Toast.LENGTH_SHORT).show();


                        btnGraph.setOnClickListener(v1 -> {
                            customKeyboard.hideKeyboard();
                            if (minX.getText().toString().trim().isEmpty() || maxX.getText().toString().trim().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please enter a valid range", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(DerivativeCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                intent.putExtra("input", function);
                                intent.putExtra("DerInt", derivative[0]);
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


