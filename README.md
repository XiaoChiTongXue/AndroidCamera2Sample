
AndroidCamera2Sample
===================================

 gooogleCamera2demohttps://github.com/googlesamples/android-Camera2Basic,demo
 
 

Introduction
------------

The [Camera2 API][1] provides an interface to individual camera
devices connected to an Android device. It replaces the deprecated
Camera class.

Use [getCameraIdList][2] to get a list of all the available
cameras. You can then use [getCameraCharacteristics][3] and find the
best camera that suits your need (front/rear facing, resolution etc).

Create an instance of [CameraDevice.StateCallback][4] and open a
camera. It is ready to start camera preview when the camera is opened.

This sample uses TextureView to show the camera preview. Create a
[CameraCaptureSession][5] and set a repeating [CaptureRequest][6] to it.

Still image capture takes several steps. First, you need to lock the
focus of the camera by updating the CaptureRequest for the camera
preview. Then, in a similar way, you need to run a precapture
sequence. After that, it is ready to capture a picture. Create a new
CaptureRequest and call [capture][7]. Don't forget to unlock the focus
when you are done.

[1]: https://developer.android.com/reference/android/hardware/camera2/package-summary.html
[2]: https://developer.android.com/reference/android/hardware/camera2/CameraManager.html#getCameraIdList()
[3]: https://developer.android.com/reference/android/hardware/camera2/CameraManager.html#getCameraCharacteristics(java.lang.String)
[4]: https://developer.android.com/reference/android/hardware/camera2/CameraDevice.StateCallback.html
[5]: https://developer.android.com/reference/android/hardware/camera2/CameraCaptureSession.html
[6]: https://developer.android.com/reference/android/hardware/camera2/CaptureRequest.html
[7]: https://developer.android.com/reference/android/hardware/camera2/CameraCaptureSession.html#capture(android.hardware.camera2.CaptureRequest, android.hardware.camera2.CameraCaptureSession.CaptureCallback, android.os.Handler)

Pre-requisites
--------------

- Android SDK 27
- Android Build Tools v27.0.2
- Android Support Repository

Screenshots
-------------

<img src="screenshots/main.png" height="400" alt="Screenshot"/> 

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.

Support
-------


License
-------

Copyright 2017 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
