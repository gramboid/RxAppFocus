# RxAppFocus

This is a tiny library which makes it simple to monitor when your app becomes visible or hidden, in a cool RxJava way.

Why would you want to do that?

Maybe you want to trigger a sync with a cloud service when your app is resumed.
Maybe you want to stop receiving location updates when your app goes into the background.

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
    
