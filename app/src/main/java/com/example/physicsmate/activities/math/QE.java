package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import static com.example.physicsmate.Custom_methods.*;


public class QE extends AppCompatActivity {

    EditText a, b, c, graphxmin, graphxmax;
    Button btnCal, btnQE2, btnQEArea, btngraphcalc;
    TextView results1;
    TextView results2;
    TextView results3;
    TextView tvxmin;
    TextView tvxmax;
    TextView tvQEArea;
    ScrollView SV_QE;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qe);

        a = findViewById(R.id.editTextNumberDecimal19);
        b = findViewById(R.id.editTextNumberDecimal20);
        c = findViewById(R.id.editTextNumberDecimal21);
        graphxmin = findViewById(R.id.editTextNumberDecimal16);
        graphxmax = findViewById(R.id.editTextNumberDecimal17);
        SV_QE = findViewById(R.id.SV_QE);

        btnCal = findViewById(R.id.button9);
        tvQEArea = findViewById(R.id.tvQEArea);
        btnQEArea = findViewById(R.id.btnQEArea);
        btnQE2 = findViewById(R.id.btnQE2);
        results1 = findViewById(R.id.textView23);
        results2 = findViewById(R.id.textView25);
        results3 = findViewById(R.id.textView26);
        tvxmin = findViewById(R.id.textView27);
        tvxmax = findViewById(R.id.textView24);
        btngraphcalc = findViewById(R.id.button11);

        Paris.styleBuilder(results1).add(R.style.answer_textView).apply();
        Paris.styleBuilder(results2).add(R.style.answer_textView).apply();
        Paris.styleBuilder(results3).add(R.style.answer_textView).apply();


        tvQEArea.setVisibility(View.GONE);
        results1.setVisibility(View.GONE);
        results2.setVisibility(View.GONE);
        results3.setVisibility(View.GONE);
        btnQE2.setVisibility(View.GONE);
        btnQEArea.setVisibility(View.GONE);
        graphxmin.setVisibility(View.GONE);
        graphxmax.setVisibility(View.GONE);
        tvxmax.setVisibility(View.GONE);
        tvxmin.setVisibility(View.GONE);
        btngraphcalc.setVisibility(View.GONE);

        et_Scroller(a, QE.this);

        et_Scroller(b, QE.this);

        et_Scroller(c, QE.this);

        String DefBtn = "Get more information about the equation";

        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtChange(DefBtn);
            }
        });

        b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtChange(DefBtn);
            }
        });

        c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtChange(DefBtn);
            }
        });

        graphxmin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvQEArea.setVisibility(View.GONE);
                tvQEArea.setText(null);
            }
        });

        graphxmax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvQEArea.setVisibility(View.GONE);
                tvQEArea.setText(null);
            }
        });

        btnCal.setOnClickListener(view ->
        {
            if (a.getText().toString().isEmpty() || b.getText().toString().isEmpty() || c.getText().toString().isEmpty()) {
                Toast.makeText(QE.this, "Please enter all the values", Toast.LENGTH_SHORT).show();
            } else if (a.getText().toString().equals("0")) {
                Toast.makeText(QE.this, " 'a' cannot be 0!", Toast.LENGTH_SHORT).show();
            } else {
                double A = evalf(a.getText().toString().trim());
                double B = evalf(b.getText().toString().trim());
                double C = evalf(c.getText().toString().trim());

                double D = (B * B) - (4 * A * C);
                if (D < 0) {
                    String result = "The given equation does not have any real roots";
                    results1.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    double x1 = (-B + Math.sqrt(D)) / (2 * A);
                    double x2 = (-B - Math.sqrt(D)) / (2 * A);

                    String result = "The roots of the given equation are " + HtmlNumberFormatter(QE.this, rounded_String(x1, a, b, c)) + " and " + HtmlNumberFormatter(QE.this, rounded_String(x2, a, b, c));
                    results1.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                }
                results1.setVisibility(View.VISIBLE);

                String result2 = "The derivative of the equation is " + HtmlColoriser(QE.this, rounded_String(2 * A, a, b, c) + "x +  " + rounded_String(B, a, b, c));
                results2.setText(Html.fromHtml(result2, Html.FROM_HTML_MODE_LEGACY));
                results2.setVisibility(View.VISIBLE);

                double pom = (-B) / (2 * A);  //x
                double valy = (pom * pom * A) + (B * pom) + C;
                String result;  //x

                if (A < 0)  //maxima
                {
                    result = "At x = " + HtmlNumberFormatter(QE.this, rounded_String(pom)) + ", the value of the given equation will be maximum, that is, y = " + HtmlNumberFormatter(QE.this, rounded_String(valy));
                } else {
                    result = "At x = " + HtmlNumberFormatter(QE.this, rounded_String(pom)) + ", the value of the given equation will be minimum, that is, y = " + HtmlNumberFormatter(QE.this, rounded_String(valy));
                }

                results3.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                results3.setVisibility(View.VISIBLE);
                btnQE2.setVisibility(View.VISIBLE);

                SV_QE.post(() -> SV_QE.smoothScrollTo(0, btnCal.getBottom()));

            }


            btnQE2.setOnClickListener(view1 ->
            {
                tvxmin.setVisibility(View.VISIBLE);
                tvxmax.setVisibility(View.VISIBLE);
                graphxmin.setVisibility(View.VISIBLE);
                graphxmax.setVisibility(View.VISIBLE);
                btngraphcalc.setVisibility(View.VISIBLE);
                btnQEArea.setVisibility(View.VISIBLE);

                SV_QE.post(() -> SV_QE.smoothScrollTo(0, btnQEArea.getBottom()));

            });

            btnQEArea.setOnClickListener(view13 ->
            {
                if (graphxmin.getText().toString().trim().isEmpty() || graphxmax.getText().toString().trim().isEmpty()) {
                    Toast.makeText(QE.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else {

                    double A = evalf(a.getText().toString().trim());
                    double B = evalf(b.getText().toString().trim());
                    double C = evalf(c.getText().toString().trim());

                    int xmin = Integer.parseInt(graphxmin.getText().toString().trim());
                    int xmax = Integer.parseInt(graphxmax.getText().toString().trim());

                    if (xmin > xmax) {
                        Toast.makeText(QE.this, "Minimum value of x must be smaller than maximum value of x", Toast.LENGTH_LONG).show();
                    } else {
                        // (a(xmax^3)/3) + (b(xmax^2))/2 + c(xmax) - (a(xmin^3)/3) + (b(xmin^2))/2 + c(xmin),     xmax - xmin


                        double tot_area = (A * (xmax * xmax * xmax) / 3) + (B * (xmax * xmax)) / 2 + C * (xmax) - ((A * (xmin * xmin * xmin) / 3) + (B * (xmin * xmin)) / 2 + C * (xmin));

                        String area = "Area under the curve between " + rounded_String(xmin) + " and " + rounded_String(xmax) + " is " + HtmlNumberFormatter(QE.this, rounded_String(tot_area)) + "<br><br>";

                        tvQEArea.setText(Html.fromHtml(area, Html.FROM_HTML_MODE_LEGACY));
                        tvQEArea.setVisibility(View.VISIBLE);
                        SV_QE.post(() -> SV_QE.smoothScrollTo(0, btngraphcalc.getBottom()));

                    }
                }
            });

            btngraphcalc.setOnClickListener(view12 ->
            {

                if (graphxmin.getText().toString().trim().isEmpty() || graphxmax.getText().toString().trim().isEmpty()) {
                    Toast.makeText(QE.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else {

                    double A = evalf(a.getText().toString().trim());
                    double B = evalf(b.getText().toString().trim());
                    double C = evalf(c.getText().toString().trim());

                    int xmin = Integer.parseInt(graphxmin.getText().toString().trim());
                    int xmax = Integer.parseInt(graphxmax.getText().toString().trim());

                    if (xmin > xmax) {
                        Toast.makeText(QE.this, "Minimum value of x must be smaller than maximum value of x", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(QE.this, QE_Graph.class);

                        intent.putExtra("xmin", xmin);
                        intent.putExtra("xmax", xmax);
                        intent.putExtra("A", A);
                        intent.putExtra("B", B);
                        intent.putExtra("C", C);

                        startActivity(intent);

                    }
                }
            });
        });


    }

    public void txtChange(String defBtn) {
        btnCal.setText(defBtn);
        btnQEArea.setVisibility(View.GONE);
        btngraphcalc.setVisibility(View.GONE);
        btnQE2.setVisibility(View.GONE);
        results1.setVisibility(View.GONE);
        results2.setVisibility(View.GONE);
        results3.setVisibility(View.GONE);
        tvxmin.setVisibility(View.GONE);
        tvxmax.setVisibility(View.GONE);
        graphxmax.setVisibility(View.GONE);
        graphxmin.setVisibility(View.GONE);
        tvQEArea.setVisibility(View.GONE);
        results1.setText(null);
        results2.setText(null);
        results3.setText(null);
        graphxmax.setText(null);
        graphxmin.setText(null);
        tvQEArea.setText(null);
    }

    public static class QE_Graph extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.line_chart_universal);

            LineChart mChart = findViewById(R.id.lineChart);
            ArrayList<Entry> yValues = mChartProperties(mChart);

            int xmin = getIntent().getIntExtra("xmin", 0);
            int xmax = getIntent().getIntExtra("xmax", 10);

            //________________________________________________________________________
            double A = getIntent().getDoubleExtra("A", 1.0);
            double B = getIntent().getDoubleExtra("B", 0.0);
            double C = getIntent().getDoubleExtra("C", 0.0);

            for (double lineChartX = xmin; lineChartX < xmax; lineChartX = lineChartX + ((xmax - xmin) / 5000f)) {
                yValues.add(new Entry((float) lineChartX, (float) ((float) A * lineChartX * lineChartX + B * lineChartX + C)));
            }
            //________________________________________________________________________

            mChartDataSetter(mChart, this, true, yValues);

        }


    }
}
