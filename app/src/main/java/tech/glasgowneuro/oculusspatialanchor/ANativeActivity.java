package tech.glasgowneuro.oculusspatialanchor;

import tech.glasgowneuro.oculusspatialanchor.databinding.ActivityMainBinding;

public class ANativeActivity extends android.app.NativeActivity {
  static {
    System.loadLibrary("openxr_loader");
    System.loadLibrary("oculusspatialanchor");
  }
}
