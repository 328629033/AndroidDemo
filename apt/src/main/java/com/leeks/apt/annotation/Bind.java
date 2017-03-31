package com.leeks.apt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by herr.wang on 2017/3/28.
 */
@Target(ElementType.FIELD)
public @interface Bind {
    int id();
    String name() default "hello";
}
