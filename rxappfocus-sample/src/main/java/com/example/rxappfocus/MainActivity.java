package com.example.rxappfocus;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gramboid.rxappfocus.AppFocusProvider;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;


public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_change_activity).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        disposable = AppFocusProvider.getInstance().getAppFocus2()
                .filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean visible) throws Exception {
                        return visible;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean visible) throws Exception {
                        Log.d("MainActivity", "We are visible!");
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
