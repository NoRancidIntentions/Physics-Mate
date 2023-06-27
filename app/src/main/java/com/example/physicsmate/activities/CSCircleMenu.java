package com.example.physicsmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

public class CSCircleMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cscircle_menu);

        Button Graph, Info;

        Graph = findViewById(R.id.button22);
        Info = findViewById(R.id.button23);

        Graph.setOnClickListener(v -> startActivity(new Intent(CSCircleMenu.this, CSCircleGraphInfoGetter.class)));
    }

}
