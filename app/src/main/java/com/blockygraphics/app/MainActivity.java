package com.blockygraphics.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private Button applyBtn;
    private Button restoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.status_text);
        applyBtn = findViewById(R.id.btn_apply);
        restoreBtn = findViewById(R.id.btn_restore);

        checkCompatibility();

        applyBtn.setOnClickListener(v -> applySettings());
        restoreBtn.setOnClickListener(v -> restoreSettings());
    }

    private void checkCompatibility() {
        String version = OpenGLChecker.getVersion();
        boolean supported = OpenGLChecker.isSupported();

        if (supported) {
            statusText.setText("✅ OpenGL ES 3.0+ DETECTED\n\nVersion: " + version + "\n\n✅ DEVICE SUPPORTED");
            applyBtn.setEnabled(true);
        } else {
            statusText.setText("❌ UNSUPPORTED DEVICE\n\nOpenGL ES 3.0 or higher is required.\nYour device: " + version);
            applyBtn.setEnabled(false);
        }
    }

    private void applySettings() {
        boolean success = SettingsApplier.applyBlockySettings(this);
        if (success) {
            Toast.makeText(this, "✅ Blocky Graphics Settings Applied!\n\nRestart your game for changes.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "❌ Failed to apply settings", Toast.LENGTH_SHORT).show();
        }
    }

    private void restoreSettings() {
        boolean success = SettingsApplier.restoreDefaultSettings(this);
        if (success) {
            Toast.makeText(this, "✅ Restored to Default Settings", Toast.LENGTH_SHORT).show();
        }
    }
}
