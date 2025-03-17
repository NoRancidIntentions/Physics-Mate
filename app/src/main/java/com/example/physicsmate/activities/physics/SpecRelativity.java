package com.example.physicsmate.activities.physics;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.*;

public class SpecRelativity extends AppCompatActivity {

    public static int c = 299792458;

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

            chkProperLength.setChecked(true);
            chkVelocity.setChecked(true);

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

                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);

                Paris.styleBuilder(tv1).add(R.style.custom_textView).apply();
                Paris.styleBuilder(tv2).add(R.style.custom_textView).apply();

                linear_layout.addView(tv1, 5);
                linear_layout.addView(tv2, 7);

                EditText et1 = new EditText(this);
                EditText et2 = new EditText(this);

                Paris.styleBuilder(et1).add(R.style.custom_edittext).apply();
                Paris.styleBuilder(et2).add(R.style.custom_edittext).apply();

                linear_layout.addView(et1, 6);
                linear_layout.addView(et2, 8);

                String s = checkBoxes.stream().filter(CB -> !CB.isChecked()).findFirst().map(CB -> CB.getText().toString()).orElse("Contracted Length");

                //find which checkbox is not checked

                //add textviews to linear layout which is checked
                switch (s) {
                    case "Proper Length":
                        tv1.setText("Enter the contracted length of the moving object, as viewed from the stationary observer");
                        tv2.setText("Enter the velocity of the moving object, as viewed from the stationary observer");
                        break;
                    case "Contracted Length":
                        tv1.setText("Enter the proper length of the moving object, as viewed from the stationary observer");
                        tv2.setText("Enter the velocity of the moving object, as viewed from the stationary observer");
                        break;
                    case "Velocity":
                        tv1.setText("Enter the proper length of the moving object, as viewed from the stationary observer");
                        tv2.setText("Enter the contracted length of the moving object, as viewed from the stationary observer");
                        break;
                }

                Button btnCalc = new Button(this);

                Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();

                btnCalc.setText("Calculate");

                linear_layout.addView(btnCalc, 9);

                TextView tvRes = new TextView(this);

                Paris.styleBuilder(tvRes).add(R.style.custom_textView).apply();

                linear_layout.addView(tvRes, 10);

                //setupEditTextChangeListener(tvRes, btnCalc, cust);

                btnCalc.setOnClickListener(view1 ->
                {
                    switch (s) {
                        case "Proper Length":
                            double contracted_length = evalf(et1.getText().toString());
                            double velocity = evalf(et2.getText().toString());

                            double gamma = 1 / Math.sqrt(1 - (velocity * velocity) / (c * c)); //always greater than 1
                            double proper_length = gamma * contracted_length;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter("The proper length of the moving object is " + proper_length + "units")));

                            break;

                        case "Contracted Length":
                            double proper_length1 = evalf(et1.getText().toString());
                            double velocity1 = evalf(et2.getText().toString());

                            double gamma1 = 1 / Math.sqrt(1 - (velocity1 * velocity1) / (c * c)); //always greater than 1
                            double contracted_length1 = proper_length1 / gamma1;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter("The contracted length of the moving object is " + contracted_length1 + "units")));

                            break;

                        case "Velocity":
                            double proper_length2 = evalf(et1.getText().toString());
                            double contracted_length2 = evalf(et2.getText().toString());

                            double gamma2 = proper_length2 / contracted_length2;
                            double velocity2 = gamma2 * c;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter("The velocity of the moving object is " + velocity2 + "units")));

                            break;
                    }


                });

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