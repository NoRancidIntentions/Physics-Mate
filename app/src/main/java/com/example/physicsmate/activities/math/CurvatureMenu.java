package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;

import java.util.Arrays;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.getTex;
import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;

public class CurvatureMenu extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

    public static class CurvatureParametric extends AppCompatActivity
    {
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
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

            mathView.setText(getTex("r(t) = x(t) i + y(t) j + z(t) k"));

            View[] viewsToDissapear = {tvResult};
            Iterable<EditText> ets = Arrays.asList(et1, et2, et3);

            setupEditTextChangeListener(viewsToDissapear, calcButton, null, ets);

            calcButton.setOnClickListener(v ->
            {
                String x = et1.getText().toString();
                String y = et2.getText().toString();
                String z = et3.getText().toString();
                mathView.setText(getTex("r(t) = " + x + " i + " + y + " j + " + z + " k"));

                String curvature;
            });
        }
    }

    public static class CurvatureCartesian extends AppCompatActivity
    {
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.math_curvature_cartesian);

        }
    }
}
