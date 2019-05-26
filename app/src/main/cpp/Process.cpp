//
// Created by herr.wang on 2017/9/7.
//

#include "Process.h"

Process::Process() {
    LOGE("process create...");
    pid = fork();
    language = "c";
}

std::string Process::do_work() {
    language = "java";
    return "java";
}

void Process::destroy() {

    kill(pid, SIGKILL);
}
