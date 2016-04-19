package com.example.rxappvisibility;

import android.app.Application;
import android.widget.Toast;

import com.gramboid.rxappvisibility.AppVisibilityProvider;

import rx.functions.Action1;

public class App extends Application {

    private AppVisibilityProvider visibilityProvider;

    AppVisibilityProvider getVisibilityProvider() {
        return visibilityProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        visibilityProvider = new AppVisibilityProvider(this);
        visibilityProvider
                .getAppVisibility()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean visible) {
                        Toast.makeText(App.this, visible ? "App visible" : "App hidden", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
