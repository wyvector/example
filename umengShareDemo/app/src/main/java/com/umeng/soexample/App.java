package com.umeng.soexample;

import android.app.Application;

import com.share.umeng.simplify.ShareInit;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();        
        ShareInit.init(this);
    }
}
