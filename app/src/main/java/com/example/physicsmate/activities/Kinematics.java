package com.example.physicsmate.activities;

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
import com.example.physicsmate.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import org.jetbrains.annotations.NotNull;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.physicsmate.Custom_methods.*;


public class Kinematics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinematics);

        Button KE1, KE2;

        KE1 = findViewById(R.id.button16);
        KE2 = findViewById(R.id.button14);

        KE1.setOnClickListener(view -> {
            Intent intent = new Intent(Kinematics.this, KE1.class);
            startActivity(intent);
        });

        KE2.setOnClickListener(view -> {
            Intent intent = new Intent(Kinematics.this, Kinematics.KE2.class);
            startActivity(intent);
        });


    }

    public static class KE1 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ke1);

            CheckBox CB_acc, CB_u, CB_v, CB_t, CB_s;
            Button btn_next, btn_clear;

            CB_acc = findViewById(R.id.checkBox2);
            CB_u = findViewById(R.id.checkBox5);
            CB_v = findViewById(R.id.checkBox);
            CB_t = findViewById(R.id.checkBox3);
            CB_s = findViewById(R.id.checkBox4);

            btn_next = findViewById(R.id.button18);
            btn_clear = findViewById(R.id.button20);

            btn_clear.setVisibility(View.GONE);

            btn_next.setClickable(false);
            btn_next.setBackgroundResource(R.drawable.btn_disabled);

            final int[] a = {0};
            int max_CB_Required = 3;

            btn_clear.setOnClickListener(view -> {
                CB_acc.setChecked(false);
                CB_v.setChecked(false);
                CB_u.setChecked(false);
                CB_t.setChecked(false);
                CB_s.setChecked(false);

                CB_acc.setClickable(true);
                CB_s.setClickable(true);
                CB_t.setClickable(true);
                CB_v.setClickable(true);
                CB_u.setClickable(true);

                btn_next.setClickable(false);
                btn_next.setBackgroundResource(R.drawable.btn_disabled);
                btn_clear.setVisibility(View.GONE);

                a[0] = 0;
            });

            CB_acc.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, CB_acc, CB_u, CB_v, CB_t, CB_s));

            CB_t.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, CB_acc, CB_u, CB_v, CB_t, CB_s));

            CB_u.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, CB_acc, CB_u, CB_v, CB_t, CB_s));

            CB_v.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, CB_acc, CB_u, CB_v, CB_t, CB_s));

            CB_s.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, CB_acc, CB_u, CB_v, CB_t, CB_s));

            btn_next.setOnClickListener(view -> {

                boolean bool_acc = CB_acc.isChecked();
                boolean u_isChecked = CB_u.isChecked();
                boolean v_isChecked = CB_v.isChecked();
                boolean t_isChecked = CB_t.isChecked();
                boolean s_isChecked = CB_s.isChecked();

                Intent intent = new Intent(KE1.this, KE1.KE1_1.class);

                intent.putExtra("bool_acc", bool_acc);
                intent.putExtra("u_isChecked", u_isChecked);
                intent.putExtra("v_isChecked", v_isChecked);
                intent.putExtra("s_isChecked", s_isChecked);
                intent.putExtra("t_isChecked", t_isChecked);

                startActivity(intent);
            });

        }


        public static class KE1_1 extends AppCompatActivity {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ke11);

                TextView textView37, textView39, textView40;
                EditText editTextNumber, editTextNumber2, editTextNumber3;
                Button cal, KE11_graphButton;
                TextView tv_results;

                boolean bool_acc = getIntent().getBooleanExtra("bool_acc", false);
                boolean bool_u = getIntent().getBooleanExtra("u_isChecked", false);
                boolean bool_v = getIntent().getBooleanExtra("v_isChecked", false);
                boolean bool_s = getIntent().getBooleanExtra("s_isChecked", false);

                cal = findViewById(R.id.button19);
                textView37 = findViewById(R.id.textView37);
                textView39 = findViewById(R.id.textView39);
                textView40 = findViewById(R.id.textView40);
                editTextNumber = findViewById(R.id.editTextNumber);
                editTextNumber2 = findViewById(R.id.editTextNumber2);
                editTextNumber3 = findViewById(R.id.editTextNumber3);
                tv_results = findViewById(R.id.textView41);
                KE11_graphButton = findViewById(R.id.button25);

                tv_results.setVisibility(View.GONE);
                KE11_graphButton.setVisibility(View.GONE);

                if (bool_s) {
                    String tv = "Please enter displacement in SI unit";
                    textView37.setText(tv);
                } else if (bool_v) {
                    String tv = "Please enter final velocity in SI unit";
                    textView37.setText(tv);
                    bool_v = false;
                } else if (bool_u) {
                    String tv = "Please enter initial velocity in SI unit";
                    textView37.setText(tv);
                    bool_u = false;
                } else if (bool_acc) {
                    String tv = "Please enter acceleration in SI unit";
                    textView37.setText(tv);
                    bool_acc = false;
                } else {
                    String tv = "Please enter time in SI unit";
                    textView37.setText(tv);
                }

                //________________________________________________________________________

                if (bool_v) {
                    String tv = "Please enter final velocity in SI unit";
                    textView39.setText(tv);
                } else if (bool_u) {
                    String tv = "Please enter initial velocity in SI unit";
                    textView39.setText(tv);
                    bool_u = false;
                } else if (bool_acc) {
                    String tv = "Please enter acceleration in SI unit";
                    textView39.setText(tv);
                    bool_acc = false;
                } else {
                    String tv = "Please enter time in SI unit";
                    textView39.setText(tv);
                }

                //_____________________________________________________________________________

                if (bool_u) {
                    String tv = "Please enter initial velocity in SI unit";
                    textView40.setText(tv);
                } else if (bool_acc) {
                    String tv = "Please enter acceleration in SI unit";
                    textView40.setText(tv);
                } else {
                    String tv = "Please enter time in SI unit";
                    textView40.setText(tv);
                }

                //________________________________________________________________________________

                boolean ini = textView37.getText().equals("Please enter initial velocity in SI unit") || textView39.getText().equals("Please enter initial velocity in SI unit") || textView40.getText().equals("Please enter initial velocity in SI unit");
                boolean fin = textView37.getText().equals("Please enter final velocity in SI unit") || textView39.getText().equals("Please enter final velocity in SI unit") || textView40.getText().equals("Please enter final velocity in SI unit");
                boolean dis = textView37.getText().equals("Please enter displacement in SI unit") || textView39.getText().equals("Please enter displacement in SI unit") || textView40.getText().equals("Please enter displacement in SI unit");
                boolean tim = textView37.getText().equals("Please enter time in SI unit") || textView39.getText().equals("Please enter time in SI unit") || textView40.getText().equals("Please enter time in SI unit");
                boolean acc = textView37.getText().equals("Please enter acceleration in SI unit") || textView39.getText().equals("Please enter acceleration in SI unit") || textView40.getText().equals("Please enter acceleration in SI unit");

                boolean case1 = dis && fin && ini; //a and t
                boolean case2 = dis && fin && acc; //u and t
                boolean case3 = dis && fin && tim; //u and a
                boolean case4 = dis && ini && acc; //v and t
                boolean case5 = dis && ini && tim; //v and a
                boolean case6 = dis && acc && tim; //u and v
                boolean case7 = fin && ini && acc; //t and s
                boolean case8 = fin && ini && tim; //u and s
                boolean case9 = fin && acc && tim; //a and s
                //v and s

                cal.setOnClickListener(view -> {
                    if (editTextNumber.getText().toString().trim().isEmpty() || editTextNumber2.getText().toString().trim().isEmpty() || editTextNumber3.getText().toString().trim().isEmpty()) {
                        Toast.makeText(KE1.KE1_1.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                    } else {

                        ScrollView SV_KE11 = findViewById(R.id.SV_KE11);


                        if (case1) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double v = evalf(editTextNumber2.getText().toString().trim());
                            double u = evalf(editTextNumber3.getText().toString().trim());

                            double t = 2 * s / (v - u);
                            double a = (v - u) / t;

                            String text = "<font color=#6BBBE1>Time is </font> <font color=#CDDC39>" + rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Acceleration is is </font> <font color=#CDDC39>" + rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case2) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double v = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double u = Math.sqrt(v * v - (2 * a * s));
                            double t = (v - u) / a;


                            String text = "<font color=#6BBBE1>Initial velocity is </font> <font color=#CDDC39>" + rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Time is </font> <font color=#CDDC39>" + rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case3) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double v = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = 2 * s / t - v;
                            double a = (v - u) / t;

                            String text = "<font color=#6BBBE1>Initial velocity is </font> <font color=#CDDC39>" + rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>acceleration is </font> <font color=#CDDC39>" + rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case4) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double v = Math.sqrt(u * u + 2 * a * s);
                            double t = (v - u) / a;

                            String text = "<font color=#6BBBE1>time is </font> <font color=#CDDC39>" + rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Final velocity is </font> <font color=#CDDC39>" + rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case5) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double v = 2 * s / t - u;
                            double a = (v - u) / t;

                            String text = "<font color=#6BBBE1>Acceleration is </font> <font color=#CDDC39>" + rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Final velocity is </font> <font color=#CDDC39>" + rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case6) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = (s - (a * t * t / 2)) / t;
                            double v = u + a * t;

                            String text = "<font color=#6BBBE1>Initial velocity is </font> <font color=#CDDC39>" + rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Final velocity is </font> <font color=#CDDC39>" + rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case7) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double s = (v * v - u * u) / (2 * a);
                            double t = (v - u) / a;

                            String text = "<font color=#6BBBE1>Displacement is </font> <font color=#CDDC39>" + rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Time is </font> <font color=#CDDC39>" + rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case8) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double a = (v - u) / t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "<font color=#6BBBE1>Acceleration is </font> <font color=#CDDC39>" + rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Displacement is </font> <font color=#CDDC39>" + rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case9) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = v - a * t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "<font color=#6BBBE1>Initial velocity is </font> <font color=#CDDC39>" + rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Displacement is </font> <font color=#CDDC39>" + rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else //case10
                        {
                            double u = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double v = u + a * t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "<font color=#6BBBE1>Final velocity is </font> <font color=#CDDC39>" + rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "<font color=#6BBBE1>Displacement is </font> <font color=#CDDC39>" + rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        }

                        SV_KE11.post(() -> SV_KE11.smoothScrollTo(0, KE11_graphButton.getBottom()));


                        //_______________________________________________________________________________________


                    }
                });

            }

            private void ResultAndGraphStarterButton(@NotNull Button KE11_graphButton, @NotNull TextView tv_results, double u, double a, double t, String text) {
                tv_results.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
                tv_results.setVisibility(View.VISIBLE);
                KE11_graphButton.setVisibility(View.VISIBLE);

                KE11_graphStarter(KE11_graphButton, u, a, t);
            }

            private void KE11_graphStarter(@NotNull Button KE11_graph, double u, double a, double t) {
                KE11_graph.setOnClickListener(view1 -> {
                    Intent intent = new Intent(this, KE1.KE1_1.ke11_graph.class);
                    intent.putExtra("u_intent", u);
                    intent.putExtra("a_intent", a);
                    intent.putExtra("t_intent", t);
                    startActivity(intent);
                });
            }

            public static class ke11_graph extends AppCompatActivity {

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.line_chart_universal);

                    LineChart mChart = findViewById(R.id.lineChart);

                    double u = getIntent().getDoubleExtra("u_intent", 0);
                    double a = getIntent().getDoubleExtra("a_intent", 1);
                    double t_final = getIntent().getDoubleExtra("t_intent", 1);

                    ArrayList<Entry> yValues = mChartProperties(mChart);

                    for (double t_ini = 0; Math.abs(t_ini) <= Math.abs(t_final); t_ini = t_ini + (t_final / 5000f)) {
                        double v = (u + a * t_ini);
                        yValues.add(new Entry((float) t_ini, (float) v));
                    }
                    //________________________________________________________________________

                    mChartDataSetter(mChart, this, true, yValues);
                }
            }
        }
    }

    public static class KE2 extends AppCompatActivity {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ke2);

            EditText X, Y, G, B, V, A1, A2;

            Button btnCalc = findViewById(R.id.button15);
            X = findViewById(R.id.editTextPhone);
            Y = findViewById(R.id.editTextPhone2);
            V = findViewById(R.id.editTextPhone3);
            A1 = findViewById(R.id.editTextPhone4);
            A2 = findViewById(R.id.editTextPhone5);
            G = findViewById(R.id.editTextPhone6);
            B = findViewById(R.id.editTextPhone8);
            TextView result = findViewById(R.id.textView49);
            LineChart lc = findViewById(R.id.lineChart2);


            result.setVisibility(View.GONE);


            X.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });

            Y.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });
            V.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });
            A1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });
            A2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });
            G.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });
            B.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    result.setVisibility(View.GONE);
                }
            });


            btnCalc.setOnClickListener(view -> {
                if (X.getText().toString().isEmpty() || Y.getText().toString().isEmpty() || V.getText().toString().isEmpty() || A1.getText().toString().isEmpty() || A2.getText().toString().isEmpty() || G.getText().toString().isEmpty() || B.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    double x = evalf(X.getText().toString()); //gets launching X coordinate
                    double y = evalf(Y.getText().toString()); //gets launching Y coordinate
                    double v = evalf(V.getText().toString()); //gets launching velocity
                    double a1 = Math.toRadians(evalf(A1.getText().toString())); //gets  angle of incline
                    double a2 = Math.toRadians(evalf(A2.getText().toString())); //gets launching angle wrt incline
                    double g = evalf(G.getText().toString()); //gets launching gravity
                    double t = 0;                        //gets launching time
                    double b = evalf(B.getText().toString()); //gets coeff of air resistance

                    double x0 = x;
                    double y0 = y;


                    // Calculate the initial velocity components in tilted coordinate axes
                    double v0x = v * Math.cos(a2);
                    double v0y = v * Math.sin(a2);

                    // Set the time step
                    double dt = 0.00001;

                    // Calculate the motion of the projectile
                    double ax = 0;
                    double ay = 0;

                    ArrayList<Entry> yValues = mChartProperties(lc);

                    while (y >= 0) {

                        //calculate instantaneous velocity
                        double ivmag = Math.sqrt(v0x * v0x + v0y * v0y);

                        // Calculate the acceleration due to air resistance
                        ax = -Math.sin(a1) * g - (b * v0x * ivmag);
                        ay = -Math.cos(a1) * g - (b * v0y * ivmag);

                        // Update the velocity
                        v0x = v0x + ax * dt;
                        v0y = v0y + ay * dt;

                        // Update the position
                        x = x + v0x * dt;
                        y = y + v0y * dt;

                        // Update the time
                        t = t + dt;

                        //instantaneous distance travelled
                        double id = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));

                        //instantaneous angle
                        double ia = Math.atan2(y - y0, x - x0);

                        yValues.add(new Entry((float) ((float) Math.cos(a1) * x), (float) ((float) id * Math.sin(a1 + ia))));
                    }

                    mChartDataSetter(lc, this, true, yValues);

                    //calculate distance travelled
                    double distanceTravelled = Math.sqrt(Math.pow((x - x0), 2) + Math.pow((y - y0), 2));

                    DecimalFormat df = new DecimalFormat("###.####");
                    df.setRoundingMode(RoundingMode.CEILING);

                    //velocities relative to real axes
                    v0x = v0x * Math.cos(a1);
                    v0y = v0y * Math.sin(a1);

                    //positions relative to real axes
                    x = x * Math.cos(a1);
                    y = x * Math.tan(a1);

                    String resultText = "<font color=#6BBBE1> x:  </font> <font color=#CDDC39>" + df.format(x) + "</font><br><br>" +
                            "<font color=#6BBBE1> y: </font> <font color=#CDDC39>" + df.format(y) + "</font><br><br>" +
                            "<font color=#6BBBE1> t:  </font> <font color=#CDDC39>" + df.format(t) + "</font><br><br>" +
                            "<font color=#6BBBE1> Velocity along x when striking the incline/ground:  </font> <font color=#CDDC39>" + df.format(v0x) + "</font><br><br>" +
                            "<font color=#6BBBE1> Velocity along y when striking the incline/ground :  </font> <font color=#CDDC39>" + df.format(v0y) + "</font><br><br>" +
                            "<font color=#6BBBE1> Acceleration along x when striking the incline/ground:  </font> <font color=#CDDC39>" + df.format(ax) + "</font><br><br>" +
                            "<font color=#6BBBE1> Acceleration along y when striking the incline/ground:  </font> <font color=#CDDC39>" + df.format(ay) + "</font><br><br>" +
                            "<font color=#6BBBE1> Distance travelled:  </font> <font color=#CDDC39>" + df.format(distanceTravelled) + "</font> <font color=#6BBBE1> </font><br><br>" +
                            "<font color=#6BBBE1> Time taken before striking incline/ground:  </font> <font color=#CDDC39>" + df.format(t) + "</font>";
                    result.setText(Html.fromHtml(resultText, Html.FROM_HTML_MODE_LEGACY));
                    result.setVisibility(View.VISIBLE);
                    btnCalc.setEnabled(true);


                }
            });

        }
    }
}
