package com.example.prashant.testaccelometer;


import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements SensorEventListener{
    private static Toolbar toolbar;
    Sensor acc;
    SensorManager sm;
    TextView tv;
    ProgressBar progressBar;
    Button bt;
    double calib = 0;
    public static double c;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       tv= (TextView) findViewById(R.id.textView);

        toolbar= (Toolbar) findViewById(R.id.toolbar1);
        bt = (Button) findViewById(R.id.button);
        if (toolbar!=null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
            toolbar.setBackgroundColor(Color.parseColor("#e74c3c"));
              

        }
        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=calib;

             startActivity(new Intent("com.prashant.Test"));
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       // tv.setText("X:"+ event.values[0]*10+"\nY: "+event.values[1]*10+"\nZ:"+event.values[2]*10 + "\n "+(event.values[0]*10+event.values[1]*10+event.values[2]*10));
        //progressBar.incrementProgressBy((int) (event.values[0]+event.values[1]+event.values[2]));
       // calib=  (Math.abs(event.values[0])+Math.abs(event.values[1])+Math.abs(event.values[2]));
       // calib = event.values[0]+event.values[1]+event.values[2];
        calib= event.values[2]+event.values[1]+Math.abs(event.values[0]);
//        tv.setText(""+calib);
       counter++;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
