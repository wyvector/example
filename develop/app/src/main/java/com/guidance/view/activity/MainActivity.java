package com.guidance.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.guidance.R;

public class MainActivity extends AppCompatActivity {
    private FragmentTransaction transaction = null;
    private FragmentManager fm = null;
    private FrameLayout flContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void findViews() {
        flContent = (FrameLayout) findViewById(R.id.content);
    }

    private void initViews() {

    }
}
