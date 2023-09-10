package com.example.physicsmate.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import com.example.physicsmate.R;

public class RadDegSwitch extends LinearLayout {

    @SuppressLint("UseCompatLoadingForDrawables")
    public RadDegSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Inflate the rad/deg switch
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rad_deg, this, true);

        // Initialize toggle buttons
        ToggleButton radButton = findViewById(R.id.toggleRad);
        ToggleButton degButton = findViewById(R.id.toggleDeg);

        // Set the default state of the toggle buttons
        radButton.setChecked(true);
        degButton.setChecked(false);

        // Set the listener for the toggle buttons
        radButton.setOnClickListener(v -> {
            radButton.setChecked(true);
            degButton.setChecked(false);

            radButton.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.color.yellow_num_answer, null));
            degButton.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.color.app_bg, null));

            radButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.app_bg, null));
            degButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.yellow_num_answer, null));
        });

        degButton.setOnClickListener(v -> {
            radButton.setChecked(false);
            degButton.setChecked(true);

            radButton.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.color.app_bg, null));
            degButton.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.color.yellow_num_answer, null));

            radButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.yellow_num_answer, null));
            degButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.app_bg, null));
        });

    }

    public boolean isRad() {
        ToggleButton radButton = findViewById(R.id.toggleRad);
        return radButton.isChecked();
    }
}
