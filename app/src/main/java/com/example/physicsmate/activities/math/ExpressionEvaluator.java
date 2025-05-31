package com.example.physicsmate.activities.math;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.agog.mathdisplay.MTMathView;
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
        LinearLayout ll = findViewById(R.id.ll_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        ImageView img = findViewById(R.id.imageView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
        MTMathView mv = findViewById(R.id.latexView_activity_2et_2tv_1tvresult_1calcbtn_1copybtn_1graphbtn_dynamic_calc);
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
        mv.setVisibility(View.GONE);

        Paris.styleBuilder(tvResult).add(R.style.answer_textView);
        btnCal.setEnabled(false);
        Paris.styleBuilder(btnCal).add(R.style.custom_button_disabled).apply();
        btnCal.setText("Enter Expression");

        tv1.setText("enter the function to evaluate");
        tv2.setText("Enter value of 'x'");
        btnCal.setText("Get f(x)");
        etexp.setHint("3x^2 + 5x + 4");
        etval.setHint("5 - 4.3 * 6");

        //shift the mathview to below tv2
        ll.removeView(mv);
        int index = ll.indexOfChild(etexp);
        ll.addView(mv, index + 1);

        //add a text change listener to etexp
        etexp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etexp.getText().toString().isEmpty()) {
                    mv.setVisibility(View.GONE);
                    btnCal.setEnabled(false);
                    Paris.styleBuilder(btnCal).add(R.style.custom_button_disabled).apply();
                    btnCal.setText("Enter Expression");
                } else {
                    mv.setLatex(getTex(etexp.getText().toString(), mv));
                    mv.setVisibility(View.VISIBLE);
                    btnCal.setEnabled(true);
                    Paris.styleBuilder(btnCal).add(R.style.custom_button_enabled).apply();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnCal.setOnClickListener(v ->
        {
            String strexpression = etexp.getText().toString().toLowerCase();
            strexpression = replaceConstants(strexpression);

            if (strexpression.isEmpty() || etval.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {

                hideKeyboardOnClick(btnCal, ExpressionEvaluator.this);
                double val;

                val = evalf(etval.getText().toString().toLowerCase());
                System.out.println(val);


                double result = Double.parseDouble(rounded_String(evalAtPointX(strexpression, val), etval));
                System.out.println(result);

                String StrResult = "f(" + etval.getText().toString() + ") = " + HtmlColoriser(ExpressionEvaluator.this, rounded_String(result, etval));

                tvResult.setText(Html.fromHtml(StrResult, Html.FROM_HTML_MODE_LEGACY));

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