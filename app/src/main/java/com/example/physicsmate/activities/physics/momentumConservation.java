package com.example.physicsmate.activities.physics;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;

import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;

public class momentumConservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        TextView tv1 = findViewById(R.id.tv1_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        TextView tv2 = findViewById(R.id.tv2_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        TextView tvRes = findViewById(R.id.tvResults_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        EditText et1 = findViewById(R.id.et1_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        EditText et2 = findViewById(R.id.et2_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        Button btnCalc = findViewById(R.id.btnCalc_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        Button btnCopy = findViewById(R.id.btnCopy_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        ImageView img = findViewById(R.id.imageView_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        custom_keyboard1_initializer keyboard = findViewById(R.id.custom_keyboard1_initializer_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        ScrollView sv = findViewById(R.id.SV_activity_4et_4tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        View[] viewsToDisappear = {btnCopy, tvRes};

        btnCopy.setVisibility(View.GONE);
        tvRes.setVisibility(View.GONE);

        img.setVisibility(View.GONE);

        Paris.styleBuilder(btnCalc).add(R.style.custom_button_disabled).apply();
        btnCalc.setClickable(false);
        setupEditTextChangeListener(viewsToDisappear, btnCalc, keyboard, et1, et2);

    }
}