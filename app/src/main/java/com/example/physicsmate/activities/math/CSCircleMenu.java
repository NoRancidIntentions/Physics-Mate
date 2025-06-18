package com.example.physicsmate.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;

public class CSCircleMenu extends BaseActivity {

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
