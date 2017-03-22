package com.ndk.module1;

/**
 * Created by wuyong on 2017/3/22.
 */

public class NdkModule1Util {

    static {
        System.loadLibrary("module1-lib");
    }
    public native String stringFromHelloModule1JNI();
    
    public String getModule1String(){
        return stringFromHelloModule1JNI();
    }
    


}
