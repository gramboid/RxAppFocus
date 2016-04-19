package com.example.rxappvisibility;

import android.app.Application;
import android.util.Log;

import com.gramboid.rxappvisibility.RxAppVisibility;

import rx.functions.Action1;

/**
 * Created by graham on 19/04/16.
 */
public class App extends Application {

    private static final String TAG = "RxAppVisibility";

    @Override public void onCreate() {
        super.onCreate();
        new RxAppVisibility(this)
                .getAppVisibility()
                .subscribe(new Action1<Boolean>() {
                    @Override public void call(Boolean visible) {
                        Log.d(TAG, "App visible? " + visible);
                    }
                });
    }
}
