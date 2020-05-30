package com.cl.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by 任小龙 on 2020/5/29.
 */
public class Application1907 extends Application {
    private static Application1907 mApplication1907;
    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication1907 = this;
    }

    public static Application1907 getApplication(){
        return mApplication1907;
    }

    public static Context get07ApplicationContext(){
        return mApplicationContext.getApplicationContext();
    }
}
