package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

public class CSMenu extends AppCompatActivity {

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