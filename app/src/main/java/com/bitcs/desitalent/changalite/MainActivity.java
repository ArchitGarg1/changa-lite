package com.bitcs.desitalent.changalite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.web_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentInWebView();
    }

    private void setContentInWebView() {
        String html = "<html><body><h1>Hello, World!</h1></body></html>";
        mWebView.loadData(html, "text/html", "UTF-8");
    }
}