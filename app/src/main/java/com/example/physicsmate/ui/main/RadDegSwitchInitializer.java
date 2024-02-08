package com.example.physicsmate.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import com.example.physicsmate.R;

public class RadDegSwitchInitializer extends LinearLayout
{

    Boolean isRad = true;

    public RadDegSwitchInitializer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rad_deg_switch, this, true);

        ToggleButton radButton, degButton;

        radButton = findViewById(R.id.toggleRad);
        degButton = findViewById(R.id.toggleDeg);

        radButton.setChecked(true);
        degButton.setChecked(false);


        radButton.setOnClickListener(v -> {
            radButton.setBackgroundColor(context.getResources().getColor(R.color.yellow_num_answer));
            degButton.setBackgroundColor(context.getResources().getColor(R.color.app_bg));
            radButton.setTextColor(context.getResources().getColor(R.color.app_bg));
            degButton.setTextColor(context.getResources().getColor(R.color.yellow_num_answer));
            isRad = true;
        });

        degButton.setOnClickListener(v -> {
            degButton.setBackgroundColor(context.getResources().getColor(R.color.yellow_num_answer));
            radButton.setBackgroundColor(context.getResources().getColor(R.color.app_bg));
            degButton.setTextColor(context.getResources().getColor(R.color.app_bg));
            radButton.setTextColor(context.getResources().getColor(R.color.yellow_num_answer));
            isRad = false;
        });

    }

    public Boolean isRad() {
        return isRad;
    }

    public void setRad(Boolean rad) {
        isRad = rad;
        if (isRad)
        {
            findViewById(R.id.toggleRad).performClick();
        }
        else
        {
            findViewById(R.id.toggleDeg).performClick();
        }
    }

}
