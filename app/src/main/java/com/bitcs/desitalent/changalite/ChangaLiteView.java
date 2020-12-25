package com.bitcs.desitalent.changalite;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ChangaLiteView extends ConstraintLayout {

    private ConstraintLayout constraintLayout;
    private WebView mWebView;
    private String AppId = "f0626ff3-ba6b-41fc-a259-75ea2a661b6e";

    public ChangaLiteView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ChangaLiteView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChangaLiteView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ChangaLiteView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_changa_lite, this);

        mWebView = findViewById(R.id.web_view);
        constraintLayout = findViewById(R.id.layout);
        setContentInWebView();
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
    }

    public void setParam() {
//        constraintLayout.setBackgroundColor(R.attr.param1);
    }

    private void setContentInWebView() {
        String html = "<html><body><div id='changa-slider' appid=" + AppId + "></div> <script src='https://www.changa.in/assets/js/changa-lite.js'></script></body></html>";
        mWebView.loadData(html, "text/html", "UTF-8");
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    private static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}