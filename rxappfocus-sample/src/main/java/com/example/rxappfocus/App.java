package com.example.rxappfocus;

import android.app.Application;
import android.widget.Toast;

import com.gramboid.rxappfocus.AppFocusProvider;

import io.reactivex.functions.Consumer;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // show a toast every time the app becomes visible or hidden
        AppFocusProvider.getInstance().getAppFocus2()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean visible) {
                        Toast.makeText(App.this, visible ? "App visible" : "App hidden", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
