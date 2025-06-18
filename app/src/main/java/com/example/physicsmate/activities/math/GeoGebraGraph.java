package com.example.physicsmate.activities.math;

import android.os.Bundle;
import android.webkit.WebView;
import com.example.physicsmate.R;
import com.example.physicsmate.ui.BaseActivity;

import static com.example.physicsmate.Custom_methods.WebViewLoader;

public class GeoGebraGraph extends BaseActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_webview);

        wv = findViewById(R.id.webView);
        WebViewLoader(wv, "https://www.geogebra.org/graphing?lang=en");
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