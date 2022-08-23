# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("oculusecg")

# set it to the oculus API
set(OCULUS_OPENXR_MOBILE_SDK /home/bp1/sandbox/ovr_openxr_mobile_sdk)

set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -u ANativeActivity_onCreate")

add_library(openxr_loader SHARED IMPORTED)
set_property(
        TARGET
        openxr_loader
        PROPERTY
        IMPORTED_LOCATION
        ${OCULUS_OPENXR_MOBILE_SDK}/OpenXR/Libs/Android/${ANDROID_ABI}/${CMAKE_BUILD_TYPE}/libopenxr_loader.so
)

include_directories(
        ${OCULUS_OPENXR_MOBILE_SDK}/3rdParty/khronos/openxr/OpenXR-SDK/include
        ${OCULUS_OPENXR_MOBILE_SDK}/1stParty/OVR/Include/
        ${OCULUS_OPENXR_MOBILE_SDK}/OpenXR/Include
        ${ANDROID_NDK}/sources/android/native_app_glue/
)
set(CODEGEN_FOLDER ../app/.cxx/openxr)
add_subdirectory(${OCULUS_OPENXR_MOBILE_SDK}/3rdParty/khronos/openxr/OpenXR-SDK/include ${CODEGEN_FOLDER})

add_library(native_app_glue STATIC
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c)

add_library( # Sets the name of the library.
        oculusecg

        # Sets the library as a shared library.
        SHARED

        SpatialAnchorGl.cpp
        SimpleXrInput.cpp
        SpatialAnchorXr.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

# set (CMAKE_CXX_LINK_LIBRARY_FLAG "-u ANativeActivity_onCreate")

target_link_libraries( # Specifies the target library.
        oculusecg

        openxr_loader
        nativewindow
        android
        native_app_glue
        GLESv3
        EGL
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})