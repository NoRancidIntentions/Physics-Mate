package com.example.physicsmate.activities.math;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.paris.Paris;
import com.example.physicsmate.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;
import static com.example.physicsmate.Custom_methods.solveEquationNumeric;

public class VarSolver extends AppCompatActivity {

    Button btnSolve, btnAddRow, btnCopy;
    EditText et1, etVars;
    TextView tvResult;
    LinearLayout rowsLinearLayout;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver);

        rowsLinearLayout = findViewById(R.id.var_solver_SOE);
        tvResult = findViewById(R.id.var_solver_solution);
        scrollView = findViewById(R.id.var_solver_scroll_view);

        et1 = findViewById(R.id.var_solver_et1);
        et1.addTextChangedListener(new customTextWatcher());
        etVars = findViewById(R.id.var_solver_variables);

        btnAddRow = findViewById(R.id.var_solver_add_button);
        btnAddRow.setOnClickListener(v -> addRow());
        btnCopy = findViewById(R.id.btnCopy);

        btnSolve = findViewById(R.id.var_solver_solve_button);

        tvResult.setVisibility(View.GONE);
        btnCopy.setVisibility(View.GONE);
    }

    public class customTextWatcher implements android.text.TextWatcher {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                solveButtonClickable();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                solveButtonClickable();
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                solveButtonClickable();

                if (btnSolve.isEnabled())
                {
                    btnSolve.setOnClickListener(v -> {
                        //store all the equations in array
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

                        //solve the equations in a separate thread
                        Thread thread = new Thread(() -> {
                            String result = solveEquationNumeric(eqns, vars);
                            resultParsed.set(parseString(result));
                        });
                        thread.start();

                        //after the thread is done, display the result
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        tvResult.setText(android.text.Html.fromHtml(resultParsed.get(), android.text.Html.FROM_HTML_MODE_LEGACY));
                        tvResult.setVisibility(View.VISIBLE);

                        scrollView.post(() -> scrollView.smoothScrollTo(0, tvResult.getBottom()));

                        btnCopy.setVisibility(View.VISIBLE);
                        btnCopy.setOnClickListener(v1 -> {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", tvResult.getText().toString());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(VarSolver.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                        });
                    });
                }
            }
    }

    public void addRow() {
        LinearLayout rowItem = (LinearLayout) getLayoutInflater().inflate(R.layout.var_solver_rows, null, false);
        rowsLinearLayout.addView(rowItem);
        EditText et = (EditText) rowItem.getChildAt(0);
        View[] viewsToDissapear = {tvResult};
        setupEditTextChangeListener(viewsToDissapear, btnSolve, et);

        solveButtonClickable();

        int eqnsLinearLayoutChildCount = rowsLinearLayout.getChildCount();

        for (int i = 1; i < eqnsLinearLayoutChildCount; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText eqn = (EditText) eqnRowItem.getChildAt(0);
            eqn.setHint("x" + (i + 1));
            eqn.addTextChangedListener(new customTextWatcher());

        }

        //delete listener to each row
        for (int i = 1; i < eqnsLinearLayoutChildCount; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            ImageButton LagrangeDeleteRowButton = (ImageButton) eqnRowItem.getChildAt(1);
            LagrangeDeleteRowButton.setOnClickListener(v -> {
                removeRow();
                solveButtonClickable();});
        }
    }

    public void removeRow() {
        rowsLinearLayout.removeViewAt(rowsLinearLayout.getChildCount() - 1);
        solveButtonClickable();
    }

    public void solveButtonClickable() {
        int numOfEqns = rowsLinearLayout.getChildCount();
        et1 = (EditText) rowsLinearLayout.getChildAt(0);
        for (int i = 1; i < numOfEqns; i++) {
            LinearLayout eqnRowItem = (LinearLayout) rowsLinearLayout.getChildAt(i);
            EditText et = (EditText) eqnRowItem.getChildAt(0);
            if (et.getText().toString().isEmpty() || et1.getText().toString().isEmpty()) {
                btnSolve.setClickable(false);
                btnSolve.setEnabled(false);
                btnSolve.setBackgroundResource(R.drawable.btn_disabled);
                btnSolve.setTextColor(getResources().getColor(R.color.app_bg));
                btnSolve.setText("Enter all the fields");
                btnCopy.setVisibility(View.GONE);
                System.out.println("not clickable");
                break;
            } else {
                btnSolve.setClickable(true);
                btnSolve.setEnabled(true);
                //set style of the button
                Paris.styleBuilder(btnSolve).add(R.style.custom_button_enabled).apply();
                btnSolve.setText("Solve");
                System.out.println("clickable");
            }

        }
    }

    private static String parseString(String input) {
        // Replace },{ with <br/>OR<br/>
        String result = input.replaceAll("\\},\\{", "<br/><br/>OR<br/><br/>");
        // Replace {{ with <br/>
        result = result.replaceAll("\\{\\{", "<br/>");
        // Replace }} with <br/>
        result = result.replaceAll("\\}\\}", "<br/>");
        // Replace { with <br/>
        result = result.replaceAll("\\{", "<br/>");
        // Replace } with <br/>
        result = result.replaceAll("\\}", "<br/>");
        // Replace , with <br/>
        result = result.replaceAll(",", "<br/>");
        // Replace -> with =
        result = result.replaceAll("->", "=");

        //make the numerical part #CDDC39 and the text part in #6BBBE1
        List<String> resultSplit = Arrays.asList(result.split("<br/>"));
        List<String> resultSplitParsed = resultSplit.stream().map(s -> {
            if (s.contains("=")) {
                String[] split = s.split("=");
                return "<font color='#6BBBE1'>" + split[0] + "= </font>" + "<font color='#CDDC39'>" + split[1] + "</font>";
            } else {
                return s;
            }
        }).collect(Collectors.toList());

        result = String.join("<br/>", resultSplitParsed);

        return result;
    }



}