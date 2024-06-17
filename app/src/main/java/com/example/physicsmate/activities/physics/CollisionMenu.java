package com.example.physicsmate.activities.physics;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.airbnb.paris.Paris;
import com.example.physicsmate.Custom_methods;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.physicsmate.Custom_methods.CB_MainMethod;
import static com.example.physicsmate.Custom_methods.et_tv_dynamic_adder;

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
            Button btnNext = findViewById(R.id.btnNext);
            Button btnClear = findViewById(R.id.btnClear);

            btnClear.setVisibility(View.GONE);

            btnNext.setClickable(false);
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_disabled);

            final int[] a = {0};
            int max_CB_Required = 6;

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

                Paris.styleBuilder(btnCalc).add(R.style.custom_button_disabled).apply();
                btnCalc.setClickable(false);

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

                // Create a list of all checkboxes and their corresponding labels
                boolean[] checkboxStates = {chkM1, chkM2, chkV1, chkV2, chkA1, chkA2, chkB1, chkB2, chkU1, chkU2};
                String[] labels = {"M1", "M2", "V1", "V2", "A1", "A2", "B1", "B2", "U1", "U2"};

                // Add TextViews dynamically based on the boolean values
                for (int i = 0; i < checkboxStates.length; i++) {
                    if (checkboxStates[i]) {
                        TextView textView = new TextView(this);
                        EditText editText = new EditText(this);

                        textView.setText("Please enter the value of " + labels[i] + "in SI units");
                        textView.setId(i*10); //0 in ones place correspond to textview,
                        // and the digits left of ones place is the row number of the textview

                        editText.setId(i*10+1); //1 in ones place correspond to edittext

                        Paris.styleBuilder(textView).add(R.style.custom_textView).apply();
                        linearLayout.addView(textView);
                    }
                }

                //get the values entered by the user
                String[] values = new String[checkboxStates.length];
                for (int i = 0; i < checkboxStates.length; i++) {
                    if (checkboxStates[i]) {
                        EditText editText = findViewById(i*10+1);
                        values[i] = editText.getText().toString();
                    }
                }


            }
        }
    }
}