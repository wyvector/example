#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_vector_jni_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello native";
    return env->NewStringUTF(hello.c_str());
}
