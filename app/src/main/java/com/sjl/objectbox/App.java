package com.sjl.objectbox;

import android.app.Application;

/**
 * App
 *
 * @author 沈建林
 * @date 2019/4/19
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
