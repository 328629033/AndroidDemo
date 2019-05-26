package com.demo.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;

import com.demo.android.mulitprocess.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/7/11.
 */

public class MyApplication extends Application{
    private int activityCount, aliveActivityCount;
    public static Context context;
    private static List<Activity> activityStack = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Printer.print("onCreate..." + Process.myPid());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ++aliveActivityCount;
                activityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

//                if(activityCount == 0)
//                    FloatViewManager.getInstance().show();
                ++activityCount;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
                if(activityCount == 0){
//                    FloatViewManager.getInstance().hide();
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
                --activityCount;
//                if(activityCount == 0){
//                    FloatViewManager.getInstance().hide();
//                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                --aliveActivityCount;
                activityStack.remove(activity);
            }
        });
    }

    public static Class getTopActivityClass(){
        return activityStack.get(activityStack.size() - 1).getClass();
    }


}
