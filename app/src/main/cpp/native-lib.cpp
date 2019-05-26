//
// Created by herr.wang on 2017/9/7.
//
#include <jni.h>
#include <string>
#include "Process.h"
Process *process;
extern "C"
jstring
Java_com_demo_android_mulitprocess_NativeInteract_getMessage(JNIEnv *env, jobject){
    std::string message = "hello java.";
    return env->NewStringUTF(message.c_str());
}
extern "C"
pid_t Java_com_demo_android_mulitprocess_NativeInteract_createProcess(JNIEnv *env, jobject){
    process = new Process();
    return process->pid;
}
extern "C"
std::string Java_com_demo_android_mulitprocess_NativeInteract_send2Native(JNIEnv *env, jobject){
    if(process != NULL){
        return process->do_work();
//        printf(process->language.c_str());
    }
    return "not working";
}
extern "C"
bool Java_com_demo_android_mulitprocess_NativeInteract_killProcess(JNIEnv *env, jobject){
    if(process != NULL){
        process->destroy();
    }
}



