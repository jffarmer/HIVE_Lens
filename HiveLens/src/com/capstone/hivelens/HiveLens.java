package com.capstone.hivelens;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HiveLens extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Button toAccel;
    private Button gyroToast;
    private TextView xCoord;
    private TextView yCoord;
    private TextView zCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_lens);
        toAccel = (Button) findViewById(R.id.goToAccel);
        gyroToast = (Button) findViewById(R.id.gyroToast);
        xCoord = (TextView) findViewById(R.id.xCoord);
        yCoord = (TextView) findViewById(R.id.yCoord);
        zCoord = (TextView) findViewById(R.id.zCoord);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener((SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_hive_lens, menu);
        return true;
    }

    public void goToAccelInfo(View v)
    {
        Intent toAccel = new Intent(this, HiveLens2.class);
        startActivity(toAccel);
    }

    public void gyroToast(View v)
    {
        Toast aToast = Toast.makeText(this,
                "The Above Data is the Phone's Gyroscope Data in Real Time.",
                Toast.LENGTH_LONG);
        aToast.show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            xCoord.setText("X: " + x);
            yCoord.setText("Y: " + y);
            zCoord.setText("Z: " + z);
        }
    }
}
