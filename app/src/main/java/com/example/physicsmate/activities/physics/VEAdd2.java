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

import java.util.Arrays;

import static com.example.physicsmate.Custom_methods.*;

public class VEAdd2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veadd2);

        Button btnPolarCosines = findViewById(R.id.btnPolarCosines);
        Button btnPolar = findViewById(R.id.btnPolar);
        Button btnCartesian = findViewById(R.id.btnCartesian);
        EditText etNum = findViewById(R.id.editText_activity_veadd2);

        btnCartesian.setOnClickListener(view -> {
            int num = Integer.parseInt(etNum.getText().toString().trim());
            Intent intent = new Intent(VEAdd2.this, VEAdd2Cart.class);
            intent.putExtra("num", num);
            startActivity(intent);
        });

        btnPolar.setOnClickListener(view -> {
            int num = Integer.parseInt(etNum.getText().toString().trim());
            Intent intent = new Intent(VEAdd2.this, VEAdd2Polar.class);
            intent.putExtra("num", num);
            startActivity(intent);
        });

        btnPolarCosines.setOnClickListener(view -> {
            int num = Integer.parseInt(etNum.getText().toString().trim());
            Intent intent = new Intent(VEAdd2.this, VEAdd2PolarCosines.class);
            intent.putExtra("num", num);
            startActivity(intent);
        });

    }

    public static class VEAdd2Cart extends AppCompatActivity {

        ScrollView sv;
        custom_keyboard1_initializer customKeyboard;

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

        public void copyToClipboard(CharSequence string) {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", string);
            clipboard.setPrimaryClip(clip);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            LinearLayout linearLayout = findViewById(R.id.linearLayout_dynamic_fields);
            TextView tvRes = findViewById(R.id.tvResults_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCalc = findViewById(R.id.btnCalc_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCopy = findViewById(R.id.btnCopy_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            ImageView img = findViewById(R.id.imageView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            //MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            //mv.setVisibility(View.GONE);
            img.setVisibility(View.GONE);

            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
            btnCalc.setClickable(true);

            Intent intent = getIntent();
            int num = intent.getIntExtra("num", 0);

            EditText[] editTexts = new EditText[num];

            //add edittexts based on the number of vectors
            for (int i = 0; i < num; i++) {
                EditText et = new EditText(this);
                Paris.styleBuilder(et).add(R.style.custom_edittext).apply();
                linearLayout.addView(et);
                editTexts[i] = et;

                setupEditTextForCustomKeyboard(customKeyboard, sv, editTexts[i]);
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            Paris.styleBuilder(tv).add(R.style.custom_textView).apply();
            tv.setText("Enter the vectors in cartesian coordinates");
            linearLayout.addView(tv, 0);

            TextView tvHelp = new TextView(this);
            Paris.styleBuilder(tvHelp).add(R.style.custom_textViewSmallNotice).apply();
            tvHelp.setText("Use the format (x,y,z,...)");
            linearLayout.addView(tvHelp, 1);

            btnCalc.setOnClickListener(v -> {
                //add the vectors
                String[] vectors = new String[editTexts.length];
                for (int i = 0; i < editTexts.length; i++) {
                    vectors[i] = editTexts[i].getText().toString();
                }
                //calculate the result
                String result = addVectors(vectors);
                tvRes.setText(Html.fromHtml(HtmlNumberFormatter(result), Html.FROM_HTML_MODE_LEGACY));

                tvRes.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);
            });

            btnCopy.setOnClickListener(v -> {
                copyToClipboard(tvRes.getText().toString());
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            });
        }

    }

    public static class VEAdd2Polar extends AppCompatActivity {

        ScrollView sv;
        custom_keyboard1_initializer customKeyboard;

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

        public void copyToClipboard(CharSequence string) {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", string);
            clipboard.setPrimaryClip(clip);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            LinearLayout linearLayout = findViewById(R.id.linearLayout_dynamic_fields);
            TextView tvRes = findViewById(R.id.tvResults_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCalc = findViewById(R.id.btnCalc_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCopy = findViewById(R.id.btnCopy_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            ImageView img = findViewById(R.id.imageView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            //MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            //mv.setVisibility(View.GONE);
            img.setVisibility(View.GONE);

            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
            btnCalc.setClickable(true);

            Intent intent = getIntent();
            int num = intent.getIntExtra("num", 0);

            EditText[] editTexts = new EditText[num];

            //add edittexts based on the number of vectors
            for (int i = 0; i < num; i++) {
                EditText et = new EditText(this);
                Paris.styleBuilder(et).add(R.style.custom_edittext).apply();
                linearLayout.addView(et);
                editTexts[i] = et;

                setupEditTextForCustomKeyboard(customKeyboard, sv, editTexts[i]);
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            Paris.styleBuilder(tv).add(R.style.custom_textView).apply();
            tv.setText("Enter the vectors in polar coordinates");
            linearLayout.addView(tv, 0);

            TextView tvHelp = new TextView(this);
            Paris.styleBuilder(tvHelp).add(R.style.custom_textViewSmallNotice).apply();
            tvHelp.setText("Use the format (r, theta1, theta2, ..., theta(n-1)) [for n-dimensional vectors], where theta is in radians. \n\n the theta1 angle is measured from the vector to x1 axis. theta2, theta3, ... theta(n-1) are measured from the projection of vector to x2-x3, x4-x5, ... x(n-1)-xn planes from the x2, x3, ... x(n-1) axes.");
            linearLayout.addView(tvHelp, 1);

            btnCalc.setOnClickListener(v -> {
                //add the vectors
                String[] vectors = new String[editTexts.length];
                for (int i = 0; i < editTexts.length; i++) {
                    vectors[i] = editTexts[i].getText().toString();
                }
                //calculate the result
                String result = addPolarVectors(vectors);
                tvRes.setText(Html.fromHtml(HtmlNumberFormatter(result), Html.FROM_HTML_MODE_LEGACY));

                tvRes.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);
            });

            btnCopy.setOnClickListener(v -> {
                copyToClipboard(tvRes.getText().toString());
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            });
        }

    }

    public static class VEAdd2PolarCosines extends AppCompatActivity {

        ScrollView sv;
        custom_keyboard1_initializer customKeyboard;

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

        public void copyToClipboard(CharSequence string) {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", string);
            clipboard.setPrimaryClip(clip);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            LinearLayout linearLayout = findViewById(R.id.linearLayout_dynamic_fields);
            TextView tvRes = findViewById(R.id.tvResults_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCalc = findViewById(R.id.btnCalc_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            Button btnCopy = findViewById(R.id.btnCopy_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            ImageView img = findViewById(R.id.imageView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            customKeyboard = findViewById(R.id.custom_keyboard1_initializer_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            //MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
            sv = findViewById(R.id.SV_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            //mv.setVisibility(View.GONE);
            img.setVisibility(View.GONE);

            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
            btnCalc.setClickable(true);

            Intent intent = getIntent();
            int num = intent.getIntExtra("num", 0);

            EditText[] editTexts = new EditText[num];

            //add edittexts based on the number of vectors
            for (int i = 0; i < num; i++) {
                EditText et = new EditText(this);
                Paris.styleBuilder(et).add(R.style.custom_edittext).apply();
                linearLayout.addView(et);
                editTexts[i] = et;

                setupEditTextForCustomKeyboard(customKeyboard, sv, editTexts[i]);
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            Paris.styleBuilder(tv).add(R.style.custom_textView).apply();
            tv.setText("Enter the vectors in polar coordinates");
            linearLayout.addView(tv, 0);

            TextView tvHelp = new TextView(this);
            Paris.styleBuilder(tvHelp).add(R.style.custom_textViewSmallNotice).apply();
            tvHelp.setText("Use the format (r, cos(theta1), (theta2), ..., cos(theta(n-1))) [for n-dimensional vectors], where theta is in radians. \n\n theta1, theta2, ... theta(n) are measured from the vector to the x1, x2, ... x(n-1) axes. \n\n (The last cosine is automatically calculated)");
            linearLayout.addView(tvHelp, 1);

            btnCalc.setOnClickListener(v -> {
                //add the vectors
                String[] vectors = new String[editTexts.length];
                for (int i = 0; i < editTexts.length; i++) {

                    //calculate the last cosine using formula cos(theta(n)) = sqrt(1 - cos^2(theta1) - cos^2(theta2) - ... - cos^2(theta(n-1)))
                    //eg, the vector (4,0.6) is represented as (4,0.6,0.8) in polar coordinates for 2D
                    //note that editTexts.length is the number of vectors to add, not the dimension of the vector

                    String s = editTexts[i].getText().toString();
                    //remove the brackets before splitting
                    s = s.substring(1, s.length() - 1);
                    String[] parts = s.split(",");
                    System.out.println(Arrays.toString(parts));
                    System.out.println(parts[1]);
                    double sumSqrCosines = 0;
                    for (int j = 1; j < parts.length; j++) {
                        sumSqrCosines += Math.pow(Double.parseDouble(parts[j]), 2);
                    }
                    double lastCosine = Math.sqrt(1 - sumSqrCosines);


                    //remove the last bracket before appending the last cosine
                    editTexts[i].setText(editTexts[i].getText().toString().substring(0, editTexts[i].getText().toString().length() - 1));
                    editTexts[i].setText(editTexts[i].getText().toString() + "," + lastCosine + ")");

                    vectors[i] = editTexts[i].getText().toString();

                }
                //calculate the result
                String result = addPolarVectorsDirnCosines(vectors);
                tvRes.setText(Html.fromHtml(HtmlNumberFormatter(result), Html.FROM_HTML_MODE_LEGACY));

                tvRes.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);

                sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

            });

            btnCopy.setOnClickListener(v -> {
                copyToClipboard(tvRes.getText().toString());
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            });
        }

    }
}