package com.example.week14_3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SensorManager smanager = null;
    TextView textView1 = null;
    Sensor sensorAccel;

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText(" X축 : " + values[0] + "\n Y축 : " + values[1] + "\n Z축 : " + values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView1 = (TextView) findViewById(R.id.textView2);

        sensorAccel = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensorAccel == null) {
            Toast.makeText(getBaseContext(), "가속도계가 없음", Toast.LENGTH_LONG).show();
        }
        else {
            smanager.registerListener(listener, (Sensor) sensorAccel, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        smanager.unregisterListener(listener);
    }
}