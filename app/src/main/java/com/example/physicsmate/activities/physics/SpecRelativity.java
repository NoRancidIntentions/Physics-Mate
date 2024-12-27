package com.example.physicsmate.activities.physics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.CB_MainMethod;

public class SpecRelativity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);

        Button btnLengthContraction, btnTimeDilation, btnVelocityAddition, btnDopplerEffect, btnRelativisiticPosition, btnRelativisticTime;
        LinearLayout linear_layout = findViewById(R.id.linear_layout);

        btnLengthContraction = new Button(this);
        btnTimeDilation = new Button(this);
        btnVelocityAddition = new Button(this);
        btnDopplerEffect = new Button(this);
        btnRelativisiticPosition = new Button(this);
        btnRelativisticTime = new Button(this);

        Paris.styleBuilder(btnLengthContraction).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnTimeDilation).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnVelocityAddition).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnDopplerEffect).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnRelativisiticPosition).add(R.style.custom_button_enabled).apply();
        Paris.styleBuilder(btnRelativisticTime).add(R.style.custom_button_enabled).apply();

        btnLengthContraction.setText("Length Contraction");
        btnTimeDilation.setText("Time Dilation");
        btnVelocityAddition.setText("Velocity Addition");
        btnDopplerEffect.setText("Doppler Effect");
        btnRelativisiticPosition.setText("Relativistic Position");
        btnRelativisticTime.setText("Relativistic Time");

        linear_layout.addView(btnLengthContraction, 0);
        linear_layout.addView(btnTimeDilation, 1);
        linear_layout.addView(btnVelocityAddition, 2);
        linear_layout.addView(btnDopplerEffect, 3);
        linear_layout.addView(btnRelativisiticPosition, 4);
        linear_layout.addView(btnRelativisticTime, 5);

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

        btnRelativisiticPosition.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, RelativisiticPosition.class);
            startActivity(intent);
        });

        btnRelativisticTime.setOnClickListener(view -> {
            Intent intent = new Intent(SpecRelativity.this, RelativisticTime.class);
            startActivity(intent);
        });
    }

    public static class LengthContraction extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            CheckBox chkProperLength, chkContractedLength, chkVelocity;

            chkProperLength = new CheckBox(this);
            chkContractedLength = new CheckBox(this);
            chkVelocity = new CheckBox(this);

            Paris.styleBuilder(chkProperLength).add(R.style.custom_checkBox).apply();
            Paris.styleBuilder(chkContractedLength).add(R.style.custom_checkBox).apply();
            Paris.styleBuilder(chkVelocity).add(R.style.custom_checkBox).apply();

            chkProperLength.setText("Proper Length");
            chkContractedLength.setText("Contracted Length");
            chkVelocity.setText("Velocity");

            LinearLayout linear_layout = findViewById(R.id.linear_layout);

            linear_layout.addView(chkProperLength, 0);
            linear_layout.addView(chkContractedLength, 1);
            linear_layout.addView(chkVelocity, 2);

            final int[] a = {0};
            int max_CB_Required = 2;

            Collection<CheckBox> checkBoxes = new ArrayList<>();
            checkBoxes.add(chkProperLength);
            checkBoxes.add(chkContractedLength);
            checkBoxes.add(chkVelocity);

            Button btnNext = new Button(this);
            Button btnClear = new Button(this);

            chkProperLength.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes));

            chkContractedLength.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes));

            chkVelocity.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes));

            Paris.styleBuilder(btnNext).add(R.style.custom_button_enabled).apply();
            Paris.styleBuilder(btnClear).add(R.style.custom_button_enabled).apply();

            btnNext.setText("Next");
            btnClear.setText("Clear");

            linear_layout.addView(btnNext, 3);
            linear_layout.addView(btnClear, 4);

            btnNext.setOnClickListener(view -> {

            });

            btnClear.setOnClickListener(view -> {
                chkProperLength.setChecked(false);
                chkContractedLength.setChecked(false);
                chkVelocity.setChecked(false);

                chkProperLength.setClickable(true);
                chkContractedLength.setClickable(true);
                chkVelocity.setClickable(true);

                btnNext.setClickable(false);
                btnNext.setEnabled(false);
                btnNext.setBackgroundResource(R.drawable.btn_disabled);
                btnClear.setVisibility(View.GONE);

                a[0] = 0;
            });
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

    public static class RelativisiticPosition extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }

    public static class RelativisticTime extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);
        }
    }


}