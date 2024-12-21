package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

        Button btnNext = findViewById(R.id.btnNext_activity_veadd2);
        CheckBox btnCartesian = findViewById(R.id.checkbox_Cartesian);
        CheckBox btnPolar = findViewById(R.id.checkbox_Polar);
        EditText etNum = findViewById(R.id.editText_activity_veadd2);

        int dim = Integer.parseInt(etNum.getText().toString());

        btnNext.setOnClickListener(v -> {
            if (btnCartesian.isChecked()) {
                Intent intent = new Intent(this, VEAdd2Cart.class);
                intent.putExtra("dim", dim);
                startActivity(intent);
            } else if (btnPolar.isChecked()) {
                Intent intent = new Intent(this, VEAdd2Polar.class);
                intent.putExtra("dim", dim);
                startActivity(intent);
            }
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

            btnCopy.setVisibility(View.GONE);
            tvRes.setVisibility(View.GONE);

            img.setVisibility(View.GONE);

            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
            btnCalc.setClickable(true);

            Intent intent = getIntent();
            int dim = intent.getIntExtra("dim", 0);

            EditText[] editTexts = new EditText[dim];

            //add edittexts based on the number of dimensions
            for (int i = 0; i < dim; i++) {
                EditText et = new EditText(this);
                linearLayout.addView(et);
                editTexts[i] = et;
            }

            //setup the edittext change listener
            View[] viewsToDissappear = new View[]{tvRes, btnCopy};
            setupEditTextChangeListener(viewsToDissappear, btnCalc, customKeyboard, Arrays.asList(editTexts));

            //add a textview at the top (child 0), asking the user to enter the vectors
            TextView tv = new TextView(this);
            tv.setText("Enter the vectors in Cartesian coordinates");
            linearLayout.addView(tv, 0);

            btnCalc.setOnClickListener(v -> {
                //add the vectors
                String[] vectors = new String[editTexts.length];
                for (int i = 0; i < editTexts.length; i++) {
                    vectors[i] = editTexts[i].getText().toString();
                }
                //calculate the result
                String result = "Result = " + addVectors(vectors);
                tvRes.setText(Html.fromHtml(HtmlNumberFormatter(result), Html.FROM_HTML_MODE_LEGACY));
            });

        }
    }

    public static class VEAdd2Polar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_net_ntv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        }
    }
}