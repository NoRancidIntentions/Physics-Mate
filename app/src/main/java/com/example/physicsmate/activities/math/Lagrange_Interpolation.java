package com.example.physicsmate.activities.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import org.hipparchus.analysis.polynomials.PolynomialFunctionLagrangeForm;

import static com.example.physicsmate.Custom_methods.*;

public class Lagrange_Interpolation extends AppCompatActivity {

    Button LagrangeAddRowButton, LagrangeCalcButton;
    EditText Lagrangex1, Lagrangey1, Lagrangex2, Lagrangey2;
    TextView LagrangeTvResult;
    LinearLayout LagrangeRowsLinearLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagrange_interpolation);

        LagrangeRowsLinearLayout = findViewById(R.id.LagrangeRowsLinearLayout);
        LagrangeTvResult = findViewById(R.id.LagrangeTvResult);

        Lagrangex1 = findViewById(R.id.Lagrangex1);
        Lagrangey1 = findViewById(R.id.Lagrangey1);
        Lagrangex2 = findViewById(R.id.Lagrangex2);
        Lagrangey2 = findViewById(R.id.Lagrangey2);

        Lagrangex1.addTextChangedListener(new CustomTextWatcher());
        Lagrangey1.addTextChangedListener(new CustomTextWatcher());
        Lagrangex2.addTextChangedListener(new CustomTextWatcher());
        Lagrangey2.addTextChangedListener(new CustomTextWatcher());

        LagrangeAddRowButton = findViewById(R.id.LagrangeAddRowButton);
        LagrangeCalcButton = findViewById(R.id.LagrangeCalcButton);

        LagrangeCalcButton.setClickable(false);
        LagrangeCalcButton.setBackgroundResource(R.drawable.btn_disabled);
        LagrangeCalcButton.setTextColor(getResources().getColor(R.color.app_bg));
        LagrangeCalcButton.setText("Enter all the fields");

        LagrangeTvResult.setVisibility(View.GONE);

        LagrangeAddRowButton.setOnClickListener(this::LagrangeAddRow);

    }

    public void LagrangeAddRow(View view) {
        LinearLayout LagrangeRowsLinearLayout = findViewById(R.id.LagrangeRowsLinearLayout);
        LinearLayout LagrangeRow = (LinearLayout) getLayoutInflater().inflate(R.layout.lagrange_rows, null);
        LagrangeRowsLinearLayout.addView(LagrangeRow);

        LagrangeCalcButtonClickable();

        int LagrangeRowsLinearLayoutChildCount = LagrangeRowsLinearLayout.getChildCount();

        for (int i = 0; i < LagrangeRowsLinearLayoutChildCount; i++) {
            LinearLayout LagrangeRowItem = (LinearLayout) LagrangeRowsLinearLayout.getChildAt(i);
            EditText LagrangeRowItemX = (EditText) LagrangeRowItem.getChildAt(0);
            EditText LagrangeRowItemY = (EditText) LagrangeRowItem.getChildAt(1);
            LagrangeRowItemX.setHint("x" + (i + 1));
            LagrangeRowItemY.setHint("y" + (i + 1));
            LagrangeRowItemX.addTextChangedListener(new CustomTextWatcher());
            LagrangeRowItemY.addTextChangedListener(new CustomTextWatcher());

        }

        //delete listener to each row
        for (int i = 0; i < LagrangeRowsLinearLayoutChildCount; i++) {
            ImageButton LagrangeDeleteRowButton = (ImageButton) LagrangeRow.getChildAt(2);
            LagrangeDeleteRowButton.setOnClickListener(v -> {
                LagrangeRowsLinearLayout.removeView(LagrangeRow);
                LagrangeCalcButtonClickable();
            });
        }

    }


    //use lagrange interpolation from apache library
    public String getPolynomialFunction(double[] x, double[] y) {

        try {
            PolynomialFunctionLagrangeForm interpolator = new PolynomialFunctionLagrangeForm(x, y);

            // Get the polynomial function as a string
            double[] coeff = interpolator.getCoefficients();

            //round off the coefficients to 2 decimal places
            for (int i = 0; i < coeff.length; i++) {
                coeff[i] = Math.round(coeff[i] * 100.0) / 100.0;
            }

            int n = coeff.length;
            StringBuilder sb = new StringBuilder();

            for (int i = n - 1; i >= 0; i--) {
                if (coeff[i] == 0) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(coeff[i] > 0 ? " + " : " - ");
                    sb.append(Math.abs(coeff[i]));
                } else {
                    sb.append(coeff[i]);
                }
                if (i > 1) {
                    sb.append("x^");
                    sb.append(i);
                } else if (i == 1) {
                    sb.append("x");
                }
            }

            return sb.toString();
        } catch (Exception e) {
            return "Error";
        }
    }


    //make btnLagrangeCalc button unclickable if any of the edit texts are empty
    @SuppressLint("ResourceType")
    public void LagrangeCalcButtonClickable() {
        LinearLayout LagrangeRowsLinearLayout = findViewById(R.id.LagrangeRowsLinearLayout);
        int LagrangeRowsLinearLayoutChildCount = LagrangeRowsLinearLayout.getChildCount();
        for (int i = 0; i < LagrangeRowsLinearLayoutChildCount; i++) {
            LinearLayout LagrangeRow = (LinearLayout) LagrangeRowsLinearLayout.getChildAt(i);
            EditText LagrangeRowItemX = (EditText) LagrangeRow.getChildAt(0);
            EditText LagrangeRowItemY = (EditText) LagrangeRow.getChildAt(1);
            String LagrangeRowItemXValue = LagrangeRowItemX.getText().toString().trim();
            String LagrangeRowItemYValue = LagrangeRowItemY.getText().toString().trim();

            if (LagrangeRowItemXValue.isEmpty() || LagrangeRowItemYValue.isEmpty()) {
                LagrangeCalcButton.setClickable(false);
                LagrangeCalcButton.setBackgroundResource(R.drawable.btn_disabled);
                LagrangeCalcButton.setTextColor(getResources().getColor(R.color.app_bg));
                LagrangeCalcButton.setText("Enter all the fields");
                break;
            } else {
                LagrangeCalcButton.setClickable(true);
                //set style of the button
                Paris.styleBuilder(LagrangeCalcButton).add(R.style.custom_button_enabled).apply();
                LagrangeCalcButton.setText("Get polynomial");
            }
        }
    }

    //custom text watcher class to add textChangedListener to all the edit texts
    public class CustomTextWatcher implements TextWatcher {

        public CustomTextWatcher() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            LagrangeCalcButtonClickable();
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            LagrangeCalcButtonClickable();
        }

        @Override
        public void afterTextChanged(Editable s) {
            LagrangeCalcButtonClickable();

            if (LagrangeCalcButton.isClickable()) {
                LagrangeCalcButton.setOnClickListener(v ->
                {
                    //get number of linear layouts inside LagrangeRowsLinearLayout
                    int LagrangeRowsLinearLayoutChildCount = LagrangeRowsLinearLayout.getChildCount();


                    double[] x = new double[LagrangeRowsLinearLayoutChildCount];
                    double[] y = new double[LagrangeRowsLinearLayoutChildCount];
                    for (int i = 0; i < LagrangeRowsLinearLayoutChildCount; i++) {
                        //get the linear layout at index i
                        LinearLayout LagrangeRow = (LinearLayout) LagrangeRowsLinearLayout.getChildAt(i);

                        //get the edit text at index 0 of LagrangeRow
                        EditText LagrangeRowItemX = (EditText) LagrangeRow.getChildAt(0);
                        //get the edit text at index 1
                        EditText LagrangeRowItemY = (EditText) LagrangeRow.getChildAt(1);
                        //get the value of the edit text at index 0
                        String LagrangeRowItemXValue = LagrangeRowItemX.getText().toString().trim();
                        //get the value of the edit text at index 1
                        String LagrangeRowItemYValue = LagrangeRowItemY.getText().toString().trim();
                        //if the edit text at index 0 is empty, dont add it to the array
                        if (LagrangeRowItemXValue.isEmpty() || LagrangeRowItemYValue.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please fill in all the values", Toast.LENGTH_SHORT).show();
                        } else {
                            //convert the value of the edit text at index 0 to double and store it in the x array at index j
                            x[i] = evalf(LagrangeRowItemXValue);
                            //convert the value of the edit text at index 1 to double and store it in the y array at index j
                            y[i] = evalf(LagrangeRowItemYValue);
                        }

                    }

                    String poly = getPolynomialFunction(x, y);
                    String TvLagrangePoly = "<font color=#6BBBE1>The polynomial satisfying the above points is </font> <font color=#CDDC39> y = " + poly + "</font>";
                    String TexPoly = getTex("y= " + poly);

                    LagrangeTvResult.setText(Html.fromHtml(TvLagrangePoly, Html.FROM_HTML_MODE_LEGACY));
                    LagrangeTvResult.setVisibility(View.VISIBLE);


                });
            }
        }

    }
}