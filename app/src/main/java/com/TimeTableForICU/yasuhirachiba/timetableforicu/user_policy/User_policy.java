package com.TimeTableForICU.yasuhirachiba.timetableforicu.user_policy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;

public class User_policy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        WebView oWebView = new WebView(getApplicationContext());
        oWebView.loadUrl("file:///android_asset/userPolicy.html");
        setContentView(oWebView);
    }



}
