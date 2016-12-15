package com.umeng.soexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.Config;
import com.umeng.soexample.share_auth.AuthActivity;
import com.umeng.soexample.share_auth.CheckActivity;
import com.umeng.soexample.share_auth.ShareMenuActivity;
import com.umeng.soexample.share_auth.UserinfoActivity;


/**
 * Created by umeng on 15/9/14.
 */
public class MainActivity extends Activity {
    private Button shareButton, authbutton, getinfoButton,selfcheckButton;



    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.umeng_share) {
                Intent intent = new Intent(MainActivity.this, ShareMenuActivity.class);
                startActivity(intent);
            } else if (view.getId() == R.id.umeng_auth) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            } else if (view.getId() == R.id.umeng_getinfo) {
                Intent intent = new Intent(MainActivity.this, UserinfoActivity.class);
                startActivity(intent);
            } else if (view.getId() == R.id.umeng_selfcheck) {
                Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(intent);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        Config.dialogSwitch = true;
//          如想让你的app在android 6.0系统上也能运行的话，需要动态获取权限，没有权限的话分享sdk会出错，参考一下代码做动态获取权限,适配安卓6.0系统
//          你需要最新的android.support.v4包，或者v13的包可也以
//            if(Build.VERSION.SDK_INT>=23){
//                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
//                ActivityCompat.requestPermissions(this,mPermissionList,123);
//            }
        authbutton = (Button) findViewById(R.id.umeng_auth);
        shareButton = (Button) findViewById(R.id.umeng_share);
        getinfoButton = (Button) findViewById(R.id.umeng_getinfo);
        selfcheckButton = (Button) findViewById(R.id.umeng_selfcheck);
        authbutton.setOnClickListener(clickListener);
        shareButton.setOnClickListener(clickListener);
        getinfoButton.setOnClickListener(clickListener);
        selfcheckButton.setOnClickListener(clickListener);


    }

}


