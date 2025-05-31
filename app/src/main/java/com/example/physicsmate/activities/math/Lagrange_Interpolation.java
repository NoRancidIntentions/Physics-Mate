package com.example.physicsmate.activities.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.agog.mathdisplay.MTMathView;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import org.hipparchus.analysis.polynomials.PolynomialFunctionLagrangeForm;

import java.util.ArrayList;
import java.util.List;

import static com.example.physicsmate.Custom_methods.*;

public class Lagrange_Interpolation extends AppCompatActivity {

    private Button btnAddRow, btnCalc;
    private TextView tvResult;
    private MTMathView mathView;
    private LinearLayout rowsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagrange_interpolation);

        rowsLayout = findViewById(R.id.LagrangeRowsLinearLayout);
        tvResult = findViewById(R.id.LagrangeTvResult);
        mathView = findViewById(R.id.l_mathview);
        btnAddRow = findViewById(R.id.LagrangeAddRowButton);
        btnCalc = findViewById(R.id.LagrangeCalcButton);

        Paris.styleBuilder(tvResult).add(R.style.answer_textView).apply();

        mathView.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);

        setupInitialTextWatchers();
        btnAddRow.setOnClickListener(v -> addRow());
        btnCalc.setOnClickListener(v -> calculatePolynomial());

        updateCalcButtonState();
    }

    private void setupInitialTextWatchers() {
        for (int i = 0; i < rowsLayout.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) rowsLayout.getChildAt(i);
            for (int j = 0; j < 2; j++) {
                EditText et = (EditText) row.getChildAt(j);
                et.addTextChangedListener(new CustomTextWatcher());
            }
        }
    }

    private void addRow() {
        @SuppressLint("InflateParams")
        LinearLayout newRow = (LinearLayout) getLayoutInflater().inflate(R.layout.lagrange_rows, null, false);
        Animation anim = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        newRow.startAnimation(anim);
        rowsLayout.addView(newRow);

        int index = rowsLayout.getChildCount();

        EditText xInput = (EditText) newRow.getChildAt(0);
        EditText yInput = (EditText) newRow.getChildAt(1);
        ImageButton deleteButton = (ImageButton) newRow.getChildAt(2);

        xInput.setHint("x" + index);
        yInput.setHint("y" + index);

        xInput.addTextChangedListener(new CustomTextWatcher());
        yInput.addTextChangedListener(new CustomTextWatcher());

        deleteButton.setOnClickListener(v -> removeRow(newRow));

        updateCalcButtonState();
    }

    private void removeRow(View row) {
        Animation anim = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        row.startAnimation(anim);
        row.postDelayed(() -> {
            rowsLayout.removeView(row);
            updateCalcButtonState();
        }, anim.getDuration());
    }

    private void updateCalcButtonState() {
        boolean allFilled = true;
        for (int i = 0; i < rowsLayout.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) rowsLayout.getChildAt(i);
            EditText x = (EditText) row.getChildAt(0);
            EditText y = (EditText) row.getChildAt(1);
            if (x.getText().toString().trim().isEmpty() || y.getText().toString().trim().isEmpty()) {
                allFilled = false;
                break;
            }
        }

        if (allFilled) {
            btnCalc.setEnabled(true);
            btnCalc.setClickable(true);
            btnCalc.setText("Get Polynomial");
            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
        } else {
            btnCalc.setEnabled(false);
            btnCalc.setClickable(false);
            btnCalc.setText("Enter all the fields");
            btnCalc.setBackgroundResource(R.drawable.btn_disabled);
            btnCalc.setTextColor(getResources().getColor(R.color.app_bg));
        }
    }

    private void calculatePolynomial() {
        List<Double> xVals = new ArrayList<>();
        List<Double> yVals = new ArrayList<>();

        for (int i = 0; i < rowsLayout.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) rowsLayout.getChildAt(i);
            EditText x = (EditText) row.getChildAt(0);
            EditText y = (EditText) row.getChildAt(1);
            try {
                xVals.add(evalf(x.getText().toString()));
                yVals.add(evalf(y.getText().toString()));
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        double[] xArr = xVals.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArr = yVals.stream().mapToDouble(Double::doubleValue).toArray();

        String poly = getPolynomialString(xArr, yArr);
        String resultText = "The polynomial satisfying the above points is " + HtmlColoriser(this, "y = " + poly);

        tvResult.setText(Html.fromHtml(resultText, Html.FROM_HTML_MODE_LEGACY));
        tvResult.setVisibility(View.VISIBLE);

        mathView.setLatex(getTex("y = " + poly, mathView));
        mathView.setVisibility(View.VISIBLE);
    }

    private String getPolynomialString(double[] x, double[] y) {
        try {
            PolynomialFunctionLagrangeForm poly = new PolynomialFunctionLagrangeForm(x, y);
            double[] coeff = poly.getCoefficients();
            for (int i = 0; i < coeff.length; i++) {
                coeff[i] = Math.round(coeff[i] * 100.0) / 100.0;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = coeff.length - 1; i >= 0; i--) {
                if (coeff[i] == 0) continue;
                if (sb.length() > 0 && coeff[i] > 0) sb.append(" + ");
                else if (coeff[i] < 0) sb.append(" - ");

                sb.append(Math.abs(coeff[i]));
                if (i > 1) sb.append("x^").append(i);
                else if (i == 1) sb.append("x");
            }

            return sb.toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    private class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            updateCalcButtonState();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateCalcButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {
            updateCalcButtonState();
        }
    }
}
