package com.example.physicsmate.activities.math;

import android.text.Html;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.physicsmate.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;
import static com.example.physicsmate.Custom_methods.solveEquationNumeric;

public class VarSolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver);

        LinearLayout rowsContainer = findViewById(R.id.var_solver_SOE);
        ArrayList<EditText> equations = new ArrayList<>();
        Button solveButton = findViewById(R.id.var_solver_solve_button);
        TextView result = findViewById(R.id.var_solver_solution);
        EditText variables = findViewById(R.id.var_solver_variables);

        View[] viewsToDisappear = {result};
        solveButton.setEnabled(false);

        //the first row does not have a delete button, and is simply an edittext, comtaining the first (mandatory) equation.
        //the rest of the rows are added dynamically, and have a delete button just nextto the edittext. this is a horizontal linear layout.

        EditText et1 = findViewById(R.id.var_solver_et1); //the first edittext
        equations.add(et1);

        //setup rest of the rows
        for (int i = 1; i < rowsContainer.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) rowsContainer.getChildAt(i);
            EditText et = (EditText) row.getChildAt(0);
            equations.add(et);
            ImageButton deleteButton = (ImageButton) row.getChildAt(1);
            deleteButton.setOnClickListener(v -> {
                rowsContainer.removeView(row);
                checkSolveButton(equations, solveButton);
            });
            setupEditTextChangeListener(viewsToDisappear, solveButton, equations);
        }

        //add row button
        Button addRowButton = findViewById(R.id.var_solver_add_button);
        addRowButton.setOnClickListener(v ->
        {
            LinearLayout row = (LinearLayout) getLayoutInflater().inflate(R.layout.var_solver_rows, null);
            EditText et = (EditText) row.getChildAt(0);
            equations.add(et);
            ImageButton deleteButton = (ImageButton) row.getChildAt(1);
            deleteButton.setOnClickListener(v1 ->
            {
                checkSolveButton(equations, solveButton);
                rowsContainer.removeView(row);
                checkSolveButton(equations, solveButton);
                System.out.println(equations);
                System.out.println("Delete button clicked");
            });
            rowsContainer.addView(row);
            setupEditTextChangeListener(viewsToDisappear, solveButton, equations);
        });

        //solve button
        solveButton.setOnClickListener(v -> {
            checkSolveButton(equations, solveButton);
            if (solveButton.isEnabled()) {
                System.out.println("Solve button clicked");
                // Store the equations in string form: "{eqn1, eqn2, eqn3, ...}"
                Collection<String> eqns = new ArrayList<>();
                for (EditText equation : equations) {
                    eqns.add(equation.getText().toString());
                }
                // Store the variables in string form: "{var1, var2, var3, ...}"
                Collection<String> vars = new ArrayList<>();
                for (String var : variables.getText().toString().split(",")) {
                    vars.add(var.trim());
                }
                String resultString = solveEquationNumeric(eqns, vars);

                result.setText(Html.fromHtml(formatResult(resultString), Html.FROM_HTML_MODE_LEGACY));
                result.setVisibility(View.VISIBLE);
                checkSolveButton(equations, solveButton);
                eqns.clear();
            } else {
                System.out.println("Solve button disabled");
            }
        });

    }

    private static String formatResult(String input) {
        return Arrays.stream(input.replaceAll("[{}]", "").split(","))
                .map(pair -> pair.split("->"))
                .filter(keyValue -> keyValue.length == 2)
                .map(keyValue -> {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    return "<font color='#6BBBE1'>" + key + " is </font><font color='#CDDC39'>" + value + "</font>";
                })
                .collect(Collectors.joining("<br/>"));
    }

    //if any edittext is empty, the solve button is disabled. check clickablity of solve button
    private void checkSolveButton(Iterable<EditText> equations, Button solveButton) {
        for (EditText equation : equations) {
            if (equation.getText().toString().equals("")) {
                solveButton.setEnabled(false);
                return;
            }
        }
        solveButton.setEnabled(true);
    }
}