package com.example.physicsmate.activities.math;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.physicsmate.R;

public class VEAdd2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veadd2);

        Button btnCartesian = findViewById(R.id.veadd2_btn_cartesian);
        Button btnPolar = findViewById(R.id.veadd2_btn_polar);

        btnCartesian.setOnClickListener(view -> {
            //TODO
        });
        btnPolar.setOnClickListener(view -> {
            //TODO
        });
    }
}