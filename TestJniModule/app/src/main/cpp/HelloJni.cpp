//
// Created by wuyong on 2017/3/21.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_vector_jni_MainActivity_stringFromHelloJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello jni";
    return env->NewStringUTF(hello.c_str());
}
