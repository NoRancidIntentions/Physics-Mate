package com.example.physicsmate.activities.physics;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.example.physicsmate.Custom_methods.*;


public class Kinematics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinematics);

        Button KE1, KE_projectile;

        KE1 = findViewById(R.id.button16);
        KE_projectile = findViewById(R.id.button14);

        KE1.setOnClickListener(view -> {
            Intent intent = new Intent(Kinematics.this, KE1.class);
            startActivity(intent);
        });

        KE_projectile.setOnClickListener(view -> {
            Intent intent = new Intent(Kinematics.this, Kinematics.KE_projectile.class);
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
            btn_next.setEnabled(false);
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
                btn_next.setEnabled(false);
                btn_next.setBackgroundResource(R.drawable.btn_disabled);
                btn_clear.setVisibility(View.GONE);

                a[0] = 0;
            });

            Collection<CheckBox> checkBoxes = new ArrayList<>();
            checkBoxes.add(CB_acc);
            checkBoxes.add(CB_u);
            checkBoxes.add(CB_v);
            checkBoxes.add(CB_t);
            checkBoxes.add(CB_s);


            CB_acc.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, checkBoxes));

            CB_t.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, checkBoxes));

            CB_u.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, checkBoxes));

            CB_v.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, checkBoxes));

            CB_s.setOnClickListener(view -> CB_MainMethod(btn_next, btn_clear, a, max_CB_Required, checkBoxes));

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

                            String text = "Time is " + HtmlNumberFormatter(KE1_1.this, rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "br><br>" + "Acceleration is is " + HtmlNumberFormatter(KE1_1.this, rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case2) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double v = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double u = Math.sqrt(v * v - (2 * a * s));
                            double t = (v - u) / a;


                            String text = "Initial velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Time is " + HtmlNumberFormatter(KE1_1.this, rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case3) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double v = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = 2 * s / t - v;
                            double a = (v - u) / t;

                            String text = "Initial velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "acceleration is " + HtmlNumberFormatter(KE1_1.this, rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case4) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double v = Math.sqrt(u * u + 2 * a * s);
                            double t = (v - u) / a;

                            String text = "time is " + HtmlNumberFormatter(KE1_1.this, rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Final velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case5) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double v = 2 * s / t - u;
                            double a = (v - u) / t;

                            String text = "Acceleration is " + HtmlNumberFormatter(KE1_1.this, rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Final velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case6) {
                            double s = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = (s - (a * t * t / 2)) / t;
                            double v = u + a * t;

                            String text = "Initial velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Final velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case7) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double a = evalf(editTextNumber3.getText().toString().trim());

                            double s = (v * v - u * u) / (2 * a);
                            double t = (v - u) / a;

                            String text = "Displacement is " + HtmlNumberFormatter(KE1_1.this, rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Time is " + HtmlNumberFormatter(KE1_1.this, rounded_String(t, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case8) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double u = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double a = (v - u) / t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "Acceleration is " + HtmlNumberFormatter(KE1_1.this, rounded_String(a, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Displacement is " + HtmlNumberFormatter(KE1_1.this, rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else if (case9) {
                            double v = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double u = v - a * t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "Initial velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(u, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Displacement is " + HtmlNumberFormatter(KE1_1.this, rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

                            ResultAndGraphStarterButton(KE11_graphButton, tv_results, u, a, t, text);
                        } else //case10
                        {
                            double u = evalf(editTextNumber.getText().toString().trim());
                            double a = evalf(editTextNumber2.getText().toString().trim());
                            double t = evalf(editTextNumber3.getText().toString().trim());

                            double v = u + a * t;
                            double s = (v * v - u * u) / (2 * a);

                            String text = "Final velocity is " + HtmlNumberFormatter(KE1_1.this, rounded_String(v, editTextNumber, editTextNumber2, editTextNumber3) + "</font><br><br>" + "Displacement is " + HtmlNumberFormatter(KE1_1.this, rounded_String(s, editTextNumber, editTextNumber2, editTextNumber3))) + "</font><br><br>";

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
















    /*public static class KE2_Temp extends AppCompatActivity {

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

                    String resultText = " x:  " + df.format(x) + "</font><br><br>" +
                            " y: " + df.format(y) + "</font><br><br>" +
                            " t:  " + df.format(t) + "</font><br><br>" +
                            " Velocity along x when striking the incline/ground:  " + df.format(v0x) + "</font><br><br>" +
                            " Velocity along y when striking the incline/ground :  " + df.format(v0y) + "</font><br><br>" +
                            " Acceleration along x when striking the incline/ground:  " + df.format(ax) + "</font><br><br>" +
                            " Acceleration along y when striking the incline/ground:  " + df.format(ay) + "</font><br><br>" +
                            " Distance travelled:  " + df.format(distanceTravelled) + "</font>  </font><br><br>" +
                            " Time taken before striking incline/ground:  " + df.format(t) + "</font>";
                    result.setText(Html.fromHtml(resultText, Html.FROM_HTML_MODE_LEGACY));
                    result.setVisibility(View.VISIBLE);
                    btnCalc.setEnabled(true);


                }
            });

        }
    }*/

    public static class KE_projectile extends AppCompatActivity {

        ScrollView sv;
        custom_keyboard1_initializer customKeyboard;

        EditText etXi, etYi, etXp, etYp, etMass, etGravity, etVelocityMagnitude, etInclinedPlaneAngle, etProjectionAngle,
                etDensity, etArea, etDragCoefficient;

        Button btnCalc, btnGetGraph;

        CheckBox cbAirResistance;

        TextView tvResult, tvAirResistanceInfo;


        LineChart lc;

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
            setContentView(R.layout.activity_ke_projectile);

            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_ke_projectile);
            sv = findViewById(R.id.SV_ke_projectile);

            etXi = findViewById(R.id.etXi);
            etYi = findViewById(R.id.etYi);
            etXp = findViewById(R.id.etXp);
            etYp = findViewById(R.id.etYp);
            etMass = findViewById(R.id.etMass);
            etGravity = findViewById(R.id.etGravity);
            etVelocityMagnitude = findViewById(R.id.etVelocityMagnitude);
            etInclinedPlaneAngle = findViewById(R.id.etInclinedPlaneAngle);
            etProjectionAngle = findViewById(R.id.etProjectionAngle);
            etDensity = findViewById(R.id.etDensity);
            etArea = findViewById(R.id.etArea);
            etDragCoefficient = findViewById(R.id.etDragCoefficient);

            cbAirResistance = findViewById(R.id.cbAirResistance);

            TextView tvMass = findViewById(R.id.textView48);
            TextView CSArea = findViewById(R.id.textView49);
            TextView AirDensity = findViewById(R.id.textView50);
            TextView DragCoefficient = findViewById(R.id.textView51);

            btnCalc = findViewById(R.id.btnCalc);
            btnGetGraph = findViewById(R.id.btnGetGraphs);

            lc = findViewById(R.id.lineChart2);

            tvResult = findViewById(R.id.tvResult);
            tvAirResistanceInfo = findViewById(R.id.tvAirResistanceInfo);

            btnCalc.setEnabled(false);

            View[] viewsToDissapear = {lc, tvResult};

            setupEditTextForCustomKeyboard(customKeyboard, sv, etXi, etYi, etXp, etYp, etMass, etGravity,
                    etVelocityMagnitude, etInclinedPlaneAngle, etProjectionAngle, etDensity, etArea, etDragCoefficient);

            tvAirResistanceInfo.setVisibility(View.GONE);
            tvMass.setVisibility(View.GONE);
            CSArea.setVisibility(View.GONE);
            AirDensity.setVisibility(View.GONE);
            DragCoefficient.setVisibility(View.GONE);
            etMass.setVisibility(View.GONE);
            etDensity.setVisibility(View.GONE);
            etArea.setVisibility(View.GONE);
            etDragCoefficient.setVisibility(View.GONE);
            tvResult.setVisibility(View.GONE);
            btnGetGraph.setVisibility(View.GONE);
            lc.setVisibility(View.GONE);

            //add etGravity, etVelocityMagnitude,etInclinedPlaneAngle, etProjectionAngle to a Collection<EditText>
            Collection<EditText> editTexts = Arrays.asList(etGravity, etVelocityMagnitude,
                    etInclinedPlaneAngle, etProjectionAngle);

            setupEditTextChangeListener(viewsToDissapear, btnCalc, customKeyboard,editTexts);

            cbAirResistance.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (isChecked) {
                    tvAirResistanceInfo.setVisibility(View.VISIBLE);
                    tvMass.setVisibility(View.VISIBLE);
                    CSArea.setVisibility(View.VISIBLE);
                    AirDensity.setVisibility(View.VISIBLE);
                    DragCoefficient.setVisibility(View.VISIBLE);
                    etMass.setVisibility(View.VISIBLE);
                    etDensity.setVisibility(View.VISIBLE);
                    etArea.setVisibility(View.VISIBLE);
                    etDragCoefficient.setVisibility(View.VISIBLE);

                    Collection<EditText> editTexts2 = Arrays.asList(etXi, etYi, etYp, etXp, etMass, etGravity, etVelocityMagnitude,
                            etInclinedPlaneAngle, etProjectionAngle, etDensity, etArea, etDragCoefficient);

                    setupEditTextChangeListener(viewsToDissapear, btnCalc, customKeyboard, editTexts2);
                } else {
                    tvAirResistanceInfo.setVisibility(View.GONE);
                    tvMass.setVisibility(View.GONE);
                    CSArea.setVisibility(View.GONE);
                    AirDensity.setVisibility(View.GONE);
                    DragCoefficient.setVisibility(View.GONE);
                    etMass.setVisibility(View.GONE);
                    etDensity.setVisibility(View.GONE);
                    etArea.setVisibility(View.GONE);

                    Collection<EditText> editTexts3 = Arrays.asList( etGravity, etVelocityMagnitude,
                            etInclinedPlaneAngle, etProjectionAngle);

                    setupEditTextChangeListener(viewsToDissapear, btnCalc, customKeyboard,editTexts3);
                }
            });

            btnCalc.setOnClickListener(view -> {
                hideKeyboardOnClick(btnCalc, this);
                if (cbAirResistance.isChecked()) {
                    calculateProjectileWithAirResistance();
                } else {
                    calculateProjectile();
                }
            });

        }

        public void calculateProjectileWithAirResistance() {

        }

        public void calculateProjectile() {
            double u = evalf(etVelocityMagnitude.getText().toString());

            double alphaPlusBeta, beta;

            beta = evalf(etInclinedPlaneAngle.getText().toString());
            alphaPlusBeta = (evalf(etProjectionAngle.getText().toString()));

            double alpha = alphaPlusBeta - beta;

            double g = evalf(etGravity.getText().toString()); //
            double Xp = evalf(etXp.getText().toString());
            double Yp = evalf(etYp.getText().toString());
            double Xi = evalf(etXi.getText().toString());
            double Yi = evalf(etYi.getText().toString());


            System.out.println(u + " " + beta + " " + alpha + " " + g + " " + Xp + " " + Yp);

            if (Xi == Xp && Yi == Yp) {
                //range = 2*u^2*sin(alpha)*cos(alpha+beta)/(g*cos(beta)^2)
                double range = 2 * Math.pow(u, 2) * Math.sin(alpha) * Math.cos(alpha + beta) / (g * Math.pow(Math.cos(beta), 2));

                //time = 2*u*sin(alpha)/(g*cos(beta))
                double time = 2 * u * Math.sin(alpha) / (g * Math.cos(beta));

                //max height = (u*sin(alpha))^2/(2*g*cos(beta))
                double maxHeight = Math.pow(u * Math.sin(alpha), 2) / (2 * g * Math.cos(beta));

                //angle of projection for maximum range = pi/4 - beta/2
                double maxAngle = Math.PI / 4 - beta / 2;

                //show results
                tvResult.setVisibility(View.VISIBLE);

                //use this string as a template:                         String strRes = "<font color = #6BBBE1> Gravitational acceleration = </font> <font color = #CDDC39>" + acc + " m/s </font>";

                String strRes = "<font color = #6BBBE1> Range = </font> <font color = #CDDC39>" + range + " m </font> <br>" +
                        "<br><font color = #6BBBE1> Time = </font> <font color = #CDDC39>" + time + " s </font> <br>" +
                        "<br><font color = #6BBBE1> Maximum height = </font> <font color = #CDDC39>" + maxHeight + " m </font> <br>" +
                        "<br><font color = #6BBBE1> Maximum angle of projection = </font> <font color = #CDDC39>" + Math.toDegrees(maxAngle) + " degrees </font>";

                tvResult.setText(Html.fromHtml(strRes));
            } else {
                //solve these two equations using symja:
                //y - Yp = x*tan(alpha) - g*x*x/(2*u*u*cos(alpha)*cos(alpha))
                //y - Yi = tan(beta)*(x - Xi)

                String trajectoryFormula = "y - Yp = x*tan(alphaPlusBeta) - g*x*x/(2*u*u*cos(alphaPlusBeta)*cos(alphaPlusBeta))";
                String inclinationFormula = "y - Yi = tan(beta)*(x - Xi)";
                String constraint1 = "x>=0";

                trajectoryFormula = trajectoryFormula.replaceAll("alphaPlusBeta", String.valueOf(alphaPlusBeta));
                trajectoryFormula = trajectoryFormula.replaceAll("beta", String.valueOf(beta));
                trajectoryFormula = trajectoryFormula.replaceAll("g", String.valueOf(g));
                trajectoryFormula = trajectoryFormula.replaceAll("u", String.valueOf(u));
                trajectoryFormula = trajectoryFormula.replaceAll("Xi", String.valueOf(Xi));
                trajectoryFormula = trajectoryFormula.replaceAll("Yi", String.valueOf(Yi));
                trajectoryFormula = trajectoryFormula.replaceAll("Xp", String.valueOf(Xp));
                trajectoryFormula = trajectoryFormula.replaceAll("Yp", String.valueOf(Yp));

                inclinationFormula = inclinationFormula.replaceAll("alphaPlusBeta", String.valueOf(alphaPlusBeta));
                inclinationFormula = inclinationFormula.replaceAll("beta", String.valueOf(beta));
                inclinationFormula = inclinationFormula.replaceAll("g", String.valueOf(g));
                inclinationFormula = inclinationFormula.replaceAll("u", String.valueOf(u));
                inclinationFormula = inclinationFormula.replaceAll("Xi", String.valueOf(Xi));
                inclinationFormula = inclinationFormula.replaceAll("Yi", String.valueOf(Yi));
                inclinationFormula = inclinationFormula.replaceAll("Xp", String.valueOf(Xp));
                inclinationFormula = inclinationFormula.replaceAll("Yp", String.valueOf(Yp));

                Collection<String> eqns = new ArrayList<>();
                eqns.add(trajectoryFormula);
                eqns.add(inclinationFormula);
                //eqns.add(constraint1);

                System.out.println("eqns: " + eqns);

                String intersection = solveEquationNumeric(eqns, "x, y");
                System.out.println("intersection: " + intersection);

                ArrayList<String> xy = IntersectionExtractor(intersection);
                System.out.println("x: " + xy.get(0));
                System.out.println("y: " + xy.get(1));

                double time = (Double.parseDouble(xy.get(0)) - Xp) / (u * Math.cos(alphaPlusBeta));
                double range = Double.parseDouble(xy.get(0)) - Xp;
                double tempTimeWithoutInclinedPlane = 2 * u * Math.sin(alphaPlusBeta) / g;
                double timeforMaxHeight = tempTimeWithoutInclinedPlane / 2;

                if (time > timeforMaxHeight) {
                    double timeWhenVerticalVelocityIsZero = u * Math.sin(alphaPlusBeta) / g;
                    double maxHeight = u * Math.sin(alphaPlusBeta) * timeWhenVerticalVelocityIsZero - 0.5 * g * timeWhenVerticalVelocityIsZero * timeWhenVerticalVelocityIsZero;
                    System.out.println("maxHeight1: " + maxHeight);
                    double maxHeight2 = u * u * Math.sin(alphaPlusBeta) * Math.sin(alphaPlusBeta) / (2 * g);
                    System.out.println("maxHeight2: " + maxHeight2);
                } else {
                    double maxHeight = Double.parseDouble(xy.get(1));
                    System.out.println("maxHeight: " + maxHeight);
                }

            }

        }
    }
}