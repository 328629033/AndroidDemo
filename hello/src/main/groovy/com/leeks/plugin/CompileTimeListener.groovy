package com.leeks.plugin

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import org.gradle.util.Clock

class CompileTimeListener implements TaskExecutionListener, BuildListener{
    private Clock clock
    private times = []

    @Override
    void beforeExecute(Task task) {
        clock = new Clock()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        def ms = clock.timeInMs
        times.add([ms, task.path])
//        task.project.logger.warn "${task.path} spend ${ms}ms"
    }

    @Override
    void buildStarted(Gradle gradle) {

    }

    @Override
    void settingsEvaluated(Settings settings) {

    }

    @Override
    void projectsLoaded(Gradle gradle) {

    }

    @Override
    void projectsEvaluated(Gradle gradle) {

    }

    @Override
    void buildFinished(BuildResult buildResult) {

        println "Task Spend Time:"
        def totalTime = 0
        for(time in times){
            printf "%10sms   %s\n", time
            totalTime += time[0]
        }
        printf "total time is %s\n", totalTime

        println("the task execution time over 1000ms will be shown below")
        for(time in times){
            if(time[0] > 1000){
                printf "%10sms   %s\n", time
            }
        }

    }

}