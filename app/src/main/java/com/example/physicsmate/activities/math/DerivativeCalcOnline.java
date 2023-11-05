package com.example.physicsmate.activities.math;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.physicsmate.R;

import static com.example.physicsmate.Custom_methods.WebViewLoader;

public class DerivativeCalcOnline extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_webview);

        wv = findViewById(R.id.webView);
        WebViewLoader(wv, "https://www.derivative-calculator.net/");
    }


    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}