package com.bitcs.desitalent.changalite;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ChangaLiteView extends ConstraintLayout {

    private ConstraintLayout constraintLayout;
    private String AppId;
    private ImageView imageView;

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
        constraintLayout = findViewById(R.id.layout);

        imageView = findViewById(R.id.main);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPopup();
            }
        });
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
    }

    @Override
    public void setBackgroundColor(int color) {
        if (constraintLayout != null) {
            constraintLayout.setBackgroundColor(color);
        }
    }

    public void setIcon(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setIcon(Bitmap bm) {
        imageView.setImageBitmap(bm);
    }

//    public void setParam() {
//        constraintLayout.setBackgroundColor(R.attr.param1);
//    }

    private void launchPopup() {
        WebView mWebView = new WebView(getContext());
        WebSettings settings = mWebView.getSettings();
        mWebView.loadUrl("https://changa-lite-app.surge.sh/?appid=" + AppId);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setBackgroundColor(Color.BLACK);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);

        RelativeLayout.LayoutParams paramsWebView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.addContentView(mWebView, paramsWebView);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }
}