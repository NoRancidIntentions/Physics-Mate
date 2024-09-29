package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

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
            //setContentView(R.layout.math_curvature_parametric);

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
