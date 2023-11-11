package com.example.physicsmate.activities.math;

import android.media.Image;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.physicsmate.R;

import java.util.ArrayList;

import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;
import static com.example.physicsmate.Custom_methods.solveEquationNumeric;

public class VarSolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver);

        LinearLayout rowsContainer = findViewById(R.id.var_solver_SOE);
        EditText[] equations = new EditText[rowsContainer.getChildCount()];
        Button solveButton = findViewById(R.id.var_solver_solve_button);
        TextView result = findViewById(R.id.var_solver_solution);
        EditText variables = findViewById(R.id.var_solver_variables);

        View[] viewsToDisappear = {result};

        //the first row does not have a delete button, and is simply an edittext, comtaining the first (mandatory) equation.
        //the rest of the rows are added dynamically, and have a delete button just nextto the edittext. this is a horizontal linear layout.

        EditText et1 = findViewById(R.id.var_solver_et1); //the first edittext (inside the rowsContainer)
        equations[0] = et1;

        setupEditTextChangeListener(viewsToDisappear, solveButton, equations[0]);
        setupEditTextChangeListener(viewsToDisappear, solveButton, variables);


        //setup rest of the rows
        for (int i = 1; i < rowsContainer.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) rowsContainer.getChildAt(i);
            EditText et = (EditText) row.getChildAt(0);
            equations[i] = et;
            ImageButton deleteButton = (ImageButton) row.getChildAt(1);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rowsContainer.removeView(row);
                    checkSolveButton(equations, solveButton);
                }
            });
            setupEditTextChangeListener(viewsToDisappear, solveButton, equations[i]);
        }

        //add row button
        Button addRowButton = findViewById(R.id.var_solver_add_button);
        addRowButton.setOnClickListener(v -> {
            LinearLayout row = (LinearLayout) getLayoutInflater().inflate(R.layout.var_solver_rows, null);
            EditText et = (EditText) row.getChildAt(0);
            equations[equations.length - 1] = et;
            ImageButton deleteButton = (ImageButton) row.getChildAt(1);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rowsContainer.removeView(row);
                    checkSolveButton(equations, solveButton);
                }
            });
            rowsContainer.addView(row);
            setupEditTextChangeListener(viewsToDisappear, solveButton, equations);
        });

        //solve button
        solveButton.setOnClickListener(v -> {
            checkSolveButton(equations, solveButton);
            if (solveButton.isEnabled()) {
                System.out.println("Solve button clicked");
                //store the eqns in string form: "{eqn1, eqn2, eqn3, ...}"
                ArrayList<String> eqns = new ArrayList<>();
                for (EditText equation : equations) {
                    eqns.add(equation.getText().toString());
                }
                //store the variables in string form: "{var1, var2, var3, ...}"
                ArrayList<String> vars = new ArrayList<>();
                for (String var : variables.getText().toString().split(",")) {
                    vars.add(var.trim());
                }
                String resultString = solveEquationNumeric(eqns, vars);

                result.setText(resultString);
                result.setVisibility(View.VISIBLE);
            }
            else {
                System.out.println("Solve button disabled");
            }
        });

    }

    //if any edittext is empty, the solve button is disabled. check clickablity of solve button
    private void checkSolveButton(EditText[] equations, Button solveButton) {
        for (EditText equation : equations) {
            if (equation.getText().toString().equals("")) {
                solveButton.setEnabled(false);
                return;
            }
        }
        solveButton.setEnabled(true);
    }
}