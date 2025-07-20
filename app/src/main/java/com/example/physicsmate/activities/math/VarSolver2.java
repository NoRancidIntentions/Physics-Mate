package com.example.physicsmate.activities.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.agog.mathdisplay.MTMathView;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;
import com.example.physicsmate.ui.main.CustomKeyboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.physicsmate.Custom_methods.*;

public class VarSolver2 extends BaseActivity {

    Button btnCalc, btnAddRow, btnCopy;
    EditText et1, etVars;
    TextView tvRes;
    LinearLayout rowsLinearLayout, MVsLinearLayout;
    ScrollView sv;
    MTMathView mv1;
    CustomKeyboard customKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver2);

        rowsLinearLayout = findViewById(R.id.var_solver_ets);
        tvRes = findViewById(R.id.var_solver_solution);
        sv = findViewById(R.id.var_solver_scroll_view);

        et1 = findViewById(R.id.var_solver_et1);
        et1.addTextChangedListener(new CustomTextWatcher());
        etVars = findViewById(R.id.var_solver_variables);

        btnAddRow = findViewById(R.id.var_solver_add_button);
        btnAddRow.setOnClickListener(v -> addRow());

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setVisibility(View.GONE);

        btnCalc = findViewById(R.id.var_solver_solve_button);

        View[] viewsToDisappear = {btnCopy, tvRes};
        Collection<EditText> editTextList = new ArrayList<>();
        editTextList.add(et1);
        editTextList.add(etVars);

        base_constraint_layout.post(() -> {
            customKeyboard = getKeyboard();
            if (customKeyboard != null) {
                customKeyboard.hideKeyboard();
                setupEditTextForCustomKeyboard(customKeyboard, sv, et1, etVars);
                //setupEditTextChangeListener(viewsToDisappear, btnCalc, customKeyboard, editTextList);
            }
        });

        setLinkedScrollView(sv);

        btnCalc.setOnClickListener(v ->
        {
            customKeyboard.hideKeyboard();
            solveEquations();
        });

        mv1 = findViewById(R.id.var_solver_mv1);
        MVsLinearLayout = findViewById(R.id.var_solver_MVs);

        tvRes.setVisibility(View.GONE);
        mv1.setVisibility(View.GONE);
    }

    private void solveEquations() {
        int numOfEqns = rowsLinearLayout.getChildCount();
        Collection<String> eqns = new ArrayList<>();
        eqns.add(et1.getText().toString());
        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            eqns.add(et.getText().toString());
        }

        String vars = etVars.getText().toString();
        AtomicReference<String> resultParsed = new AtomicReference<>("");

        Thread thread = new Thread(() -> {
            String result = solveEquationNumeric(eqns, vars);
            resultParsed.set(parseStringAfterSolvingEqn(VarSolver2.this, result));
        });
        thread.start();

        new Handler(Looper.getMainLooper()).post(() -> {
            try {
                thread.join();
                tvRes.setText(Html.fromHtml(resultParsed.get(), Html.FROM_HTML_MODE_LEGACY));
                tvRes.setVisibility(View.VISIBLE);
                sv.post(() -> sv.smoothScrollTo(0, tvRes.getBottom()));
                btnCopy.setVisibility(View.VISIBLE);
            } catch (InterruptedException ignored) {
            }
        });

        btnCopy.setOnClickListener(v -> {
            customKeyboard.hideKeyboard();
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", tvRes.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(VarSolver2.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });
    }

    public void addRow() {
        @SuppressLint("InflateParams")
        LinearLayout rowItem = (LinearLayout) getLayoutInflater().inflate(R.layout.var_solver_rows, null, false);
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        rowItem.startAnimation(fadeIn);

        rowsLinearLayout.addView(rowItem);
        EditText et = (EditText) rowItem.getChildAt(0);
        et.addTextChangedListener(new CustomTextWatcher());
        setupEditTextForCustomKeyboard(customKeyboard, sv, et);

        @SuppressLint("InflateParams")
        MTMathView mvItem = (MTMathView) getLayoutInflater().inflate(R.layout.var_solver_mv2, null, false);
        MVsLinearLayout.addView(mvItem);

        int lastIndex = rowsLinearLayout.getChildCount() - 1;
        ImageButton deleteBtn = (ImageButton) rowItem.getChildAt(1);
        deleteBtn.setOnClickListener(v -> removeRowWithAnimation(lastIndex));

        solveButtonClickable();
    }

    public void removeRowWithAnimation(int index) {
        try {
            View rowView = rowsLinearLayout.getChildAt(index);
            View mvView = MVsLinearLayout.getChildAt(index);

            Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
            rowView.startAnimation(fadeOut);
            mvView.startAnimation(fadeOut);

            new Handler().postDelayed(() -> {
                rowsLinearLayout.removeViewAt(index);
                MVsLinearLayout.removeViewAt(index);
                solveButtonClickable();
            }, fadeOut.getDuration());
        } catch (RuntimeException e) {
            Toast.makeText(this, "Error removing row", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void solveButtonClickable() {
        int numOfEqns = rowsLinearLayout.getChildCount();

        if (et1.getText().toString().isEmpty()) {
            setSolveButtonState(false, "Enter all the fields");
            mv1.setVisibility(View.GONE);
            return;
        }

        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            if (et.getText().toString().isEmpty()) {
                setSolveButtonState(false, "Enter all the fields");
                mv1.setVisibility(View.GONE);
                return;
            }
        }

        setSolveButtonState(true, "Solve");
        mv1.setLatex(getTex(et1.getText().toString(), mv1));
        mv1.setVisibility(View.VISIBLE);

        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            MTMathView mv = (MTMathView) MVsLinearLayout.getChildAt(i);
            mv.setLatex(getTex(et.getText().toString(), mv));
        }
    }

    private void setSolveButtonState(boolean enabled, CharSequence message) {
        btnCalc.setEnabled(enabled);
        btnCalc.setClickable(enabled);
        btnCalc.setText(message);
        if (enabled) {
            Paris.styleBuilder(btnCalc).add(R.style.custom_button_enabled).apply();
        } else {
            btnCalc.setBackgroundResource(R.drawable.btn_disabled);
            btnCalc.setTextColor(getResources().getColor(R.color.app_bg));
        }
    }

    public class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            solveButtonClickable();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            solveButtonClickable();
        }

        @Override
        public void afterTextChanged(Editable s) {
            solveButtonClickable();
        }
    }
}
