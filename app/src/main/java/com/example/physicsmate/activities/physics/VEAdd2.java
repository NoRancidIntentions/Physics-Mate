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
import io.github.kexanie.library.MathView;

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
            MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            mv.setVisibility(View.GONE);
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
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            tv.setText("Enter the vectors in cartesian coordinates");
            linearLayout.addView(tv, 0);

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
        }

    }

    public static class VEAdd2Polar extends AppCompatActivity {

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
            MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            mv.setVisibility(View.GONE);
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
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            tv.setText("Enter the vectors in polar coordinates");
            linearLayout.addView(tv, 0);

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
        }

    }

    public static class VEAdd2PolarCosines extends AppCompatActivity {

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
            MathView mv = findViewById(R.id.latexView_activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);
            mv.setVisibility(View.GONE);
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
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            tv.setText("Enter the vectors in polar coordinates");
            linearLayout.addView(tv, 0);

            btnCalc.setOnClickListener(v -> {
                //add the vectors
                String[] vectors = new String[editTexts.length];
                for (int i = 0; i < editTexts.length; i++) {
                    vectors[i] = editTexts[i].getText().toString();
                }
                //calculate the result
                String result = addPolarVectorsDirnCosines(vectors);
                tvRes.setText(Html.fromHtml(HtmlNumberFormatter(result), Html.FROM_HTML_MODE_LEGACY));

                tvRes.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);
            });
        }

    }
}