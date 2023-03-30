//
// Created by jye on 2023/2/23.
//
#include <jni.h>
#include <android/log.h>
#include "Calculate.h"

extern "C"
JNIEXPORT jint JNICALL
Java_com_jye_jnidemo_MyNatives_nativeAdd(JNIEnv *env, jobject jobj, jint num1, jint num2) {
    return Calculate::calcAdd(num1, num2);
}

jint add(JNIEnv *env, jobject obj, jint num1, jint num2) {
    __android_log_print(ANDROID_LOG_INFO, "MyNatives-native",
                        "num1=%d, num2=%d, num1+num2=%d", num1, num2, num1 + num2);
    return Calculate::calcAdd(num1, num2);
}

jstring getString(JNIEnv *env) {
    return env->NewStringUTF("哈哈哈哈");
}

// 实现 jni.h 中的 JNI_OnLoad 方法，加载动态库时自行调用
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    // 实例化JNIEnv
    vm->GetEnv((void **) &env, JNI_VERSION_1_6);

    // java层的类名
    const char *classPath = "com/jye/jnidemo/MyNatives";
    // java函数名与JNI函数名对应关系
    const JNINativeMethod methods[] = {
            {"nativeAdd",       "(II)I",                reinterpret_cast<void *>( add)},
            {"nativeGetString", "()Ljava/lang/String;", reinterpret_cast<void *>( getString)}
    };

    // 找到对应的类
    jclass cls = env->FindClass(classPath);

    // 进行注册
    jint result = env->RegisterNatives(cls, methods, sizeof(methods) / sizeof((methods)[0]));
    __android_log_print(ANDROID_LOG_INFO, "MyNatives-native",
                        "动态函数注册: %s, result=%d", result == 0 ? "OK" : "ERR", result);
    if (result != JNI_OK) {
        return JNI_ERR;
    }

    env->CallVoidMethod(env->NewObject(cls, env->GetMethodID(cls, "<init>", "()V")),
                        env->GetMethodID(cls, "printHello", "()V"));

    return JNI_VERSION_1_6;
}