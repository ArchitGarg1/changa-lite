package com.bitcs.desitalent.changalite;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ChangaLiteView extends ConstraintLayout {

    private ConstraintLayout constraintLayout;
    private WebView mWebView;
    private String AppId;

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

        setContentInWebView();
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
        setContentInWebView();
    }

    @Override
    public void setBackgroundColor(int color) {
        if (mWebView != null) {
            mWebView.setBackgroundColor(color);
        }
    }

//    public void setParam() {
//        constraintLayout.setBackgroundColor(R.attr.param1);
//    }

    private void setContentInWebView() {
//        AppId = "f0626ff3-ba6b-41fc-a259-75ea2a661b6e";
        mWebView = findViewById(R.id.web_view);
        WebSettings settings = mWebView.getSettings();
        constraintLayout = findViewById(R.id.layout);
        String html = "<html><body><div id='changa-slider' appid=" + AppId + " slider-type='vertical'></div> <script src='https://www.changa.in/assets/js/changa-lite.js'></script></body></html>";
        mWebView.loadData(html, "text/html", "UTF-8");
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setUseWideViewPort(false);     // to switch in desktop and mobile site
    }

    private static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}