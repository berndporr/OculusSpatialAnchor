# OculusSpatialAnchor

Oculus Quest 2 demo `XrSpatialAnchor` but with cmake
as a build system and separated from the API.

# Prerequisites

- Install the Oculus openXR API.
- Edit `app/src/main/cpp/CMakeLists.txt` and point it to the openXR API:
  `set(OCULUS_OPENXR_MOBILE_SDK /home/bernd/ovr_openxr_mobile_sdk)`

## How to run

Start Android Studio and click `run`.
