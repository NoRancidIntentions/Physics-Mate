package com.example.physicsmate.activities.physics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

public class SpecRelativity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);

        Button btnLengthContraction, btnTimeDilation, btnVelocityAddition, btnDopplerEffect;
        LinearLayout linear_layout = findViewById(R.id.linear_layout);

        btnLengthContraction = new Button(this);
        btnTimeDilation = new Button(this);
        btnVelocityAddition = new Button(this);
        btnDopplerEffect = new Button(this);

        Paris.styleBuilder(btnLengthContraction).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnTimeDilation).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnVelocityAddition).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnDopplerEffect).add(R.style.custom_button_enabled).apply();

        btnLengthContraction.setText("Length Contraction");
        btnTimeDilation.setText("Time Dilation");
        btnVelocityAddition.setText("Velocity Addition");
        btnDopplerEffect.setText("Doppler Effect");

        linear_layout.addView(btnLengthContraction, 0);
        linear_layout.addView(btnTimeDilation, 1);
        linear_layout.addView(btnVelocityAddition, 2);
        linear_layout.addView(btnDopplerEffect, 3);

        btnLengthContraction.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, LengthContraction.class);
            startActivity(intent);
        });
        btnTimeDilation.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, TimeDilation.class);
            startActivity(intent);
        });
        btnVelocityAddition.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, VelocityAddition.class);
            startActivity(intent);
        });
        btnDopplerEffect.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, DopplerEffect.class);
            startActivity(intent);
        });
    }

    public static class LengthContraction extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }

    public static class TimeDilation extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }

    public static class VelocityAddition extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }

    public static class DopplerEffect extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }


}