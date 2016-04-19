package com.example.rxappfocus;

import android.app.Application;
import android.widget.Toast;

import com.gramboid.rxappfocus.AppFocusProvider;

import rx.functions.Action1;

public class App extends Application {

    private AppFocusProvider focusProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        focusProvider = new AppFocusProvider(this);
        focusProvider
                .getAppFocus()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean visible) {
                        Toast.makeText(App.this, visible ? "App visible" : "App hidden", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
