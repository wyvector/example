package com.vector.jni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ndk.module1.NdkModule1Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvApp, tvMoudle1;
    Button btnApp, btnModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        tvApp = (TextView) findViewById(R.id.tv_app);
        tvMoudle1 = (TextView) findViewById(R.id.tv_module1);

        btnApp = (Button) findViewById(R.id.btn_app);
        btnModule = (Button) findViewById(R.id.btn_module1);
        btnApp.setOnClickListener(this);
        btnModule.setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String stringFromHelloJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("HelloJni-lib");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_app:
                String jniTxt = stringFromJNI();
                jniTxt += "\n" + stringFromHelloJNI();
                tvApp.setText(jniTxt);
                break;
            case R.id.btn_module1:
                String module1Txt = new NdkModule1Util().getModule1String();
                tvMoudle1.setText(module1Txt);
                break;
            default:
        }
    }
}
