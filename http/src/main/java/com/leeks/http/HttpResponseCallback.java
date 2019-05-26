package com.leeks.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by herr.wang on 2017/6/9.
 */

public abstract class HttpResponseCallback<T> {

    private Type type;

    public HttpResponseCallback(){
        type = getType(getClass());
    }

    private Type getType(Class<?> clazz){
        Type superType = clazz.getGenericSuperclass();
        if(superType instanceof Class){
            throw new IllegalArgumentException("please check type.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superType;
        return parameterizedType.getActualTypeArguments()[0];
    }

    public abstract void onResponse(T response);
    public abstract void onFail();
}
