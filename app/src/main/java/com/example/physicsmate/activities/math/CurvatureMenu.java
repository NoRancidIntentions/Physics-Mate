package com.example.physicsmate.activities.math;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.example.physicsmate.Custom_methods.*;

public class CurvatureMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_menu);

        LinearLayout layout = findViewById(R.id.generalMenuLinearLayout);

        //dynamically add 2 buttons, one for each type of curvature, parametric and cartesian
        Button parametricButton = new Button(this);
        parametricButton.setText("Curvature of a 2D/3D Parametric Curve");
        parametricButton.setOnClickListener(v -> startActivity(new Intent(CurvatureMenu.this, CurvatureParametric.class)));
        layout.addView(parametricButton);
        Paris.styleBuilder(parametricButton).add(R.style.custom_button_enabled).apply();


        Button cartesianButton = new Button(this);
        cartesianButton.setText("Curvature of a 2D Cartesian Curve");
        cartesianButton.setOnClickListener(v -> startActivity(new Intent(CurvatureMenu.this, CurvatureCartesian.class)));
        layout.addView(cartesianButton);
        Paris.styleBuilder(cartesianButton).add(R.style.custom_button_enabled).apply();

    }

    public static class CurvatureParametric extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            TextView tv1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv1.setText("enter x(t)");
            TextView tv2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv2.setText("enter y(t)");
            TextView tv3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tv3.setText("enter z(t)");

            EditText et1 = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et1.setHint("cos(t)");
            EditText et2 = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et2.setHint("sin(t)");
            EditText et3 = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            et3.setHint("0");

            TextView tvResult = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            tvResult.setVisibility(View.GONE);
            Button calcButton = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            ScrollView scrollView = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            img.setVisibility(View.GONE);
            MathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCopy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            mathView.setText(getTex("r(t) = x(t) i + y(t) j + z(t) k"));
            btnCopy.setVisibility(View.GONE);

            View[] viewsToDissapear = {tvResult, btnCopy};
            Iterable<EditText> ets = Arrays.asList(et1, et2, et3);

            setupEditTextChangeListener(viewsToDissapear, calcButton, null, ets);

            calcButton.setOnClickListener(v -> {
                String x = et1.getText().toString();
                String y = et2.getText().toString();
                String z = et3.getText().toString();
                mathView.setText(getTex("r(t) = " + x + " i + " + y + " j + " + z + " k"));

                String x_prime = calculateNthDerivative(x, "t", 1);
                String y_prime = calculateNthDerivative(y, "t", 1);
                String z_prime = calculateNthDerivative(z, "t", 1);

                String x_double_prime = calculateNthDerivative(x, "t", 2);
                String y_double_prime = calculateNthDerivative(y, "t", 2);
                String z_double_prime = calculateNthDerivative(z, "t", 2);

                ExprEvaluator util = new ExprEvaluator();

                try {
                    // Evaluate cross product r'(t) x r''(t)
                    String crossProductX = "(" + y_prime + "*" + z_double_prime + " - " + z_prime + "*" + y_double_prime + ")";
                    String crossProductY = "(" + z_prime + "*" + x_double_prime + " - " + x_prime + "*" + z_double_prime + ")";
                    String crossProductZ = "(" + x_prime + "*" + y_double_prime + " - " + y_prime + "*" + x_double_prime + ")";

                    String crossProductMagnitude = "Sqrt[" + crossProductX + "^2 + " + crossProductY + "^2 + " + crossProductZ + "^2]";

                    // Evaluate magnitude of r'(t)
                    String rPrimeMagnitude = "Sqrt[" + x_prime + "^2 + " + y_prime + "^2 + " + z_prime + "^2]";

                    // Curvature formula: |r'(t) x r''(t)| / |r'(t)|^3
                    IExpr numerator = util.eval(crossProductMagnitude);
                    IExpr denominator = util.eval(rPrimeMagnitude + "^3");

                    IExpr result = util.eval("Abs[" + numerator + " / " + denominator + "]");

                    tvResult.setText(result.toString());
                    tvResult.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                    mathView.setVisibility(View.VISIBLE);
                    btnCopy.setVisibility(View.VISIBLE);

                    //add a button to ask if user wants to evaluate the curvature at a specific point
                    Button btnEvaluateAtPoint = new Button(this);
                    btnEvaluateAtPoint.setText("Evaluate Curvature at a specific point");
                    btnEvaluateAtPoint.setOnClickListener(v1 -> {
                        //dynamically add value of 't' in editText, and a textview to ask for that value
                        EditText etEvaluate = new EditText(this);
                        etEvaluate.setHint("t");
                        ((LinearLayout) findViewById(R.id.generalMenuLinearLayout)).addView(etEvaluate);
                        TextView tvEvaluate = new TextView(this);
                        tvEvaluate.setText("Enter numeric value for t: ");
                        ((LinearLayout) findViewById(R.id.generalMenuLinearLayout)).addView(tvEvaluate);
                        Button btnCalc2 = new Button(this);
                        btnCalc2.setText("Calculate");
                        ((LinearLayout) findViewById(R.id.generalMenuLinearLayout)).addView(btnCalc2);


                        btnCalc2.setOnClickListener(v2 -> {
                            Double t = evalf(etEvaluate.getText().toString());
                            //retrieve the expression from the previous textview
                            String expression = tvResult.getText().toString();
                            double result2 = evalAtPointX(expression, t);

                            TextView tvResult2 = new TextView(this);
                            tvResult2.setText(formatText("Curvature at t = " + t + " is " + result2));
                            ((LinearLayout) findViewById(R.id.generalMenuLinearLayout)).addView(tvResult2);

                            setupEditTextChangeListener(new View[]{tvResult2}, btnCalc2, null, Collections.singletonList(etEvaluate));
                        });
                        ((LinearLayout) findViewById(R.id.generalMenuLinearLayout)).addView(btnEvaluateAtPoint);
                        Paris.styleBuilder(btnEvaluateAtPoint).add(R.style.custom_button_enabled).apply();

                    });

                } catch (Exception e) {
                    tvResult.setText("Error: " + e.getMessage());
                    tvResult.setVisibility(View.VISIBLE);
                }
            });

            btnCopy.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text", tvResult.getText().toString());
                clipboard.setPrimaryClip(clip);
            });

        }
    }

    public static class CurvatureCartesian extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.math_curvature_cartesian);

        }
    }
}
