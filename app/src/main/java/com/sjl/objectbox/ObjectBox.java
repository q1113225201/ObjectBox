package com.sjl.objectbox;

import android.app.Application;

import com.sjl.objectbox.model.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * ObjectBox
 *
 * @author 沈建林
 * @date 2019/4/19
 */
public class ObjectBox {
    private static BoxStore boxStore;

    public static void init(Application application) {
        boxStore = MyObjectBox.builder()
                .androidContext(application)
                .build();
    }

    public static BoxStore get() {
        return boxStore;
    }
}
