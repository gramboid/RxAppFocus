package com.gramboid.rxappfocus;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Stub implementation of ActivityLifecycleCallbacks. Intended to be overridden.
 */
class DefaultActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override public void onActivityStarted(Activity activity) {
    }

    @Override public void onActivityResumed(Activity activity) {
    }

    @Override public void onActivityPaused(Activity activity) {
    }

    @Override public void onActivityStopped(Activity activity) {
    }

    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override public void onActivityDestroyed(Activity activity) {
    }
}
