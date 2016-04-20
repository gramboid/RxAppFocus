# RxAppFocus

This is a tiny library which makes it simple to monitor when your app becomes visible or hidden.

## Usage

In your `Application.onCreate()`:
```java
AppFocusProvider focusProvider = new AppFocusProvider(this);
focusProvider.getAppFocus()
    .subscribe(new Action1<Boolean>() {
        @Override
        public void call(Boolean visible) {
            if (visible) {
                // app is now visible
            } else {
                // app is no longer visible
            }
        }
    });
```

See the [rxappfocus-sample](https://github.com/gramboid/RxAppFocus/tree/master/rxappfocus-sample) module for a working example.
