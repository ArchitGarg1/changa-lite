package com.bitcs.desitalent.changalite.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bitcs.desitalent.changalite.ChangaLiteView;
import com.bitcs.desitalent.changalite.R;

public class DemoActivity extends AppCompatActivity {

    ChangaLiteView changaLiteView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        changaLiteView = findViewById(R.id.changa_lite_view);
        changaLiteView.setAppId("f0626ff3-ba6b-41fc-a259-75ea2a661b6e");
    }
}
