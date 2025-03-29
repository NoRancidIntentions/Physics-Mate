package com.example.physicsmate.activities.math;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

import static com.example.physicsmate.Custom_methods.*;

public class ExpressionEvaluator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        EditText etexp, etval;
        Button btnCal, btnCopy, btnGraph;
        TextView tvResult, tv1, tv2;
        ScrollView sv = findViewById(R.id.SV_2et_3tv_2btn_1graph_dynamic_calc);
        ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        etexp = findViewById(R.id.et1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        etexp.setInputType(InputType.TYPE_CLASS_TEXT);

        etval = findViewById(R.id.et2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        btnCal = findViewById(R.id.btnCalc_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        btnCopy = findViewById(R.id.btnCopy_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        tvResult = findViewById(R.id.tvResults_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        tv1 = findViewById(R.id.tv1_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        tv2 = findViewById(R.id.tv2_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        btnGraph = findViewById(R.id.btnGraph_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);

        btnCopy.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
        btnGraph.setVisibility(View.GONE);

        Paris.styleBuilder(tvResult).add(R.style.answer_textView);

        tv1.setText("enter the function to evaluate");
        tv2.setText("Enter value of 'x'");
        btnCal.setText("Get f(x)");
        etexp.setHint("3x^2 + 5x + 4");
        etval.setHint("5 - 4.3 * 6");

        btnCal.setOnClickListener(v ->
        {
            String strexpression = etexp.getText().toString().toLowerCase();
            strexpression = replaceConstants(strexpression);

            if (strexpression.isEmpty() || etval.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {

                double val;

                val = evalf(etval.getText().toString().toLowerCase());
                System.out.println(val);


                double result = Double.parseDouble(rounded_String(evalAtPointX(strexpression, val), etval));
                System.out.println(result);

                String StrResult = "f(" + val + ") = " + result;

                tvResult.setText(Html.fromHtml(HtmlNumberFormatter(ExpressionEvaluator.this, StrResult), Html.FROM_HTML_MODE_LEGACY));

                tvResult.setVisibility(View.VISIBLE);
                btnCopy.setVisibility(View.VISIBLE);
                sv.post(() -> sv.smoothScrollTo(0, btnCopy.getBottom()));

                btnCopy.setOnClickListener(v1 -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Evaluated value", String.valueOf(result));
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(ExpressionEvaluator.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                });

            }

        });
    }
}