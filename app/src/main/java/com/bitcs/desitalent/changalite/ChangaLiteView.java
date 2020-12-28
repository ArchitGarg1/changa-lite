package com.bitcs.desitalent.changalite;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class ChangaLiteView extends ConstraintLayout {

    private ConstraintLayout constraintLayout;
    private WebView mWebView;
    private ViewPager2 viewPager;
    private String AppId;
    private int type = 0;
    public static Context context;
    private VideoScrollAdapter videoScrollAdapter;
    private FrameLayout fragmentContainer;

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
        this.context = context;
        if (type == 0) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.layout_changa_lite_web_view, this);

            setContentInWebView();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.layout_changa_lite, this);

            setContentInViewPager();
        }
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
    }

    public void setType(int type) {
        this.type = type;
    }

//    public void setParam() {
//        constraintLayout.setBackgroundColor(R.attr.param1);
//    }

    private void setContentInWebView() {
//        AppId = "f0626ff3-ba6b-41fc-a259-75ea2a661b6e";
        constraintLayout = findViewById(R.id.layout);
        mWebView = findViewById(R.id.web_view);
        mWebView.setBackgroundColor(getResources().getColor(R.color.black));
        String html = "<html><body><div id='changa-slider' appid=" + AppId + "></div> <script src='https://www.changa.in/assets/js/changa-lite.js'></script></body></html>";
        mWebView.loadData(html, "text/html", "UTF-8");
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    private void setContentInViewPager() {
        fragmentContainer = findViewById(R.id.fragment_container);

        VideoScrollManagerFragment fragment = new VideoScrollManagerFragment();

        try {
            Activity a = (Activity) (((ContextWrapper) getContext()).getBaseContext());
            FragmentManager fragmentManager = a.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, (Fragment) fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // switch to webview mode
            type = 0;
            init(context);
        }
    }

    private static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}