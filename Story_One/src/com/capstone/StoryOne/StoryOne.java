package com.capstone.StoryOne;

import java.text.DecimalFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StoryOne extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private RelativeLayout rl;
    private Button gyroToast;
    private Button reset;
    private DecimalFormat dec;
    private TextView xCoord;
    private TextView yCoord;
    private TextView zCoord;
    private TextView xOverOne;
    private TextView yOverOne;
    private TextView zOverOne;
    private MediaPlayer mp;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_one);
        rl = (RelativeLayout) findViewById(R.id.layout);
        gyroToast = (Button) findViewById(R.id.gyroToast);
        reset = (Button) findViewById(R.id.reset);
        xCoord = (TextView) findViewById(R.id.xCoord);
        yCoord = (TextView) findViewById(R.id.yCoord);
        zCoord = (TextView) findViewById(R.id.zCoord);
        xOverOne = (TextView) findViewById(R.id.xOverOne);
        yOverOne = (TextView) findViewById(R.id.yOverOne);
        zOverOne = (TextView) findViewById(R.id.zOverOne);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener((SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        dec = new DecimalFormat("#.###");
        gyroToast.setText("Info");
        reset.setText("Reset Above Data");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_story_one, menu);
        return true;
    }

    public void gyroToast(View v)
    {
        Toast.makeText(
                this,
                "The Above Data is the Phone's Accelerometer Data in Real Time.",
                Toast.LENGTH_LONG).show();
    }

    public void resetClicked(View v)
    {
        xOverOne.setText("Rotation around X axis is less than one rad/s");
        yOverOne.setText("Rotation around Y axis is less than one rad/s");
        zOverOne.setText("Rotation around Z axis is less than one rad/s");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            xCoord.setText("Acceleration around X is: " + dec.format(x)
                    + " m/s²");
            yCoord.setText("Acceleration around Y is: " + dec.format(y)
                    + " m/s²");
            zCoord.setText("Acceleration around Z is: " + dec.format(z)
                    + " m/s²");
            counter++;
            if (x > 8.0f)
            {
                rl.setBackgroundResource(R.drawable.topdown);
                if (counter > 10)
                {
                    counter = 0;
                    mp = MediaPlayer.create(this, R.raw.button8);
                    mp.start();
                }
            }
            if(x < -8.f)
            {
                rl.setBackgroundResource(R.drawable.bottomup);
                if (counter > 10)
                {
                    counter = 0;
                    mp = MediaPlayer.create(this, R.raw.button2);
                    mp.start();
                }
            }
            if (y < -8.f && x < 1.f)
            {
                rl.setBackgroundResource(R.drawable.upsidedown);
                if (counter > 10)
                {
                    counter = 0;
                    mp = MediaPlayer.create(this, R.raw.button5);
                    mp.start();
                }
            }
            if (z > 8.f || y > 8.f)
            {
                rl.setBackgroundResource(R.drawable.rightsideup);
                if (counter > 10)
                {
                    counter = 0;
                    mp = MediaPlayer.create(this, R.raw.button6);
                    mp.start();
                }
            }
        }
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (event.values[0] > 1)
            {
                xOverOne.setText("Rotation around X was just at: "
                        + dec.format(x) + " rad/s");
            }
            if (event.values[1] > 1)
            {
                yOverOne.setText("Rotation around Y was just at: "
                        + dec.format(y) + " rad/s");
            }
            if (event.values[2] > 1)
            {
                zOverOne.setText("Rotation around Z was just at: "
                        + dec.format(z) + " rad/s");
            }
        }
    }
    
    @Override
    protected void onDestroy()
    {
        mp.release();
        super.onDestroy();
    }
    
    @Override
    protected void onStop()
    {
        mp.release();
        sensorManager.unregisterListener((SensorEventListener)this);
        super.onStop();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            mp.release();
            sensorManager.unregisterListener((SensorEventListener)this);
        }
        return super.onKeyDown(keyCode, event);
    }
}
