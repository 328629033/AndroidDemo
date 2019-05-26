//
// Created by herr.wang on 2017/9/7.
//

#ifndef ANDROIDDEMO_PROCESS_H
#define ANDROIDDEMO_PROCESS_H
#include <jni.h>
#include <android/log.h>
#include <sys/select.h>
#include <string>
#include <unistd.h>
#include <signal.h>

#define LOG_TAG "native"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

class Process {
public:
    Process();
    std::string do_work();
    void destroy();
    pid_t pid;
    std::string language;
};


#endif //ANDROIDDEMO_PROCESS_H
