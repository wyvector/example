//
// Created by wuyong on 2017/3/22.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ndk_module1_NdkModule1Util_stringFromHelloModule1JNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello module1";
    return env->NewStringUTF(hello.c_str());
}
