package com.example.doubaomini;

import android.app.Application;

import com.example.doubaomini.utils.ContextUtil;

public class DouBaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.setContext(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleObserver());
    }
}
