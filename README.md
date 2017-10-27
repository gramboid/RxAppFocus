# RxAppFocus

[![Release](https://jitpack.io/v/com.gramboid/RxAppFocus.svg)](https://jitpack.io/#com.gramboid/RxAppFocus)

This is a tiny library for Android which makes it simple to monitor when your app becomes visible or hidden, in a cool RxJava way. 

Why would you want to do that?

Maybe you want to trigger a sync with a cloud service when your app is resumed.
Maybe you want to stop receiving location updates when your app goes into the background.

RxAppFocus is most useful in a non-Activity context: application startup, receiving broadcasts, responding to asynchronous task completion, etc. (After all, in an Activity, the lifecycle methods already tell you what you need to know.)

## Usage

See the [rxappfocus-sample](https://github.com/gramboid/RxAppFocus/tree/master/rxappfocus-sample) module for a working example.

### Visibility update stream

In your `Application.onCreate()`:
```kotlin
AppFocusProvider(this).getAppFocus().subscribe { visible: Boolean -> /* handle updated visibility */ }
```

### Quick visibility check

```kotlin
if (focusProvider.isVisible()) {
    // app is visible
} else {
    // app is not visible
}
```

### Visible activity

```kotlin
focusProvider.getVisibleActivity()?.let { activity -> /* do something with */ it }
```

## Download

Available on [jitpack.io](https://jitpack.io/#gramboid/RxAppFocus). If you don't already have it, add it to your top-level **build.gradle**:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then add the library dependency to your application **build.gradle**:

```gradle
compile 'com.gramboid.rxappfocus:rxappfocus:0.2.2'
```

## License

    Copyright 2016 Gramboid Mobile Systems Ltd

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
