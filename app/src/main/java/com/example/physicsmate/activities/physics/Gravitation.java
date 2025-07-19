package com.example.physicsmate.activities.physics;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import com.agog.mathdisplay.MTMathView;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;
import com.example.physicsmate.ui.main.CustomKeyboard;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.*;

public class Gravitation extends BaseActivity {

    static double G = 0.00000000006674010551359;
    static final float fontSize = 72.f;
    static CustomKeyboard customKeyboard;
    static ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravitation);

        Button btnNLOG, btnGravPot, btnGravPotEnergy, btnGravAcc, btnBinEnergy, btnEscVel, btnOrbVel, btnKepThirdLaw, btnGravTimeDil, btnGravLensing, btnGravTorque, btnSchRad, btnRoche, btnTidalForce, btnGravWaveEnergy, btnGravWaveFrequency, btnGravWaveLuminosity, btnGravRedShift, btnJeansMass;


        btnNLOG = findViewById(R.id.button24);
        btnGravPot = findViewById(R.id.button31);
        btnGravPotEnergy = findViewById(R.id.button36);
        btnGravAcc = findViewById(R.id.button38);
        btnBinEnergy = findViewById(R.id.button32);
        btnEscVel = findViewById(R.id.button34);
        btnOrbVel = findViewById(R.id.button37);
        btnKepThirdLaw = findViewById(R.id.button26);
        btnGravTimeDil = findViewById(R.id.button33);


        btnNLOG.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, NLOG.class)));
        btnEscVel.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, EscVelMenu.class)));
        btnGravPot.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravPotentialMenu.class)));
        btnGravPotEnergy.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravPotentialEnergyMenu.class)));
        btnGravAcc.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravAcc.class)));
        btnBinEnergy.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravBinEnergy.class)));
        btnOrbVel.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, OrbVelMenu.class)));
        btnKepThirdLaw.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, KepThirdLaw.class)));
        btnGravTimeDil.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravTimeDilation.class)));
    }

    public static class EscVelMenu extends BaseActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnV, btnM, btnR;

            btnV = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            final int[] chooserV = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};

            btnV.setOnClickListener(v -> {

                chooserV[0] = 1;

                startActivity(new Intent(EscVelMenu.this, EscVel3Calc.class).putExtra("chooserV", chooserV[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(EscVelMenu.this, EscVel3Calc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(EscVelMenu.this, EscVel3Calc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class EscVel3Calc extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                TextView tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnGraph = findViewById(R.id.btnGraph_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setVisibility(View.GONE);

                int chooserV = getIntent().getIntExtra("chooserV", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);

                Paris.styleBuilder(btnCalc).add(R.style.custom_button_disabled).apply();
                btnCalc.setClickable(false);

                View[] viewsToDisappear = {btnGraph, btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                if (chooserV == 1) //needs work
                {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Escape Velocity");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, EscVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double vel = Math.sqrt(2 * G * m / r);
                        String strRes = "Escape velocity = " + vel + " m/s";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(EscVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Escape velocity", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(EscVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                    });

                } else if (chooserM == 1) {
                    tv1.setText("Enter Escape Velocity in m/s");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Mass");
                    et1.setHint("11180");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, EscVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double vel = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double m = Math.pow(vel, 2) * r / (2 * G);
                        String strRes = "Mass = " + m + " kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(EscVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v12 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mass", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(EscVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserR == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Escape Velocity in m/s");
                    btnCalc.setText("Calculate Radius");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("11180");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, EscVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double vel = evalf(et2.getText().toString());

                        double r = (2 * G * m) / Math.pow(vel, 2);
                        String strRes = "Radius = " + r + " metres";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(EscVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v13 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(EscVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                }

            }
        }
    }

    public static class GravPotentialMenu extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnV, btnM, btnR;

            btnV = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            btnV.setText("Gravitational potential \"V\"");
            btnR.setText("Distance R");

            final int[] chooserV = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};

            btnV.setOnClickListener(v -> {

                chooserV[0] = 1;

                startActivity(new Intent(GravPotentialMenu.this, GravPotential3Calc.class).putExtra("chooserV", chooserV[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(GravPotentialMenu.this, GravPotential3Calc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(GravPotentialMenu.this, GravPotential3Calc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class GravPotential3Calc extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                TextView tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnGraph = findViewById(R.id.btnGraph_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);
                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                View[] viewsToDisappear = {btnGraph, btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setVisibility(View.GONE);

                int chooserV = getIntent().getIntExtra("chooserV", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);

                if (chooserV == 1) //needs work
                {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Distance in meters");
                    btnCalc.setText("Calculate Gravitational Potential");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v1 -> {
                        hideKeyboardOnClick(btnCalc, GravPotential3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double v = -(G * m / r);
                        String strRes = "Gravitational Potential = " + v + " J/Kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotential3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v2 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Gravitational Potential", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotential3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserM == 1) {
                    tv1.setText("Enter Gravitational Potential in J/Kg");
                    tv2.setText("Enter Distance in meters");
                    btnCalc.setText("Calculate Mass");
                    et1.setHint("11180");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v1 -> {
                        hideKeyboardOnClick(btnCalc, GravPotential3Calc.this);
                        customKeyboard.hideKeyboard();
                        double v = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double m = -v * r / G;
                        String strRes = "Mass = " + m + " kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotential3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v12 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mass", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotential3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserR == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Gravitational Potential in J/Kg");
                    btnCalc.setText("Calculate Distance");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("11180");

                    btnCalc.setOnClickListener(v1 -> {
                        hideKeyboardOnClick(btnCalc, GravPotential3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double v = evalf(et2.getText().toString());

                        double r = -G * m / v;
                        String strRes = "Radius = " + r + " metres";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotential3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v13 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotential3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                }
            }
        }
    }

    public static class GravPotentialEnergyMenu extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnU, btnM, btnR;
            ImageView img = findViewById(R.id.imageView26);

            btnU = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            btnU.setText("Gravitational Potential Energy \"U\"");
            btnM.setText("Mass \"m\"");
            btnR.setText("Distance \"r\"");
            img.setVisibility(View.GONE);

            final int[] chooserU = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};


            btnU.setOnClickListener(v -> {

                chooserU[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyCalc.class).putExtra("chooserU", chooserU[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyCalc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyCalc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class GravPotentialEnergyCalc extends BaseActivity {


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                TextView tv1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et3 = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                sv = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                View[] viewsToDisappear = {btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);
                editTextList.add(et3);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2, et3);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);

                img.setVisibility(View.GONE);

                int chooserU = getIntent().getIntExtra("chooserU", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);

                if (chooserU == 1) {
                    tv1.setText("Enter Mass of first object in kilograms");
                    tv2.setText("Enter Mass of second object in kilograms");
                    tv3.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Gravitational Potential Energy");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravPotentialEnergyCalc.this);
                        customKeyboard.hideKeyboard();
                        double m1 = evalf(et1.getText().toString());
                        double m2 = evalf(et2.getText().toString());
                        double r = evalf(et3.getText().toString());

                        double u = G * m1 * m2 / r;
                        String strRes = "Gravitational potential energy = " + u + " m/s";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotentialEnergyCalc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Gravitational Potential Energy", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotentialEnergyCalc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserM == 1) {
                    tv1.setText("Enter Gravitational Potential Energy in joules");
                    tv2.setText("Enter Mass of second object in kilograms");
                    tv3.setText("Enter Distance in meters");
                    btnCalc.setText("Calculate Mass of first object");
                    et1.setHint("-61.22 * 10^6");
                    et2.setHint("7.3 * 10^22");
                    et3.setHint("384400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravPotentialEnergyCalc.this);
                        customKeyboard.hideKeyboard();
                        //find m1
                        double u = evalf(et1.getText().toString());
                        double m2 = evalf(et2.getText().toString());
                        double r = evalf(et3.getText().toString());

                        double m1 = u * r / (G * m2);
                        String strRes = "Mass of first object = " + m1 + " kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotentialEnergyCalc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mass of first object", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotentialEnergyCalc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserR == 1) { //find r
                    tv1.setText("Enter Gravitational Potential Energy in joules");
                    tv2.setText("Enter Mass of first object in kilograms");
                    tv3.setText("Enter Mass of second object in kilograms");
                    btnCalc.setText("Calculate Distance");
                    et1.setHint("-61.22 * 10^6");
                    et2.setHint("5.97 * 10^24");
                    et3.setHint("7.3 * 10^22");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravPotentialEnergyCalc.this);
                        customKeyboard.hideKeyboard();
                        //find r
                        double u = evalf(et1.getText().toString());
                        double m1 = evalf(et2.getText().toString());
                        double m2 = evalf(et3.getText().toString());

                        double r = G * m1 * m2 / u;
                        String strRes = "Distance = " + r + " m";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravPotentialEnergyCalc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravPotentialEnergyCalc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });


                    });
                }

            }

        }
    }

    public static class GravAcc extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnV, btnM, btnR;

            btnV = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            btnV.setText("Gravitational acceleration \"g\"");

            final int[] chooserA = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};

            btnV.setOnClickListener(v -> {

                chooserA[0] = 1;

                startActivity(new Intent(GravAcc.this, GravAcc3Calc.class).putExtra("chooserA", chooserA[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(GravAcc.this, GravAcc3Calc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(GravAcc.this, GravAcc3Calc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class GravAcc3Calc extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                TextView tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnGraph = findViewById(R.id.btnGraph_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                MTMathView mathView = findViewById(R.id.latexView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setVisibility(View.GONE);
                //MathJaxConfig(mathView);
                mathView.setLatex(getTex("g = G*M/r^2", fontSize, mathView));

                int chooserA = getIntent().getIntExtra("chooserA", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);
                View[] viewsToDisappear = {btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                if (chooserA == 1) //needs work
                {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Gravitational acceleration");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravAcc3Calc.this);
                        customKeyboard.hideKeyboard();

                        double m = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double acc = G * m / Math.pow(r, 2);

                        System.out.println(m);
                        System.out.println(acc);

                        String strRes = "Gravitational acceleration = " + acc + " m/s";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravAcc3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Escape velocity", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                    });
                } else if (chooserM == 1) {
                    tv1.setText("Enter Gravitational acceleration in m/s²");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Mass");
                    et1.setHint("9.8");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravAcc3Calc.this);
                        customKeyboard.hideKeyboard();
                        double acc = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double m = acc * Math.pow(r, 2) / G;
                        String strRes = "Mass = " + m + " kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravAcc3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v12 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mass", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserR == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Gravity in m/s²");
                    btnCalc.setText("Calculate Radius");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("9.8");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, GravAcc3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double acc = evalf(et2.getText().toString());

                        double r = Math.sqrt(G * m / acc);
                        String strRes = "Radius = " + r + " metres";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(GravAcc3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v13 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                }

            }
        }
    }

    public static class GravBinEnergy extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_binding_energy);
        }
    }

    public static class OrbVelMenu extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnV, btnM, btnR;
            ImageView img = findViewById(R.id.imageView26);

            img.setVisibility(View.GONE);

            btnV = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            btnV.setText("Orbital Velocity \"V\"");
            img.setVisibility(View.GONE);

            final int[] chooserV = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};


            btnV.setOnClickListener(v -> {

                chooserV[0] = 1;

                startActivity(new Intent(OrbVelMenu.this, OrbVel3Calc.class).putExtra("chooserV", chooserV[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(OrbVelMenu.this, OrbVel3Calc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(OrbVelMenu.this, OrbVel3Calc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });
        }

        public static class OrbVel3Calc extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                TextView tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnGraph = findViewById(R.id.btnGraph_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                View[] viewsToDisappear = {btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setVisibility(View.GONE);

                int chooserV = getIntent().getIntExtra("chooserV", 1);
                int chooserM = getIntent().getIntExtra("chooserM", 1);
                int chooserR = getIntent().getIntExtra("chooserR", 1);

                if (chooserV == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Distance in meters");
                    btnCalc.setText("Calculate Orbital Velocity");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("3.844 * 10^8");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, OrbVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double vel = Math.sqrt(G * m / r);
                        String strRes = "Orbital velocity = " + vel + " m/s";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(OrbVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance between the masses", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(OrbVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserM == 1) {
                    tv1.setText("Enter Orbital Velocity in m/s");
                    tv2.setText("Enter Distance in meters");
                    btnCalc.setText("Calculate Mass");
                    et1.setHint("1000");
                    et2.setHint("3.844 * 10^8");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, OrbVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double vel = evalf(et1.getText().toString());
                        double r = evalf(et2.getText().toString());

                        double m = Math.pow(vel, 2) * r / (G);
                        String strRes = "Mass = " + m + " kg";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(OrbVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v12 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mass", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(OrbVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                } else if (chooserR == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Orbital Velocity in m/s");
                    btnCalc.setText("Calculate Distance");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("3.844 * 10^8");

                    btnCalc.setOnClickListener(v -> {
                        hideKeyboardOnClick(btnCalc, OrbVel3Calc.this);
                        customKeyboard.hideKeyboard();
                        double m = evalf(et1.getText().toString());
                        double vel = evalf(et2.getText().toString());

                        double r = (G * m) / Math.pow(vel, 2);
                        String strRes = "Radius = " + r + " metres";
                        tvRes.setText(Html.fromHtml(HtmlNumberFormatter(OrbVel3Calc.this, strRes)));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v13 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(OrbVel3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    });
                }

            }
        }
    }


    public static class KepThirdLaw extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kep_third_law);
        }
    }

    public static class GravTimeDilation extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_time_dilation);
        }
    }

    public static class NLOG extends BaseActivity {

        Button btnNLOG1, btnNLOG2, btnNLOG3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nlog);

            btnNLOG1 = findViewById(R.id.btnNLOG1);
            btnNLOG2 = findViewById(R.id.btnNLOG2);
            btnNLOG3 = findViewById(R.id.btnNLOG3);

            final boolean[] findFMR = {false, false, false};

            btnNLOG1.setOnClickListener(view -> {
                Intent intent = new Intent(NLOG.this, NLOGcalc.class);
                findFMR[0] = true;
                findFMR[1] = false;
                findFMR[2] = false;
                intent.putExtra("findFMR", findFMR);
                startActivity(intent);
                finish();
            });

            btnNLOG2.setOnClickListener(view -> {
                Intent intent = new Intent(NLOG.this, NLOGcalc.class);
                findFMR[0] = false;
                findFMR[1] = true;
                findFMR[2] = false;
                intent.putExtra("findFMR", findFMR);
                startActivity(intent);
                finish();
            });

            btnNLOG3.setOnClickListener(view -> {
                Intent intent = new Intent(NLOG.this, NLOGcalc.class);
                findFMR[0] = false;
                findFMR[1] = false;
                findFMR[2] = true;
                intent.putExtra("findFMR", findFMR);
                startActivity(intent);
                finish();
            });

        }

        public static class NLOGcalc extends BaseActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                Button btnCalc = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvRes = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et1 = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et2 = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText et3 = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ScrollView sv = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tv3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                MTMathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                View[] viewsToDisappear = {btnCopy, tvRes};
                Collection<EditText> editTextList = new ArrayList<>();
                editTextList.add(et1);
                editTextList.add(et2);
                editTextList.add(et3);

                base_constraint_layout.post(() -> {
                    customKeyboard = getKeyboard();
                    if (customKeyboard != null) {
                        customKeyboard.hideKeyboard();
                        setupEditTextForCustomKeyboard(customKeyboard, sv, et1, et2, et3);
                        setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
                    }
                });

                setLinkedScrollView(sv);

                img.setVisibility(View.GONE);

                Paris.styleBuilder(tvRes).add(R.style.answer_textView).apply();

                tvRes.setVisibility(View.GONE);
                btnCopy.setVisibility(View.GONE);

                et_Scroller(et3, NLOGcalc.this);
                et_Scroller(et1, NLOGcalc.this);
                et_Scroller(et2, NLOGcalc.this);

                btnCopy.setOnClickListener(view1 -> {

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(null, tvRes.getText().toString().trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(NLOGcalc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                });

                boolean[] findFMR = getIntent().getBooleanArrayExtra("findFMR");
                boolean findF = findFMR[0];
                boolean findM = findFMR[1];
                boolean findR = findFMR[2];

                if (findF) {
                    tv1.setText("Mass of the first object");
                    tv2.setText("Mass of the second object");
                    tv3.setText("Distance between the centre of the objects");

                    mathView.setVisibility(View.VISIBLE);
                    mathView.setLatex(getTex("F=G*m1*m2/r^2", fontSize, mathView));

                    btnCalc.setOnClickListener(view -> {
                        hideKeyboardOnClick(btnCalc, NLOGcalc.this);
                        customKeyboard.hideKeyboard();

                        if (et1.getText().toString().trim().isEmpty() || et2.getText().toString().trim().isEmpty() || et3.getText().toString().trim().isEmpty()) {
                            Toast.makeText(NLOGcalc.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else {

                            double m1 = evalf(et1.getText().toString().trim());
                            double m2 = evalf(et2.getText().toString().trim());
                            double r = evalf(et3.getText().toString().trim());

                            double result = ((G * m1 * m2) / (r * r));

                            String res = HtmlNumberFormatter(NLOGcalc.this, String.valueOf(result));
                            tvRes.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));

                            tvRes.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);
                            sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));


                        }
                    });
                } else if (findM) {
                    tv1.setText("Mass of the first object");
                    tv2.setText("Force of attraction");
                    tv3.setText("Distance between the centre of the objects");

                    mathView.setVisibility(View.VISIBLE);
                    mathView.setLatex(getTex("m1=F*r^2/G", fontSize, mathView));

                    btnCalc.setOnClickListener(view -> {
                        hideKeyboardOnClick(btnCalc, NLOGcalc.this);
                        customKeyboard.hideKeyboard();

                        if (et1.getText().toString().trim().isEmpty() || et2.getText().toString().trim().isEmpty() || et3.getText().toString().trim().isEmpty()) {
                            Toast.makeText(NLOGcalc.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else {

                            double m1 = evalf(et1.getText().toString().trim());
                            double f2 = evalf(et2.getText().toString().trim());
                            double r = evalf(et3.getText().toString().trim());

                            double result = r * r * f2 / (G * m1);

                            String res = HtmlNumberFormatter(NLOGcalc.this, String.valueOf(result));
                            tvRes.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));

                            tvRes.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);
                            sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));


                        }
                    });
                } else if (findR) {
                    tv1.setText("Mass of the first object");
                    tv2.setText("Mass of the second object");
                    tv3.setText("Force of attraction");

                    mathView.setVisibility(View.VISIBLE);
                    mathView.setLatex(getTex("r=sqrt(G*m1*m2/F)", fontSize, mathView));

                    btnCalc.setOnClickListener(view -> {
                        hideKeyboardOnClick(btnCalc, NLOGcalc.this);
                        customKeyboard.hideKeyboard();

                        if (et1.getText().toString().trim().isEmpty() || et2.getText().toString().trim().isEmpty() || et3.getText().toString().trim().isEmpty()) {
                            Toast.makeText(NLOGcalc.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else {

                            double m1 = evalf(et1.getText().toString().trim());
                            double m2 = evalf(et2.getText().toString().trim());
                            double f2 = evalf(et3.getText().toString().trim());

                            double result = Math.sqrt((G * m1 * m2) / f2);

                            String res = HtmlNumberFormatter(NLOGcalc.this, String.valueOf(result));
                            tvRes.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));

                            tvRes.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);
                            sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));


                        }
                    });
                }
            }
        }

    }
}