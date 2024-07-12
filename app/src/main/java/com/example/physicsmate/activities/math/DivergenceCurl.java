package com.example.physicsmate.activities.math;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;

import java.util.Arrays;

import static com.example.physicsmate.Custom_methods.*;

public class DivergenceCurl extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_menu);

        LinearLayout linearLayout = findViewById(R.id.generalMenuLinearLayout);
        Button btnDivergence = new Button(this);
        Paris.style(btnDivergence).apply(R.style.custom_button_enabled);
        btnDivergence.setText("Divergence");
        linearLayout.addView(btnDivergence);
        Button btnCurl = new Button(this);
        Paris.style(btnCurl).apply(R.style.custom_button_enabled);
        btnCurl.setText("Curl");
        linearLayout.addView(btnCurl);

        btnDivergence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Divergence.class));
            }
        });
        btnCurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Curl.class));
            }
        });
    }

    public static class Divergence extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView tv1, tv2, tvResult;
            EditText et1, et2;
            Button btnCalc;
            ScrollView scrollView;
            ImageView img;
            MathView mathView;
            Button btnCopy;

            tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv1.setText("enter the vector field (any dimension)");
            tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv2.setText("enter the variables (in order)");

            et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et1.setHint("(x^2, sin(yz), e^z)");
            et2.setHint("(x, y, z)");

            btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalc.setText("Calculate Divergence");
            scrollView = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);
            img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            img.setVisibility(View.GONE);
            mathView = findViewById(R.id.latexView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            LinearLayout linearLayout = findViewById(R.id.linearlayout_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);


            btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCopy.setText("Copy to Clipboard");
            btnCopy.setVisibility(View.GONE);

            tvResult = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvResult.setVisibility(View.GONE);
            mathView.setVisibility(View.GONE);

            btnCalc.setOnClickListener(v -> {
                String vectorField = et1.getText().toString();
                String variables = et2.getText().toString();
                String result = calculateDivergence(vectorField, variables);
                if (result.equals("Error")) {
                    tvResult.setText("Error while evaluating. Please check your input.");
                    tvResult.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setText(Html.fromHtml(formatText("Divergence of the vector field " + vectorField + " is " + result)));
                    tvResult.setVisibility(View.VISIBLE);
                    mathView.setText(getTex(result));
                    mathView.setVisibility(View.VISIBLE);
                    btnCopy.setVisibility(View.VISIBLE);

                    btnCopy.setOnClickListener(v1 -> {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Result", result);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                    });

                    Button btnEvaluate = new Button(this);
                    Paris.style(btnEvaluate).apply(R.style.custom_button_enabled);
                    btnEvaluate.setText("Evaluate at a point");
                    linearLayout.addView(btnEvaluate);
                    scrollView.post(() -> scrollView.smoothScrollTo(0, btnEvaluate.getBottom()));

                    String finalVectorField = vectorField;
                    btnEvaluate.setOnClickListener(v1 -> {
                        TextView tvEvaluate = new TextView(this);
                        Paris.style(tvEvaluate).apply(R.style.custom_textView);
                        tvEvaluate.setText("Enter the point to evaluate at");
                        linearLayout.addView(tvEvaluate);

                        EditText etEvaluate = new EditText(this);
                        Paris.style(etEvaluate).apply(R.style.custom_edittext);
                        etEvaluate.setHint("(2, e^3, sin(0.43))");
                        linearLayout.addView(etEvaluate);

                        Button btnCalc2 = new Button(this);
                        Paris.style(btnCalc2).apply(R.style.custom_button_enabled);
                        btnCalc2.setText("Calculate");
                        linearLayout.addView(btnCalc2);
                        scrollView.post(() -> scrollView.smoothScrollTo(0, btnCalc2.getBottom()));

                        btnCalc2.setOnClickListener(v2 -> {
                            String point = etEvaluate.getText().toString();
                            String result2 = evaluateAtMultiPoint(finalVectorField, variables, point);
                            if (result2.equals("Error")) {
                                tvResult.setText("Error while evaluating. Please check your input.");
                                tvResult.setVisibility(View.VISIBLE);
                            } else {
                                TextView tvResult2 = new TextView(this);
                                Paris.style(tvResult2).apply(R.style.custom_textView);
                                tvResult2.setText(Html.fromHtml(formatText("Divergence at " + point + " is " + result2)));
                                linearLayout.addView(tvResult2);
                                scrollView.post(() -> scrollView.smoothScrollTo(0, tvResult2.getBottom()));
                            }
                        });
                    });

                }
            });

            setupEditTextChangeListener(new View[]{tvResult, btnCopy, mathView}, btnCalc, null, Arrays.asList(et1, et2));
        }

    }

    public static class Curl extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView tv1, tv2, tvResult;
            EditText et1, et2;
            Button btnCalc;
            ScrollView scrollView;
            ImageView img;
            MathView mathView;
            Button btnCopy;

            tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv1.setText("enter the vector field (any dimension)");
            tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv2.setText("enter the variables (in order)");

            et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et1.setHint("(x^2, sin(yz), e^z)");
            et2.setHint("(x, y, z)");

            btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCalc.setText("Calculate Curl");
            scrollView = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);
            img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            img.setVisibility(View.GONE);
            mathView = findViewById(R.id.latexView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            LinearLayout linearLayout = findViewById(R.id.linearlayout_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);


            btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            btnCopy.setText("Copy to Clipboard");
            btnCopy.setVisibility(View.GONE);

            tvResult = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvResult.setVisibility(View.GONE);
            mathView.setVisibility(View.GONE);

            btnCalc.setOnClickListener(v -> {
                String vectorField = et1.getText().toString();
                String variables = et2.getText().toString();
                String result = calculateCurl(vectorField, variables);
                if (result.equals("Error")) {
                    tvResult.setText("Error while evaluating. Please check your input.");
                    tvResult.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setText(Html.fromHtml(formatText("Divergence of the vector field " + vectorField + " is " + result)));
                    tvResult.setVisibility(View.VISIBLE);
                    mathView.setText(getTex(result));
                    mathView.setVisibility(View.VISIBLE);
                    btnCopy.setVisibility(View.VISIBLE);

                    btnCopy.setOnClickListener(v1 -> {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Result", result);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                    });

                    Button btnEvaluate = new Button(this);
                    Paris.style(btnEvaluate).apply(R.style.custom_button_enabled);
                    btnEvaluate.setText("Evaluate at a point");
                    linearLayout.addView(btnEvaluate);
                    scrollView.post(() -> scrollView.smoothScrollTo(0, btnEvaluate.getBottom()));

                    String finalVectorField = vectorField;
                    btnEvaluate.setOnClickListener(v1 -> {
                        TextView tvEvaluate = new TextView(this);
                        Paris.style(tvEvaluate).apply(R.style.custom_textView);
                        tvEvaluate.setText("Enter the point to evaluate at");
                        linearLayout.addView(tvEvaluate);

                        EditText etEvaluate = new EditText(this);
                        Paris.style(etEvaluate).apply(R.style.custom_edittext);
                        etEvaluate.setHint("(2, e^3, sin(0.43))");
                        linearLayout.addView(etEvaluate);

                        Button btnCalc2 = new Button(this);
                        Paris.style(btnCalc2).apply(R.style.custom_button_enabled);
                        btnCalc2.setText("Calculate");
                        linearLayout.addView(btnCalc2);
                        scrollView.post(() -> scrollView.smoothScrollTo(0, btnCalc2.getBottom()));

                        btnCalc2.setOnClickListener(v2 -> {
                            String point = etEvaluate.getText().toString();
                            String result2 = evaluateAtMultiPoint(finalVectorField, variables, point);
                            if (result2.equals("Error")) {
                                tvResult.setText("Error while evaluating. Please check your input.");
                                tvResult.setVisibility(View.VISIBLE);
                            } else {
                                TextView tvResult2 = new TextView(this);
                                Paris.style(tvResult2).apply(R.style.custom_textView);
                                tvResult2.setText(Html.fromHtml(formatText("Curl at " + point + " is " + result2)));
                                linearLayout.addView(tvResult2);
                                scrollView.post(() -> scrollView.smoothScrollTo(0, tvResult2.getBottom()));
                            }
                        });
                    });

                }
            });

            setupEditTextChangeListener(new View[]{tvResult, btnCopy, mathView}, btnCalc, null, Arrays.asList(et1, et2));
        }


    }
}