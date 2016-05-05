package com.gramboid.rxappfocus;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;

import rx.Observable;
import rx.subjects.ReplaySubject;

/**
 * Provides Observables to monitor app visibility.
 */
public class AppFocusProvider {

    private boolean  changingConfig;
    private int      foregroundCounter;
    private Activity visibleActivity;

    private final ReplaySubject<Boolean>  appFocusSubject        = ReplaySubject.createWithSize(1);
    private final ReplaySubject<Activity> visibleActivitySubject = ReplaySubject.createWithSize(1);

    private final ActivityLifecycleCallbacks callbacks = new DefaultActivityLifecycleCallbacks() {

        @Override
        public void onActivityStarted(Activity activity) {
            if (changingConfig) {
                // ignore activity start, just a config change
                changingConfig = false;
            } else {
                final boolean justBecomingVisible = !isVisible();
                foregroundCounter++;
                visibleActivity = activity;
                if (justBecomingVisible) {
                    appFocusSubject.onNext(true);
                    visibleActivitySubject.onNext(activity);
                }
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                // ignore activity stop, just a config change
                changingConfig = true;
            } else {
                visibleActivity = null;
                foregroundCounter--;
                if (!isVisible()) {
                    appFocusSubject.onNext(false);
                }
            }
        }

    };

    public AppFocusProvider(Application app) {
        app.registerActivityLifecycleCallbacks(callbacks);
    }

    public boolean isVisible() {
        return foregroundCounter > 0;
    }

    public Observable<Boolean> getAppFocus() {
        return appFocusSubject;
    }

    public Observable<Activity> getVisibleActivity() {
        return visibleActivitySubject;
    }

}