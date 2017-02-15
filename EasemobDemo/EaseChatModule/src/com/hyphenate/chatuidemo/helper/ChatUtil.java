package com.hyphenate.chatuidemo.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.easemob.redpacketsdk.RedPacket;
import com.hyphenate.chatuidemo.DemoApplication;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.ui.SplashActivity;


/**
 * Created by wuyong on 2017/2/15.
 */

public class ChatUtil {

    public static void initAtApplication(Context appContext) {
        DemoApplication.setAppContext(appContext);
        DemoHelper.getInstance().init(appContext);
        //red packet code : 初始化红包上下文，开启日志输出开关
        RedPacket.getInstance().initContext(appContext);
        RedPacket.getInstance().setDebugMode(true);
    }

    public static void startChatMenu() {

    }

    public static void startChatDemo(Activity activity) {
        Intent i = new Intent();
        i.setClass(activity, SplashActivity.class);
        activity.startActivity(i);
    }
}
