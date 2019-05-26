package com.demo.android.proxy;

import android.os.Handler;
import android.os.Looper;

import com.demo.android.mulitprocess.Printer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by herr.wang on 2018/1/3.
 */

public class ProxyTest {
    public static final ProxyTest instance = new ProxyTest();
    private Subject subject, subjectProxy;
    Handler handler = new Handler(Looper.getMainLooper());

    public void setSubject(Subject subject) {
        this.subject = subject;
        SubjectHandler subjectHandler = new SubjectHandler();
        Printer.print("set subject...");
        subjectProxy = (Subject) Proxy.newProxyInstance(subjectHandler.getClass().getClassLoader(), this.subject.getClass().getInterfaces(), subjectHandler);
    }

    public void startWork() {
        new Thread() {
            @Override
            public void run() {
                Printer.print("work...");
                if (subjectProxy != null) {
                    subjectProxy.hello();
                }
            }
        }.start();
    }

    class SubjectHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            Printer.print("invoke...");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            method.invoke(subject, args);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            return null;
        }
    }
}
