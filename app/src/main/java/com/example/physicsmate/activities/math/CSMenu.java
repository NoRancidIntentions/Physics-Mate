package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;

public class CSMenu extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csmenu);

        Button btnCircle, btnEllipse, btnParabola, btnHyperbola;

        btnCircle = findViewById(R.id.button27);
        btnEllipse = findViewById(R.id.button28);
        btnParabola = findViewById(R.id.button29);
        btnHyperbola = findViewById(R.id.button30);

        btnCircle.setOnClickListener(v -> startActivity(new Intent(CSMenu.this, CSCircleMenu.class)));
    }

}