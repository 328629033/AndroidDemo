package com.leeks.http;

import okhttp3.OkHttpClient;

/**
 * Created by herr.wang on 2017/6/9.
 */

public class HttpClient {

    private static OkHttpClient okHttpClient;

    private static final class Singleton{
        private static final HttpClient INSTANCE = new HttpClient();
    }

    public static HttpClient getInstance(){
        return Singleton.INSTANCE;
    }

}
