package com.gramboid.rxappfocus;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Provides Observables to monitor app visibility.
 */
public class AppFocusProvider {

    private boolean changingConfig;
    private int foregroundCounter;
    private Activity visibleActivity;

    private final rx.subjects.BehaviorSubject<Boolean> subjectV1;
    private final io.reactivex.subjects.BehaviorSubject<Boolean> subjectV2;

    private final ActivityLifecycleCallbacks callbacks = new DefaultActivityLifecycleCallbacks() {

        @Override
        public void onActivityStarted(Activity activity) {
            visibleActivity = activity;
            if (changingConfig) {
                // ignore activity start, just a config change
                changingConfig = false;
            } else {
                final boolean justBecomingVisible = !isVisible();
                foregroundCounter++;
                if (justBecomingVisible) {
                    publishState(true);
                }
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                // ignore activity stop, just a config change
                changingConfig = true;
            } else {
                foregroundCounter--;
                if (!isVisible()) {
                    publishState(false);
                    visibleActivity = null;
                }
            }
        }

    };

    private void publishState(boolean visible) {
        if (subjectV1 != null) subjectV1.onNext(visible);
        if (subjectV2 != null) subjectV2.onNext(visible);
    }

    private boolean rxJava1Available() {
        try {
            Class.forName("rx.Observable");
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    private boolean rxJava2Available() {
        try {
            Class.forName("io.reactivex.Observable");
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public AppFocusProvider(@NonNull Application app) {
        app.registerActivityLifecycleCallbacks(callbacks);

        subjectV1 = rxJava1Available() ? rx.subjects.BehaviorSubject.<Boolean>create() : null;
        subjectV2 = rxJava2Available() ? io.reactivex.subjects.BehaviorSubject.<Boolean>create() : null;
    }

    /**
     * Returns an RxJava 1 Observable that emits a Boolean indicating whether the app is currently visible, and again each time the app's visibility changes.
     */
    public rx.Observable<Boolean> getAppFocus() {
        return subjectV1;
    }

    /**
     * Returns an RxJava 2 Observable that emits a Boolean indicating whether the app is currently visible, and again each time the app's visibility changes.
     */
    public io.reactivex.Observable<Boolean> getAppFocus2() {
        return subjectV2;
    }

    /**
     * Returns true if the app is currently visible, or false if not.
     */
    public boolean isVisible() {
        return foregroundCounter > 0;
    }

    /**
     * Returns the currently visible Activity, or null if none of the app's activities is currently visible.
     */
    @Nullable
    public Activity getVisibleActivity() {
        return visibleActivity;
    }

}