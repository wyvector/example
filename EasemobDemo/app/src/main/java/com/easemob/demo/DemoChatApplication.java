package com.easemob.demo;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chatuidemo.helper.ChatUtil;

/**
 * Created by wuyong on 2017/2/15.
 */

public class DemoChatApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ChatUtil.initAtApplication(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
