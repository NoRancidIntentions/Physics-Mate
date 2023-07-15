package com.example.physicsmate.activities;

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
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;

import static com.example.physicsmate.Custom_methods.*;

public class DerivativeCalcOffline extends AppCompatActivity {
    EditText inputFunction, inputVar, nthDer, minX, maxX;
    TextView result, simpResult, derInttvMax, derInttvMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative_integral_calc_offline);

        inputFunction = findViewById(R.id.input_function);
        nthDer = findViewById(R.id.nth_der);
        inputVar = findViewById(R.id.input_var);
        Button calculateDerivative = findViewById(R.id.calculate_derivative);
        Button btnGraph = findViewById(R.id.btnGraph);
        Button btnGraphInfo = findViewById(R.id.btnGraphInfo);
        Button btnCopy = findViewById(R.id.btnCopy);
        result = findViewById(R.id.result);
        simpResult = findViewById(R.id.simp_result);
        MathView latexView1 = findViewById(R.id.latexView1);
        MathView latexView2 = findViewById(R.id.latexView2);
        MathView latexView3 = findViewById(R.id.latexView3);
        ScrollView derIntSymjaSV = findViewById(R.id.derIntSymjaSV);
        minX = findViewById(R.id.etXMinDerInt);
        maxX = findViewById(R.id.etXMaxDerInt);
        derInttvMax = findViewById(R.id.derInttvMax);
        derInttvMin = findViewById(R.id.derInttvMin);

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

        MathJaxConfig(latexView1, latexView2, latexView3);


        inputFunction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                latexView2.setVisibility(View.GONE);
                latexView3.setVisibility(View.GONE);
                simpResult.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);
                minX.setVisibility(View.GONE);
                maxX.setVisibility(View.GONE);
                btnGraphInfo.setVisibility(View.GONE);
                btnCopy.setVisibility(View.GONE);

                if (inputFunction.getText().toString().isEmpty()) {
                    latexView1.setVisibility(View.GONE);
                } else {
                    latexView1.setText(getTex(inputFunction.getText().toString().trim()));
                    //latexView1.setFontSize(100);
                    //latexView1.setTextColor(Color.WHITE);
                    latexView1.setVisibility(View.VISIBLE);
                }
            }
        });

        inputVar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                latexView2.setVisibility(View.GONE);
                latexView3.setVisibility(View.GONE);
                simpResult.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);
                minX.setVisibility(View.GONE);
                maxX.setVisibility(View.GONE);
                btnGraphInfo.setVisibility(View.GONE);
                btnCopy.setVisibility(View.GONE);

            }
        });

        nthDer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                latexView2.setVisibility(View.GONE);
                latexView3.setVisibility(View.GONE);
                simpResult.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);
                minX.setVisibility(View.GONE);
                maxX.setVisibility(View.GONE);
                btnGraphInfo.setVisibility(View.GONE);
                btnCopy.setVisibility(View.GONE);
            }
        });


        calculateDerivative.setOnClickListener(v ->

        {

            String function = inputFunction.getText().toString().trim();
            String StrInputVar = inputVar.getText().toString().trim();
            double n = eval(nthDer.getText().toString().trim());

            if (inputFunction.getText().toString().trim().isEmpty() || inputVar.getText().toString().trim().isEmpty() || nthDer.getText().toString().trim().isEmpty()) {
                Toast.makeText(DerivativeCalcOffline.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {

                String derivative = calculateNthDerivative(function, StrInputVar, (int) eval(nthDer.getText().toString().trim()));
                String finalStringResult = "<font color=#6BBBE1>The derivative is </font> <font color=#CDDC39>" + derivative;

                result.setText(Html.fromHtml(finalStringResult, Html.FROM_HTML_MODE_LEGACY));
                result.setVisibility(View.VISIBLE);

                latexView2.setText(getTex(derivative));
                latexView2.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);
                btnGraphInfo.setVisibility(View.VISIBLE);

                btnCopy.setOnClickListener(v1 -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Derivative", derivative);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Derivative copied to clipboard", Toast.LENGTH_SHORT).show();
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
                            Intent intent = new Intent(DerivativeCalcOffline.this, DerivativeCalcOfflineGraph.class);
                            intent.putExtra("input", function);
                            intent.putExtra("DerInt", derivative);
                            intent.putExtra("minX", eval(minX.getText().toString().trim()));
                            intent.putExtra("maxX", eval(maxX.getText().toString().trim()));
                            startActivity(intent);
                        }

                    });
                });


                // Execute simplification in a separate thread
                new Thread(() -> {
                    long startTimer = System.currentTimeMillis();
                    String simplifiedDerivative = ExprSimplifier(derivative);
                    long endTimer = System.currentTimeMillis();
                    System.out.println("Time taken to simplify derivative: " + (endTimer - startTimer) + "ms");
                    String finalSimplifiedStringResult = "<font color=#6BBBE1>The simplified derivative is </font> <font color=#CDDC39>" + derivative;


                    // Update UI with simplified derivative
                    runOnUiThread(() -> {

                        latexView3.setText(getTex(simplifiedDerivative));
                        latexView3.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "The derivative has been simplified", Toast.LENGTH_SHORT).show();
                        simpResult.setText(Html.fromHtml(finalSimplifiedStringResult, Html.FROM_HTML_MODE_LEGACY));
                        simpResult.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        derIntSymjaSV.post(() -> derIntSymjaSV.smoothScrollTo(0, btnGraphInfo.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Simplified erivative", simplifiedDerivative);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(getApplicationContext(), "Simplified derivative copied to clipboard", Toast.LENGTH_SHORT).show();
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
                                    Intent intent = new Intent(DerivativeCalcOffline.this, DerivativeCalcOfflineGraph.class);
                                    intent.putExtra("input", function);
                                    intent.putExtra("DerInt", derivative);
                                    intent.putExtra("minX", eval(minX.getText().toString().trim()));
                                    intent.putExtra("maxX", eval(maxX.getText().toString().trim()));
                                    startActivity(intent);
                                }

                            });
                        });
                    });
                }).start();


            }
        });
    }


}


