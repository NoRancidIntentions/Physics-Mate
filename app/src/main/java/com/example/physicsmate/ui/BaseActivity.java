package com.example.physicsmate.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.main.custom_keyboard1_initializer;


public class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;
    AppCompatImageButton menuButton;
    DrawerLayout drawerLayout;
    public ConstraintLayout base_constraint_layout;
    public custom_keyboard1_initializer keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base); // Contains the toolbar and FrameLayout
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getWindow().getEnterTransition().excludeTarget(toolbar, true);
        getWindow().getReturnTransition().excludeTarget(toolbar, true);


        base_constraint_layout = findViewById(R.id.base_constraint_layout);

        base_constraint_layout.post(() -> {
            keyboard = new custom_keyboard1_initializer(this, null);
            base_constraint_layout.addView(keyboard);
            //set constraints
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            layoutParams.bottomToBottom = base_constraint_layout.getId();
            layoutParams.leftToLeft = base_constraint_layout.getId();
            layoutParams.rightToRight = base_constraint_layout.getId();
            keyboard.setLayoutParams(layoutParams);
            keyboard.setVisibility(View.GONE);
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }

    // Inject your custom activity content here
    @Override
    public void setContentView(int layoutResID) {
        ViewGroup container = findViewById(R.id.base_content);// Defined in activity_base_with_toolbar.xml

        container.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, container, true);

    }

    public custom_keyboard1_initializer getKeyboard() {
        return keyboard;
    }


}
