package com.gramboid.rxappvisibility;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Provides Observables to monitor app visibility.
 */
public class AppVisibilityProvider {

    private boolean changingConfig;
    private int     foregroundCounter;

    private final PublishSubject<Boolean> subject = PublishSubject.create();

    private final ActivityLifecycleCallbacks callbacks = new DefaultActivityLifecycleCallbacks() {

        @Override
        public void onActivityStarted(Activity activity) {
            if (changingConfig) {
                // ignore activity start, just a config change
                changingConfig = false;
            } else {
                incrementVisibleCounter();
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                // ignore activity stop, just a config change
                changingConfig = true;
            } else {
                decrementVisibleCounter();
            }
        }

    };

    private void incrementVisibleCounter() {
        final boolean justBecomingVisible = !isVisible();
        foregroundCounter++;
        if (justBecomingVisible) {
            subject.onNext(true);
        }
    }

    private void decrementVisibleCounter() {
        foregroundCounter--;
        if (!isVisible()) {
            subject.onNext(false);
        }
    }

    public AppVisibilityProvider(Application app) {
        app.registerActivityLifecycleCallbacks(callbacks);
    }

    public boolean isVisible() {
        return foregroundCounter > 0;
    }

    public Observable<Boolean> getAppVisibility() {
        return subject;
    }

}