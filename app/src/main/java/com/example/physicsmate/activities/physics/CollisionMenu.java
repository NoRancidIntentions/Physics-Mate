package com.example.physicsmate.activities.physics;

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
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.physicsmate.Custom_methods.*;

public class CollisionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collision_menu);

        Button btnBounce = findViewById(R.id.btnDrop);
        Button btnLinearCollision = findViewById(R.id.btnLinearCollision);
        Button btnObliqueCollision = findViewById(R.id.btnObliqueCollision);

        btnBounce.setOnClickListener(v -> {
            //startActivity(PhysicsActivity.getIntent(this, "Bounce"));
        });

        btnLinearCollision.setOnClickListener(v -> {
            //startActivity(PhysicsActivity.getIntent(this, "LinearCollision"));
        });

        btnObliqueCollision.setOnClickListener(v -> {
            Intent intent = new Intent(this, ObliqueCollisionMenu.class);
            startActivity(intent);
        });

    }

    public static class ObliqueCollisionMenu extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_obliqe_collision_menu);

            CheckBox chkM1 = findViewById(R.id.chkM1);
            CheckBox chkM2 = findViewById(R.id.chkM2);
            CheckBox chkV1 = findViewById(R.id.chkV1);
            CheckBox chkV2 = findViewById(R.id.chkV2);
            CheckBox chkA1 = findViewById(R.id.chkA1);
            CheckBox chkA2 = findViewById(R.id.chkA2);
            CheckBox chkB1 = findViewById(R.id.chkB1);
            CheckBox chkB2 = findViewById(R.id.chkB2);
            CheckBox chkU1 = findViewById(R.id.chkU1);
            CheckBox chkU2 = findViewById(R.id.chkU2);
            CheckBox chkPhi = findViewById(R.id.chkPhi);
            Button btnNext = findViewById(R.id.btnNext);
            Button btnClear = findViewById(R.id.btnClear);

            btnClear.setVisibility(View.GONE);

            btnNext.setClickable(false);
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_disabled);

            final int[] a = {0};
            int max_CB_Required = 7;

            Collection<CheckBox> checkBoxes = new ArrayList<>();
            checkBoxes.add(chkM1);
            checkBoxes.add(chkM2);
            checkBoxes.add(chkV1);
            checkBoxes.add(chkV2);
            checkBoxes.add(chkA1);
            checkBoxes.add(chkA2);
            checkBoxes.add(chkB1);
            checkBoxes.add(chkB2);
            checkBoxes.add(chkU1);
            checkBoxes.add(chkU2);
            checkBoxes.add(chkPhi);

            btnClear.setOnClickListener(view -> {

                for (CheckBox checkBox : checkBoxes) {
                    checkBox.setChecked(false);
                    checkBox.setClickable(false);
                }

                btnNext.setClickable(false);
                btnNext.setEnabled(false);
                btnNext.setBackgroundResource(R.drawable.btn_disabled);
                btnClear.setVisibility(View.GONE);

                a[0] = 0;
            });

            for (CheckBox checkBox : checkBoxes) {
                checkBox.setOnClickListener(view -> CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes));
            }

            btnNext.setOnClickListener(view -> {

                //send the boolean values of the checkboxes to the momentumConservation activity
                Intent intent = new Intent(this, momentumConservation.class);
                intent.putExtra("chkM1", chkM1.isChecked());
                intent.putExtra("chkM2", chkM2.isChecked());
                intent.putExtra("chkV1", chkV1.isChecked());
                intent.putExtra("chkV2", chkV2.isChecked());
                intent.putExtra("chkA1", chkA1.isChecked());
                intent.putExtra("chkA2", chkA2.isChecked());
                intent.putExtra("chkB1", chkB1.isChecked());
                intent.putExtra("chkB2", chkB2.isChecked());
                intent.putExtra("chkU1", chkU1.isChecked());
                intent.putExtra("chkU2", chkU2.isChecked());
                intent.putExtra("chkPhi", chkPhi.isChecked());

                startActivity(intent);

            });

            CB_MainMethod(btnNext, btnClear, a, max_CB_Required, checkBoxes);

        }

        public static class momentumConservation extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                LinearLayout linearLayout = findViewById(R.id.linearLayout_dynamic_fields);
                TextView tvRes = findViewById(R.id.tvResults_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCalc = findViewById(R.id.btnCalc_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                Button btnCopy = findViewById(R.id.btnCopy_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                ImageView img = findViewById(R.id.imageView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
                custom_keyboard1_initializer customKeyboard = findViewById(R.id.custom_keyboard1_initializer_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

                btnCopy.setVisibility(View.GONE);
                tvRes.setVisibility(View.GONE);

                img.setVisibility(View.GONE);

                Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
                btnCalc.setClickable(true);

                //retrieve the intents
                Intent intent = getIntent();
                boolean chkM1 = intent.getBooleanExtra("chkM1", false);
                boolean chkM2 = intent.getBooleanExtra("chkM2", false);
                boolean chkV1 = intent.getBooleanExtra("chkV1", false);
                boolean chkV2 = intent.getBooleanExtra("chkV2", false);
                boolean chkA1 = intent.getBooleanExtra("chkA1", false);
                boolean chkA2 = intent.getBooleanExtra("chkA2", false);
                boolean chkB1 = intent.getBooleanExtra("chkB1", false);
                boolean chkB2 = intent.getBooleanExtra("chkB2", false);
                boolean chkU1 = intent.getBooleanExtra("chkU1", false);
                boolean chkU2 = intent.getBooleanExtra("chkU2", false);
                boolean chkPhi = intent.getBooleanExtra("chkPhi", false);

                // Create a list of all checkboxes and their corresponding labels
                boolean[] checkboxStates = {chkM1, chkM2, chkV1, chkV2, chkA1, chkA2, chkB1, chkB2, chkU1, chkU2, chkPhi};
                String[] labels = {"M1", "M2", "V1", "V2", "A1", "A2", "B1", "B2", "U1", "U2", "Phi"};
                //List<EditText> editTexts = Collections.emptyList();

                // Add TextViews dynamically based on the boolean values
                for (int i = 0; i < checkboxStates.length; i++) {
                    if (checkboxStates[i]) {
                        TextView textView = new TextView(this);
                        EditText editText = new EditText(this);

                        Paris.styleBuilder(textView).add(R.style.custom_textView).apply();
                        linearLayout.addView(textView);
                        linearLayout.addView(editText);

                        textView.setText("Please enter the value of " + labels[i] + "in SI units");
                        textView.setId(i * 10); //0 in ones place correspond to textview,
                        // and the digits left of ones place is the row number of the textview

                        editText.setId(i * 10 + 1); //1 in ones place correspond to edittext
                        editText.setTag(labels[i]);
                        //add this edittext to the list of edittexts
                        //editTexts.add(editText);
                    }

                    //View[] viewsToDissapear = {tvRes, btnCopy};
                    //setupEditTextChangeListener(viewsToDissapear, btnCalc, customKeyboard, editTexts);
                }

                Toast.makeText(this, "For u1, u2, v1, v2 and phi, enter the magnitude (absolute value)", Toast.LENGTH_LONG).show();

                btnCalc.setOnClickListener(v -> {
                    //get the values entered by the user and assign them to the respective variables
                    // Loop through all child views of the LinearLayout

                    double M1 = Double.NaN, M2 = Double.NaN, V1 = Double.NaN, V2 = Double.NaN, A1 = Double.NaN,
                            A2 = Double.NaN, B1 = Double.NaN, B2 = Double.NaN, U1 = Double.NaN, U2 = Double.NaN, Phi = Double.NaN;

                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        View child = linearLayout.getChildAt(i);
                        if (child instanceof EditText) {
                            EditText editText = (EditText) child;
                            String tag = (String) editText.getTag();
                            try {
                                double value = evalf(editText.getText().toString());
                                switch (tag) {
                                    case "M1":
                                        M1 = value;
                                        break;
                                    case "M2":
                                        M2 = value;
                                        break;
                                    case "V1":
                                        V1 = value;
                                        break;
                                    case "V2":
                                        V2 = value;
                                        break;
                                    case "A1":
                                        A1 = value;
                                        break;
                                    case "A2":
                                        A2 = value;
                                        break;
                                    case "B1":
                                        B1 = value;
                                        break;
                                    case "B2":
                                        B2 = value;
                                        break;
                                    case "U1":
                                        U1 = value;
                                        break;
                                    case "U2":
                                        U2 = value;
                                        break;
                                    case "Phi":
                                        Phi = value;
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(this, "Please enter valid numbers for " + tag, Toast.LENGTH_SHORT).show();
                            }

                            //the equations:
                            //phi = contact angle = magnitude of A1-A2

                            // Final x velocity component of the first ball
                            //double v1x_prime = (v1 * Math.cos(theta1 - phi) * (m1 - m2) + 2 * m2 * v2 * Math.cos(theta2 - phi)) / (m1 + m2);

                            // Final y velocity component of the first ball
                            //double v1y_prime = (v1 * Math.cos(theta1 - phi) * (m1 - m2) + 2 * m2 * v2 * Math.cos(theta2 - phi)) / (m1 + m2) * Math.sin(phi) + v1 * Math.sin(theta1 - phi) * Math.sin(phi + Math.PI / 2);

                            // Final x velocity component of the second ball
                            //double v2x_prime = (v2 * Math.cos(theta2 - phi) * (m2 - m1) + 2 * m1 * v1 * Math.cos(theta1 - phi)) / (m1 + m2);

                            // Final y velocity component of the second ball
                            //double v2y_prime = (v2 * Math.cos(theta2 - phi) * (m2 - m1) + 2 * m1 * v1 * Math.cos(theta1 - phi)) / (m1 + m2) * Math.sin(phi) + v2 * Math.sin(theta2 - phi) * Math.sin(phi + Math.PI / 2);

                            //tan(B1)=v1y_prime/v1x_prime
                            //tan(B2)=v2y_prime/v2x_prime

                            String eqn1 = "v1x = (u1 * cos(A1 - phi) * (m1 - m2) + 2 * m2 * u2 * cos(A2 - phi)) / (m1 + m2) * cos(phi) + u2 * sin(A1 - phi) * sin(phi + pi/2)";
                            String eqn2 = "v1y = (u1 * cos(A1 - phi) * (m1 - m2) + 2 * m2 * u2 * cos(A2 - phi)) / (m1 + m2) * sin(phi) + u1 * sin(A1 - phi) * sin(phi + pi/2)";
                            String eqn3 = "v2x = (u2 * cos(A2 - phi) * (m2 - m1) + 2 * m1 * u1 * cos(A1 - phi)) / (m1 + m2) * cos(phi) + u2 * sin(A2 - phi) * sin(phi + pi/2)";
                            String eqn4 = "v2y = (u2 * cos(A2 - phi) * (m2 - m1) + 2 * m1 * u1 * cos(A1 - phi)) / (m1 + m2) * sin(phi) + u2 * sin(A2 - phi) * sin(phi + pi/2)";
                            String eqn5 = "tan(B1) = v1y/v1x";
                            String eqn6 = "tan(B2) = v2y/v2x";
                            String eqn7 = "v1 = sqrt(v1x^2 + v1y^2)";
                            String eqn8 = "v2 = sqrt(v2x^2 + v2y^2)";

                            //replace the variables in the equations with the values entered by the user
                            if (!Double.isNaN(M1)) {
                                eqn1 = eqn1.replace("m1", String.valueOf(M1));
                                eqn2 = eqn2.replace("m1", String.valueOf(M1));
                                eqn3 = eqn3.replace("m1", String.valueOf(M1));
                                eqn4 = eqn4.replace("m1", String.valueOf(M1));
                                eqn5 = eqn5.replace("m1", String.valueOf(M1));
                                eqn6 = eqn6.replace("m1", String.valueOf(M1));
                            }

                            if (!Double.isNaN(M2)) {
                                eqn1 = eqn1.replace("m2", String.valueOf(M2));
                                eqn2 = eqn2.replace("m2", String.valueOf(M2));
                                eqn3 = eqn3.replace("m2", String.valueOf(M2));
                                eqn4 = eqn4.replace("m2", String.valueOf(M2));
                                eqn5 = eqn5.replace("m2", String.valueOf(M2));
                                eqn6 = eqn6.replace("m2", String.valueOf(M2));
                            }

                            if (!Double.isNaN(V1)) {
                                eqn1 = eqn1.replace("v1", String.valueOf(V1));
                                eqn2 = eqn2.replace("v1", String.valueOf(V1));
                                eqn3 = eqn3.replace("v1", String.valueOf(V1));
                                eqn4 = eqn4.replace("v1", String.valueOf(V1));
                                eqn5 = eqn5.replace("v1", String.valueOf(V1));
                                eqn6 = eqn6.replace("v1", String.valueOf(V1));
                            }

                            if (!Double.isNaN(V2)) {
                                eqn1 = eqn1.replace("v2", String.valueOf(V2));
                                eqn2 = eqn2.replace("v2", String.valueOf(V2));
                                eqn3 = eqn3.replace("v2", String.valueOf(V2));
                                eqn4 = eqn4.replace("v2", String.valueOf(V2));
                                eqn5 = eqn5.replace("v2", String.valueOf(V2));
                                eqn6 = eqn6.replace("v2", String.valueOf(V2));
                            }

                            if (!Double.isNaN(A1)) {
                                eqn1 = eqn1.replace("A1", String.valueOf(A1));
                                eqn2 = eqn2.replace("A1", String.valueOf(A1));
                                eqn3 = eqn3.replace("A1", String.valueOf(A1));
                                eqn4 = eqn4.replace("A1", String.valueOf(A1));
                                eqn5 = eqn5.replace("A1", String.valueOf(A1));
                                eqn6 = eqn6.replace("A1", String.valueOf(A1));
                            }

                            if (!Double.isNaN(A2)) {
                                eqn1 = eqn1.replace("A2", String.valueOf(A2));
                                eqn2 = eqn2.replace("A2", String.valueOf(A2));
                                eqn3 = eqn3.replace("A2", String.valueOf(A2));
                                eqn4 = eqn4.replace("A2", String.valueOf(A2));
                                eqn5 = eqn5.replace("A2", String.valueOf(A2));
                                eqn6 = eqn6.replace("A2", String.valueOf(A2));
                            }

                            if (!Double.isNaN(B1)) {
                                eqn1 = eqn1.replace("B1", String.valueOf(B1));
                                eqn2 = eqn2.replace("B1", String.valueOf(B1));
                                eqn3 = eqn3.replace("B1", String.valueOf(B1));
                                eqn4 = eqn4.replace("B1", String.valueOf(B1));
                                eqn5 = eqn5.replace("B1", String.valueOf(B1));
                                eqn6 = eqn6.replace("B1", String.valueOf(B1));
                            }

                            if (!Double.isNaN(B2)) {
                                eqn1 = eqn1.replace("B2", String.valueOf(B2));
                                eqn2 = eqn2.replace("B2", String.valueOf(B2));
                                eqn3 = eqn3.replace("B2", String.valueOf(B2));
                                eqn4 = eqn4.replace("B2", String.valueOf(B2));
                                eqn5 = eqn5.replace("B2", String.valueOf(B2));
                                eqn6 = eqn6.replace("B2", String.valueOf(B2));
                            }

                            if (!Double.isNaN(U1)) {
                                eqn1 = eqn1.replace("u1", String.valueOf(U1));
                                eqn2 = eqn2.replace("u1", String.valueOf(U1));
                                eqn3 = eqn3.replace("u1", String.valueOf(U1));
                                eqn4 = eqn4.replace("u1", String.valueOf(U1));
                                eqn5 = eqn5.replace("u1", String.valueOf(U1));
                                eqn6 = eqn6.replace("u1", String.valueOf(U1));
                            }

                            if (!Double.isNaN(U2)) {
                                eqn1 = eqn1.replace("u2", String.valueOf(U2));
                                eqn2 = eqn2.replace("u2", String.valueOf(U2));
                                eqn3 = eqn3.replace("u2", String.valueOf(U2));
                                eqn4 = eqn4.replace("u2", String.valueOf(U2));
                                eqn5 = eqn5.replace("u2", String.valueOf(U2));
                                eqn6 = eqn6.replace("u2", String.valueOf(U2));
                            }

                            if (!Double.isNaN(Phi)) {
                                eqn1 = eqn1.replace("phi", String.valueOf(Phi));
                                eqn2 = eqn2.replace("phi", String.valueOf(Phi));
                                eqn3 = eqn3.replace("phi", String.valueOf(Phi));
                                eqn4 = eqn4.replace("phi", String.valueOf(Phi));
                                eqn5 = eqn5.replace("phi", String.valueOf(Phi));
                                eqn6 = eqn6.replace("phi", String.valueOf(Phi));
                            }

                            //use symja to solve the equations for 4 unknowns when the user enters 6 values
                            Collection<String> equations = new ArrayList<>();
                            equations.add(eqn1);
                            equations.add(eqn2);
                            equations.add(eqn3);
                            equations.add(eqn4);
                            equations.add(eqn5);
                            equations.add(eqn6);
                            equations.add(eqn7);
                            equations.add(eqn8);

                            AtomicReference<String> resultParsed = new AtomicReference<>("");

                            //solve the equations in a separate thread
                            Thread thread = new Thread(() -> {
                                String result = solveEquationNumeric(equations, "m1,m2,v1x,v1y,v2x,v2y,v1,v2,a1,a2,b1,b2,u1,u2");
                                resultParsed.set(parseStringAfterSolvingEqn(momentumConservation.this, result));
                            });
                            thread.start();

                            //after the thread is done, display the result
                            try {
                                thread.join();
                            } catch (InterruptedException ignored) {
                            }

                            resultParsed.set(extractBetweenOR(resultParsed.get()));

                            Double b1 = extractValue(resultParsed.get(), "b1");
                            Double b2 = extractValue(resultParsed.get(), "b2");
                            Double v1 = extractValue(resultParsed.get(), "v1");
                            Double v1x = extractValue(resultParsed.get(), "v1x");
                            Double v1y = extractValue(resultParsed.get(), "v1y");
                            Double v2 = extractValue(resultParsed.get(), "v2");
                            Double v2x = extractValue(resultParsed.get(), "v2x");
                            Double v2y = extractValue(resultParsed.get(), "v2y");

                            if (v1x != null && v1x < 0) {
                                resultParsed.set(replaceBValue(resultParsed.get(), "b1", Math.PI - b1));
                            }

                            if (v2x != null && v2x < 0) {
                                resultParsed.set(replaceBValue(resultParsed.get(), "b2", Math.PI - b2));
                            }

                            System.out.println(resultParsed.get());

                            resultParsed.set(resultParsed.get().replace("-", ""));


                            System.out.println(resultParsed.get());

                            tvRes.setText(Html.fromHtml(resultParsed.get(), Html.FROM_HTML_MODE_LEGACY));
                            tvRes.setVisibility(View.VISIBLE);


                        }
                    }

                });

            }
        }
    }

    public static String extractBetweenOR(String input) {
        if (input == null) {
            return "";  // Return an empty string if the input is null
        }

        // Define a pattern to match the segments between "OR"
        Pattern pattern = Pattern.compile("(?<=OR)(.*?)(?=OR)");
        Matcher matcher = pattern.matcher(input);

        // Retry mechanism
        int retryCount = 5;
        int retryIntervalMs = 100;  // 100 milliseconds

        for (int i = 0; i < retryCount; i++) {
            if (matcher.find()) {
                // Return the first successful match
                return matcher.group().trim();
            } else {
                // Wait for the specified interval before retrying
                try {
                    Thread.sleep(retryIntervalMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // If no match is found after retries, return an empty string or a default value
        return "";
    }


    private static String replaceBValue(String input, String variable, double newValue) {
        return input.replaceAll(variable + "= </font><font color='#CDDC39'>[\\d.]+</font>",
                variable + "= </font><font color='#CDDC39'>" + newValue + "</font>");
    }
}