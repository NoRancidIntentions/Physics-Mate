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
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;
import live.pw.renderX.mathJaxView.MathJaxView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.physicsmate.Custom_methods.*;

public class VarSolver2 extends AppCompatActivity {

    Button btnSolve, btnAddRow, btnCopy;
    EditText et1, etVars;
    TextView tvResult;
    LinearLayout rowsLinearLayout, MVsLinearLayout;
    ScrollView scrollView;
    MathJaxView mv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver2);

        rowsLinearLayout = findViewById(R.id.var_solver_ets);
        tvResult = findViewById(R.id.var_solver_solution);
        scrollView = findViewById(R.id.var_solver_scroll_view);

        et1 = findViewById(R.id.var_solver_et1);
        et1.addTextChangedListener(new CustomTextWatcher());
        etVars = findViewById(R.id.var_solver_variables);

        btnAddRow = findViewById(R.id.var_solver_add_button);
        btnAddRow.setOnClickListener(v -> addRow());

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setVisibility(View.GONE);

        btnSolve = findViewById(R.id.var_solver_solve_button);
        btnSolve.setOnClickListener(v -> solveEquations());

        mv1 = findViewById(R.id.var_solver_mv1);
        MVsLinearLayout = findViewById(R.id.var_solver_MVs);

        tvResult.setVisibility(View.GONE);
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
                tvResult.setText(Html.fromHtml(resultParsed.get(), Html.FROM_HTML_MODE_LEGACY));
                tvResult.setVisibility(View.VISIBLE);
                scrollView.post(() -> scrollView.smoothScrollTo(0, tvResult.getBottom()));
                btnCopy.setVisibility(View.VISIBLE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        btnCopy.setOnClickListener(v -> {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", tvResult.getText().toString());
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

        @SuppressLint("InflateParams")
        MathJaxView mvItem = (MathJaxView) getLayoutInflater().inflate(R.layout.var_solver_mv2, null, false);
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
            mv1.setText(null);
            return;
        }

        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            if (et.getText().toString().isEmpty()) {
                setSolveButtonState(false, "Enter all the fields");
                mv1.setText(null);
                return;
            }
        }

        setSolveButtonState(true, "Solve");
        mv1.setText(getTex(et1.getText().toString()));

        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            MathJaxView mv = (MathJaxView) MVsLinearLayout.getChildAt(i);
            mv.setText(getTex(et.getText().toString()));
        }
    }

    private void setSolveButtonState(boolean enabled, String message) {
        btnSolve.setEnabled(enabled);
        btnSolve.setClickable(enabled);
        btnSolve.setText(message);
        if (enabled) {
            Paris.styleBuilder(btnSolve).add(R.style.custom_button_enabled).apply();
        } else {
            btnSolve.setBackgroundResource(R.drawable.btn_disabled);
            btnSolve.setTextColor(getResources().getColor(R.color.app_bg));
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
