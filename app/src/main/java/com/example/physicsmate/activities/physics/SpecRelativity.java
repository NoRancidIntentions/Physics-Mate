package com.example.physicsmate.activities.physics;

import android.annotation.SuppressLint;
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
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.*;

public class SpecRelativity extends AppCompatActivity {

    public static int c = 299792458;

    @SuppressLint("SetTextI18n")
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

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            CheckBox chkProperLength, chkContractedLength, chkVelocity;

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);

            customKeyboard.setVisibility(View.GONE);

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
            Button btnCopy = new Button(this);
            Button btnCalc = new Button(this);

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tvRes = new TextView(this);

            Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

            EditText et1 = new EditText(this);
            EditText et2 = new EditText(this);

            chkProperLength.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes, tv1, tv2, et1, et2, btnCalc, tvRes, btnCopy));

            chkContractedLength.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes, tv1, tv2, et1, et2, btnCalc, tvRes, btnCopy));

            chkVelocity.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes, tv1, tv2, et1, et2, btnCalc, tvRes, btnCopy));

            Paris.styleBuilder(btnNext).add(R.style.custom_button_enabled).apply();
            Paris.styleBuilder(btnClear).add(R.style.custom_button_enabled).apply();
            Paris.styleBuilder(btnCopy).add(R.style.custom_button_enabled).apply();

            btnNext.setText("Next");
            btnClear.setText("Clear");
            btnCopy.setText("Copy");

            linear_layout.addView(btnNext, 3);
            linear_layout.addView(btnClear, 4);

            btnCopy.setVisibility(View.GONE);
            btnCalc.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            btnCopy.setVisibility(View.GONE);

            Paris.styleBuilder(tv1).add(R.style.custom_textView).apply();
            Paris.styleBuilder(tv2).add(R.style.custom_textView).apply();


            Paris.styleBuilder(et1).add(R.style.custom_edittext).apply();
            Paris.styleBuilder(et2).add(R.style.custom_edittext).apply();

            linear_layout.addView(tv1, 5);
            linear_layout.addView(et1, 6);
            linear_layout.addView(tv2, 7);
            linear_layout.addView(et2, 8);

            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            et1.setVisibility(View.GONE);
            et2.setVisibility(View.GONE);

            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();

            btnCalc.setText("Calculate");

            linear_layout.addView(btnCalc, 9);

            Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

            linear_layout.addView(tvRes, 10);
            linear_layout.addView(btnCopy, 11);

            tvRes.setVisibility(View.GONE);

            View[] viewsToHide = {tvRes};
            setupEditTextChangeListener(viewsToHide, btnCalc, customKeyboard, Arrays.asList(et1, et2));

            setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2);

            btnNext.setOnClickListener(view -> {

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

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                et1.setVisibility(View.VISIBLE);
                et2.setVisibility(View.VISIBLE);

                btnCalc.setVisibility(View.VISIBLE);

                btnCalc.setOnClickListener(view1 -> {
                    switch (s) {
                        case "Proper Length":
                            double contracted_length = evalf(et1.getText().toString());
                            double velocity = evalf(et2.getText().toString());

                            double beta = velocity / c;
                            double gamma = 1 / (Math.sqrt(1 - beta * beta));

                            double proper_length = gamma * contracted_length;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter(LengthContraction.this, "The proper length of the moving object is " + proper_length + " units")));

                            break;

                        case "Contracted Length":
                            double proper_length1 = evalf(et1.getText().toString());
                            double velocity1 = evalf(et2.getText().toString());

                            double beta1 = velocity1 / c;

                            double gamma1 = 1 / (Math.sqrt(1 - beta1 * beta1));
                            System.out.println(gamma1);
                            double contracted_length1 = proper_length1 / gamma1;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter(LengthContraction.this, "The contracted length of the moving object is " + contracted_length1 + " units")));

                            break;

                        case "Velocity":
                            double proper_length2 = evalf(et1.getText().toString());
                            double contracted_length2 = evalf(et2.getText().toString());

                            double gamma2 = proper_length2 / contracted_length2;
                            double beta2 = 1 - 1 / (gamma2 * gamma2);

                            double velocity2 = beta2 * c;

                            tvRes.setText(Html.fromHtml(HtmlNumberFormatter(LengthContraction.this, "The velocity of the moving object is " + velocity2 + " units")));

                            break;
                    }

                    tvRes.setVisibility(View.VISIBLE);
                    btnCopy.setVisibility(View.VISIBLE);

                });

                btnCopy.setOnClickListener(view1 -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Text", extractNumberFromString(tvRes.getText().toString()));
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
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

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);
        }
    }

    public static class VelocityAddition extends AppCompatActivity {

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);
        }
    }

    public static class DopplerEffect extends AppCompatActivity {

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);
        }
    }

    public static class RelativisiticPosition extends AppCompatActivity {

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);
        }
    }

    public static class RelativisticTime extends AppCompatActivity {

        custom_keyboard1_initializer customKeyboard;
        ScrollView sv;

        @Override
        public void onBackPressed() {
            //if system back btn is pressed, and if keyboard is visible, hide it
            if (customKeyboard.getVisibility() == View.VISIBLE) {
                customKeyboard.setVisibility(View.GONE);
                sv.setPadding(0, 0, 0, 0);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_activity);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_blank_activity);
            sv = findViewById(R.id.sv_blank_activity);
        }
    }


}