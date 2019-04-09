package com.example.hellosensor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Context;
import android.widget.TextView;

public class Accelerometer extends Activity {
        TextView textX, textY, textZ;
        SensorManager sensorManager;
        Sensor sensor;
        private boolean woo = true;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_accelerometer);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            textX =  findViewById(R.id.textX);
            textY =  findViewById(R.id.textY);
            textZ =  findViewById(R.id.textZ);
        }

        public void onResume() {
            super.onResume();
            sensorManager.registerListener(accelListener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void onStop() {
            super.onStop();
            sensorManager.unregisterListener(accelListener);
        }

        SensorEventListener accelListener = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int acc) { }

            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                final MediaPlayer ring= MediaPlayer.create(Accelerometer.this ,R.raw.ring);

                textY.setText("Y : " + y);
                textZ.setText("Z : " + z);
                textX.setText("X : " + x);
                if (y >= 5) {
                    if(woo) {
                        ring.start();
                        woo = false;
                    } //kvar i intervallet? kommer inte låta mer. (likt compassen)
               }
                else {
                    woo = true;
                }
            }

            };
        }


