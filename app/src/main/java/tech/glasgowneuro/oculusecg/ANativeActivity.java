package tech.glasgowneuro.oculusecg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import tech.glasgowneuro.oculusecg.databinding.ActivityMainBinding;

public class ANativeActivity extends android.app.NativeActivity {
  static {
    System.loadLibrary("openxr_loader");
    System.loadLibrary("oculusecg");
  }
}
