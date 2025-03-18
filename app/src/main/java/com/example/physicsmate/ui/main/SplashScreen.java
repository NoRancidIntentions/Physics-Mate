package com.example.physicsmate.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private ImageView image;
    private AnimatedVectorDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(new MyRunnable());
        thread.start();
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        image = findViewById(R.id.object);

        image.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(SplashScreen.this, MainActivity.class));

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Drawable d = image.getDrawable();
        if (d instanceof AnimatedVectorDrawable) {
            Log.d("testanim", "onCreate: instancefound" + d);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                animation = (AnimatedVectorDrawable) d;
            }
            animation.start();

            animation.registerAnimationCallback(new Animatable2.AnimationCallback() {
                public void onAnimationEnd(Drawable drawable) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    public static class MyRunnable implements Runnable {
        public void run() {
            try {
                UI_Solver_Tools_Linker.LoggerFix.fix();
                // ExprEvaluator exprEvaluator = new ExprEvaluator();
                // Log.d(TAG, String.valueOf(exprEvaluator.eval("test")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}