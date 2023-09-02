package com.example.physicsmate.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import io.github.kexanie.library.MathView;

import static com.example.physicsmate.Custom_methods.*;

public class Gravitation extends AppCompatActivity {

    static double G = 0.00000000006674010551359;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravitation);

        Button btnNLOG, btnGravPot, btnGravPotEnergy, btnGravAcc, btnBinEnergy, btnEscVel, btnOrbVel,
                btnKepThirdLaw, btnGravTimeDil, btnGravLensing, btnGravTorque, btnSchRad,
                btnRoche, btnTidalForce, btnGravWaveEnergy, btnGravWaveFrequency, btnGravWaveLuminosity,
                btnGravRedShift, btnJeansMass;


        btnNLOG = findViewById(R.id.button24);
        btnGravPot = findViewById(R.id.button31);
        btnGravPotEnergy = findViewById(R.id.button36);
        btnGravAcc = findViewById(R.id.button38);
        btnBinEnergy = findViewById(R.id.button32);
        btnEscVel = findViewById(R.id.button34);
        btnOrbVel = findViewById(R.id.button37);
        btnKepThirdLaw = findViewById(R.id.button26);
        btnGravTimeDil = findViewById(R.id.button33);
        btnGravLensing = findViewById(R.id.button35);
        btnGravTorque = findViewById(R.id.button40);
        btnSchRad = findViewById(R.id.button39);
        btnRoche = findViewById(R.id.btnRoche);
        btnTidalForce = findViewById(R.id.btnTidalForce);
        btnGravWaveEnergy = findViewById(R.id.btnGravWaveEnergy);
        btnGravWaveFrequency = findViewById(R.id.btnGravWaveFrequency);
        btnGravWaveLuminosity = findViewById(R.id.btnGravWaveLuminosity);
        btnGravRedShift = findViewById(R.id.btnGravRedShift);
        btnJeansMass = findViewById(R.id.btnJeansMass);


        btnNLOG.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, NLOG.class)));
        btnEscVel.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, EscVelMenu.class)));
        btnGravPot.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravPotentialMenu.class)));
        btnGravPotEnergy.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravPotentialEnergyMenu.class)));
        btnGravAcc.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravAcc.class)));
        btnBinEnergy.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravBinEnergy.class)));
        btnOrbVel.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, OrbVelMenu.class)));
        btnKepThirdLaw.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, KepThirdLaw.class)));
        btnGravTimeDil.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravTimeDilation.class)));
        btnGravLensing.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravLensing.class)));
        btnGravTorque.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravTorque.class)));
        btnSchRad.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, SchRadius.class)));
        btnRoche.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, RocheLimit.class)));
        btnTidalForce.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, TidalForce.class)));
        btnGravWaveEnergy.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravWaveEnergy.class)));
        btnGravWaveFrequency.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravWaveFrequency.class)));
        btnGravWaveLuminosity.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravWaveLuminosity.class)));
        btnGravRedShift.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, GravRedshift.class)));
        btnJeansMass.setOnClickListener(v -> startActivity(new Intent(Gravitation.this, JeansMass.class)));

    }

    public static class EscVelMenu extends AppCompatActivity {


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

        public static class EscVel3Calc extends AppCompatActivity {

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

                ScrollView sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setImageResource(R.drawable.escvelpng);

                int chooserV = getIntent().getIntExtra("chooserV", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);

                Paris.styleBuilder(btnCalc).add(R.style.custom_button_disabled).apply();
                btnCalc.setClickable(false);
                setupEditTextChangeListener(btnCalc, et1, et2);

                if (chooserV == 1) //needs work
                {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Escape Velocity");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                            double m = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double vel = Math.sqrt(2 * G * m / r);
                            String strRes = "<font color = #6BBBE1> Escape velocity = </font> <font color = #CDDC39>" + vel + " m/s </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double vel = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double m = Math.pow(vel, 2) * r / (2 * G);
                            String strRes = "<font color = #6BBBE1> Mass = </font> <font color = #CDDC39>" + m + " kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double m = eval(et1.getText().toString());
                            double vel = eval(et2.getText().toString());

                            double r = (2 * G * m) / Math.pow(vel, 2);
                            String strRes = "<font color = #6BBBE1> Radius = </font> <font color = #CDDC39>" + r + " metres </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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

    public static class GravPotentialMenu extends AppCompatActivity {

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

        public static class GravPotential3Calc extends AppCompatActivity {

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

                ScrollView sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setImageResource(R.drawable.escvelpng);

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
                            double m = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double v = -(G * m / r);
                            String strRes = "<font color = #6BBBE1> Gravitational Potential = </font> <font color = #CDDC39>" + v + " J/Kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double v = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double m = -v * r / G;
                            String strRes = "<font color = #6BBBE1> Mass = </font> <font color = #CDDC39>" + m + " kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double m = eval(et1.getText().toString());
                            double v = eval(et2.getText().toString());

                            double r = -G * m / v;
                            String strRes = "<font color = #6BBBE1> Radius = </font> <font color = #CDDC39>" + r + " metres </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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

    public static class GravPotentialEnergyMenu extends AppCompatActivity {

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
            img.setImageResource(R.drawable.orbvelpng);

            final int[] chooserU = {0};
            final int[] chooserM = {0};
            final int[] chooserR = {0};


            btnU.setOnClickListener(v -> {

                chooserU[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyMenu.GravPotentialEnergyCalc.class).putExtra("chooserU", chooserU[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyMenu.GravPotentialEnergyCalc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(GravPotentialEnergyMenu.this, GravPotentialEnergyMenu.GravPotentialEnergyCalc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class GravPotentialEnergyCalc extends AppCompatActivity {


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
                MathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                ScrollView sv = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);

                img.setImageResource(R.drawable.escvelpng);

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
                            double m1 = eval(et1.getText().toString());
                            double m2 = eval(et2.getText().toString());
                            double r = eval(et3.getText().toString());

                            double u = G * m1 * m2 / r;
                            String strRes = "<font color = #6BBBE1> Gravitational potential energy = </font> <font color = #CDDC39>" + u + " m/s </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            //find m1
                            double u = eval(et1.getText().toString());
                            double m2 = eval(et2.getText().toString());
                            double r = eval(et3.getText().toString());

                            double m1 = u * r / (G * m2);
                            String strRes = "<font color = #6BBBE1> Mass of first object = </font> <font color = #CDDC39>" + m1 + " kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            //find r
                            double u = eval(et1.getText().toString());
                            double m1 = eval(et2.getText().toString());
                            double m2 = eval(et3.getText().toString());

                            double r = G * m1 * m2 / u;
                            String strRes = "<font color = #6BBBE1> Distance = </font> <font color = #CDDC39>" + r + " m </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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

    public static class GravAcc extends AppCompatActivity {

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

                startActivity(new Intent(GravAcc.this, GravAcc.GravAcc3Calc.class).putExtra("chooserA", chooserA[0]));
                finish();
            });

            btnM.setOnClickListener(v -> {

                chooserM[0] = 1;

                startActivity(new Intent(GravAcc.this, GravAcc.GravAcc3Calc.class).putExtra("chooserM", chooserM[0]));
                finish();
            });

            btnR.setOnClickListener(v -> {

                chooserR[0] = 1;

                startActivity(new Intent(GravAcc.this, GravAcc.GravAcc3Calc.class).putExtra("chooserR", chooserR[0]));
                finish();
            });

        }

        public static class GravAcc3Calc extends AppCompatActivity {

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
                MathView mathView = findViewById(R.id.latexView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                ScrollView sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setVisibility(View.GONE);
                mathView.setText(getTex("g = G*M/r^2"));

                int chooserA = getIntent().getIntExtra("chooserA", 0);
                int chooserM = getIntent().getIntExtra("chooserM", 0);
                int chooserR = getIntent().getIntExtra("chooserR", 0);

                //Paris.styleBuilder(btnCalc).add(R.style.custom_button_disabled).apply();
                //btnCalc.setClickable(false);
                setupEditTextChangeListener(btnCalc, et1, et2);

                if (chooserA == 1) //needs work
                {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Gravitational acceleration");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {

                        double m = eval(et1.getText().toString());
                        double r = eval(et2.getText().toString());

                        double acc = G * m / Math.pow(r, 2);
                        String strRes = "<font color = #6BBBE1> Gravitational acceleration = </font> <font color = #CDDC39>" + acc + " m/s </font>";
                        tvRes.setText(Html.fromHtml(strRes));


                        tvRes.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                        btnCopy.setOnClickListener(v1 -> {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Escape velocity", tvRes.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(GravAcc.GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                    });
                } else if (chooserM == 1) {
                    tv1.setText("Enter Gravitational acceleration in m/s²");
                    tv2.setText("Enter Radius in meters");
                    btnCalc.setText("Calculate Mass");
                    et1.setHint("9.8");
                    et2.setHint("6400000");

                    btnCalc.setOnClickListener(v -> {
                            double acc = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double m = acc * Math.pow(r, 2) / G;
                            String strRes = "<font color = #6BBBE1> Mass = </font> <font color = #CDDC39>" + m + " kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


                            tvRes.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);

                            sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                            btnCopy.setOnClickListener(v12 -> {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Mass", tvRes.getText().toString().trim());
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(GravAcc.GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                            });

                    });
                } else if (chooserR == 1) {
                    tv1.setText("Enter Mass in kilograms");
                    tv2.setText("Enter Gravity in m/s²");
                    btnCalc.setText("Calculate Radius");
                    et1.setHint("5.97 * 10^24");
                    et2.setHint("9.8");

                    btnCalc.setOnClickListener(v -> {
                            double m = eval(et1.getText().toString());
                            double acc = eval(et2.getText().toString());

                            double r = Math.sqrt(G * m / acc);
                            String strRes = "<font color = #6BBBE1> Radius = </font> <font color = #CDDC39>" + r + " metres </font>";
                            tvRes.setText(Html.fromHtml(strRes));


                            tvRes.setVisibility(View.VISIBLE);
                            btnCopy.setVisibility(View.VISIBLE);

                            sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                            btnCopy.setOnClickListener(v13 -> {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Distance", tvRes.getText().toString().trim());
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(GravAcc.GravAcc3Calc.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                            });

                    });
                }

            }
        }
    }

    public static class GravBinEnergy extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_binding_energy);
        }
    }

    public static class OrbVelMenu extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_esc_orb_vel_gp_gravacc_menu);

            Button btnV, btnM, btnR;
            ImageView img = findViewById(R.id.imageView26);

            btnV = findViewById(R.id.button41);
            btnM = findViewById(R.id.button42);
            btnR = findViewById(R.id.button43);

            btnV.setText("Orbital Velocity \"V\"");
            img.setImageResource(R.drawable.orbvelpng);

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

        public static class OrbVel3Calc extends AppCompatActivity {

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

                ScrollView sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);
                btnGraph.setVisibility(View.GONE);

                img.setImageResource(R.drawable.orbvelpng);

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
                            double m = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double vel = Math.sqrt(G * m / r);
                            String strRes = "<font color = #6BBBE1> Orbital velocity = </font> <font color = #CDDC39>" + vel + " m/s </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double vel = eval(et1.getText().toString());
                            double r = eval(et2.getText().toString());

                            double m = Math.pow(vel, 2) * r / (G);
                            String strRes = "<font color = #6BBBE1> Mass = </font> <font color = #CDDC39>" + m + " kg </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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
                            double m = eval(et1.getText().toString());
                            double vel = eval(et2.getText().toString());

                            double r = (G * m) / Math.pow(vel, 2);
                            String strRes = "<font color = #6BBBE1> Radius = </font> <font color = #CDDC39>" + r + " metres </font>";
                            tvRes.setText(Html.fromHtml(strRes));


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


    public static class KepThirdLaw extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kep_third_law);
        }
    }

    public static class GravTimeDilation extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_time_dilation);
        }
    }

    public static class GravLensing extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_lensing);
        }
    }

    public static class GravTorque extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_torque);
        }
    }

    public static class SchRadius extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sch_radius);
        }
    }

    public static class RocheLimit extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_roche_limit);
        }
    }

    public static class TidalForce extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tidal_force);
        }
    }

    public static class GravWaveEnergy extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_wave_energy);
        }
    }

    public static class GravWaveFrequency extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_wave_frequency);
        }
    }

    public static class GravWaveLuminosity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_wave_luminosity);
        }
    }

    public static class GravRedshift extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grav_redshift);
        }
    }

    public static class JeansMass extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jeans_mass);
        }
    }

    public static class NLOG extends AppCompatActivity {

        ImageButton btnNLOG1, btnNLOG2, btnNLOG3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nlog);

            btnNLOG1 = findViewById(R.id.btnNLOG1);
            btnNLOG2 = findViewById(R.id.btnNLOG2);
            btnNLOG3 = findViewById(R.id.btnNLOG3);

            btnNLOG1.setOnClickListener(view ->
            {
                Intent intent = new Intent(NLOG.this, NLOG.NLOG1.class);
                startActivity(intent);
                finish();
            });

            btnNLOG2.setOnClickListener(view ->
            {
                Intent intent = new Intent(NLOG.this, NLOG.NLOG2.class);
                startActivity(intent);
                finish();
            });

            btnNLOG3.setOnClickListener(view ->
            {
                Intent intent = new Intent(NLOG.this, NLOG.NLOG3.class);
                startActivity(intent);
                finish();
            });

        }

        public static class NLOG1 extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                Button btnNLOG1calc = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG1result = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG1m1 = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG1m2 = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG1r = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ScrollView SV_NLOG1 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                MathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                img.setImageResource(R.drawable.nlogpic);
                tvNLOG1.setText("Mass of the first object");
                tvNLOG2.setText("Mass of the second object");
                tvNLOG3.setText("Distance between the centre of the objects");

                tvNLOG1result.setVisibility(View.GONE);
                btnCopy.setVisibility(View.GONE);

                et_Scroller(etNLOG1r, NLOG1.this);
                et_Scroller(etNLOG1m1, NLOG1.this);
                et_Scroller(etNLOG1m2, NLOG1.this);

                TextChangeListeners(tvNLOG1result, etNLOG1r, etNLOG1m1, etNLOG1m2);


                btnNLOG1calc.setOnClickListener(view ->
                {

                    if (etNLOG1m1.getText().toString().trim().isEmpty() || etNLOG1m2.getText().toString().trim().isEmpty() || etNLOG1r.getText().toString().trim().isEmpty()) {
                        Toast.makeText(NLOG1.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    } else {

                        double m1 = eval(etNLOG1m1.getText().toString().trim());
                        double m2 = eval(etNLOG1m2.getText().toString().trim());
                        double r = eval(etNLOG1r.getText().toString().trim());

                        double result = ((0.00000000006674010551359 * m1 * m2) / (r * r));

                        String res = "<font color = #CDDC39>" + rounded_StringNLOG(result) + "</font> <font color = #6BBBE1> N </font>";
                        tvNLOG1result.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));

                        SV_NLOG1.post(() -> SV_NLOG1.smoothScrollTo(0, btnCopy.getBottom()));


                        tvNLOG1result.setVisibility(View.VISIBLE);
                        btnCopy.setVisibility(View.VISIBLE);

                        btnCopy.setOnClickListener(view1 ->
                        {

                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("force of attraction", tvNLOG1result.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(NLOG1.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                    }
                });

            }
        }

        public static class NLOG2 extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                Button btnNLOG2calculate = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnNLOG2copy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG2result = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG2m = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG2r = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                EditText etNLOG2n = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ScrollView SV_NLOG2 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                MathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                img.setImageResource(R.drawable.nlogpic);

                tvNLOG1.setText("Mass of the object");
                tvNLOG2.setText("Force between the objects");
                tvNLOG3.setText("Distance between the centre of the objects");


                et_Scroller(etNLOG2r, NLOG2.this);
                et_Scroller(etNLOG2m, NLOG2.this);
                et_Scroller(etNLOG2n, NLOG2.this);

                tvNLOG2result.setVisibility(View.GONE);
                btnNLOG2copy.setVisibility(View.GONE);


                TextChangeListeners(tvNLOG2result, etNLOG2m, etNLOG2r, etNLOG2n);


                btnNLOG2calculate.setOnClickListener(view ->
                {
                    if (etNLOG2m.getText().toString().trim().isEmpty() || etNLOG2n.getText().toString().trim().isEmpty() || etNLOG2r.getText().toString().trim().isEmpty()) {
                        Toast.makeText(NLOG2.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        double m = eval(etNLOG2m.getText().toString().trim());
                        double n = eval(etNLOG2n.getText().toString().trim());
                        double r = eval(etNLOG2r.getText().toString().trim());

                        double result = ((n * r * r) / (m * 0.00000000006674010551359));
                        String res = "<font color = #CDDC39>" + rounded_StringNLOG(result) + "</font> <font color = #6BBBE1> Kg </font>";

                        tvNLOG2result.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));

                        SV_NLOG2.post(() -> SV_NLOG2.smoothScrollTo(0, btnNLOG2copy.getBottom()));

                        tvNLOG2result.setVisibility(View.VISIBLE);
                        btnNLOG2copy.setVisibility(View.VISIBLE);

                        btnNLOG2copy.setOnClickListener(view1 ->
                        {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("mass of second object", tvNLOG2result.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(NLOG2.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });

                    }
                });
            }
        }

        public static class NLOG3 extends AppCompatActivity {

            Button btnNLOG3calc, btnNLOG3copy;
            TextView tvNLOG3result;
            EditText etNLOG3m1, etNLOG3m2, etNLOG3f;
            ScrollView SV_NLOG3;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                btnNLOG3calc = findViewById(R.id.btnCalc_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                btnNLOG3copy = findViewById(R.id.btnCopy_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                tvNLOG3result = findViewById(R.id.tvResults_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                etNLOG3m1 = findViewById(R.id.et1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                etNLOG3m2 = findViewById(R.id.et2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                etNLOG3f = findViewById(R.id.et3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                SV_NLOG3 = findViewById(R.id.SV_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG1 = findViewById(R.id.tv1_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG2 = findViewById(R.id.tv2_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                TextView tvNLOG3 = findViewById(R.id.tv3_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                MathView mathView = findViewById(R.id.latexView_activity_3et_3tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                img.setImageResource(R.drawable.nlogpic);
                tvNLOG1.setText("Mass of the first object");
                tvNLOG2.setText("Mass of the second object");
                tvNLOG3.setText("Force between the objects");


                et_Scroller(etNLOG3f, NLOG3.this);
                et_Scroller(etNLOG3m1, NLOG3.this);
                et_Scroller(etNLOG3m2, NLOG3.this);

                tvNLOG3result.setVisibility(View.GONE);
                btnNLOG3copy.setVisibility(View.GONE);


                TextChangeListeners(tvNLOG3result, etNLOG3f, etNLOG3m1, etNLOG3m2);

                btnNLOG3calc.setOnClickListener(view ->
                {

                    if (etNLOG3m1.getText().toString().trim().isEmpty() || etNLOG3m2.getText().toString().trim().isEmpty() || etNLOG3f.getText().toString().trim().isEmpty()) {
                        Toast.makeText(NLOG3.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    } else {

                        double m1 = eval(etNLOG3m1.getText().toString().trim());
                        double m2 = eval(etNLOG3m2.getText().toString().trim());
                        double n = eval(etNLOG3f.getText().toString().trim());

                        double result = (Math.sqrt((0.00000000006674010551359 * m1 * m2) / n));
                        String res = "<font color = #CDDC39>" + rounded_StringNLOG(result) + "</font> <font color = #6BBBE1> m </font>";

                        tvNLOG3result.setText(Html.fromHtml(res, Html.FROM_HTML_MODE_LEGACY));


                        SV_NLOG3.post(() -> SV_NLOG3.smoothScrollTo(0, btnNLOG3copy.getBottom()));

                        tvNLOG3result.setVisibility(View.VISIBLE);
                        btnNLOG3copy.setVisibility(View.VISIBLE);

                        btnNLOG3copy.setOnClickListener(view1 ->
                        {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Distance between the masses", tvNLOG3result.getText().toString().trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                    }
                });

            }

        }
    }


}