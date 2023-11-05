package com.example.physicsmate.activities.math;

import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.physicsmate.R;

import static com.example.physicsmate.Custom_methods.setupEditTextChangeListener;

public class VarSolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_var_solver);

        Button nextBtn = findViewById(R.id.nextBtn);
        Button nextBtn2 = findViewById(R.id.nextBtn2);
        EditText numOfVar = findViewById(R.id.numOfVar);
        EditText numOfEquation = findViewById(R.id.numOfEquation);

        setupEditTextChangeListener(null, nextBtn, numOfVar);
        setupEditTextChangeListener(null, nextBtn2, numOfEquation);
    }
}