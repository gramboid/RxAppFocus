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

        // show a toast every time the app becomes visible or hidden
        focusProvider.getAppFocus()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean visible) {
                        Toast.makeText(App.this, visible ? "App visible" : "App hidden", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public AppFocusProvider getFocusProvider() {
        return focusProvider;
    }
}
