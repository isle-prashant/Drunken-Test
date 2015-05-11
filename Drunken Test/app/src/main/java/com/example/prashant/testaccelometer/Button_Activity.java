package com.example.prashant.testaccelometer;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by PRASHANT on 15-04-2015.
 */
public class Button_Activity extends Activity implements View.OnClickListener {
    ImageButton bt1;
    int counter=0;
    TextView tv;
    TextView tv1,tv2;
    double sum=0;
    double avg;
    Button bt,retest;
    ProgressBar pgb,pgb1;
    Sensor acc;
    SensorManager sm;
    int sensorCount=0;
    int progress;
    Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragment);
            // bt1= (ImageButton) findViewById(R.id.red);
        tv= (TextView) findViewById(R.id.dis);
        bt= (Button) findViewById(R.id.test);
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        tb= (Toolbar) findViewById(R.id.toolbar1);
      /*  if (tb!=null) {
            tb.setTitle("HHHHHHHHHhhhhhhhhhhhh");
            setSupportActionBar(tb);}*/

        if (counter==0){
            int screenWidth = this.getResources().getDisplayMetrics().widthPixels;
            int screenHeight = this.getResources().getDisplayMetrics().heightPixels;
            int width = (int) (Math.random()*(screenWidth/1.5));
            int height = (int) (Math.random()*(screenHeight/1.5));
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,56,this.getResources().getDisplayMetrics());
            if (height<=px)
                height=height+px;
            RelativeLayout.LayoutParams params1 = new
                    RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params1.setMargins(width, height,0,0);
            bt.setLayoutParams(params1);
        }
        new AnimAsync().execute();
//        tv.setText("Catch me if you can");


        bt.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        counter+=1;
        System.out.println(counter);
        //tv.setText("" + counter);
        ChangePosition();


    }

    public void ChangePosition(){
        int screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        int width = (int) (Math.random()*(screenWidth/1.5));
        int height = (int) (Math.random()*(screenHeight/1.5));
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,56,this.getResources().getDisplayMetrics());
        if (height<=px)
            height=height+px;
        RelativeLayout.LayoutParams params1 = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(width, height,0,0);
        bt.setLayoutParams(params1);

    }

    class AnimAsync extends AsyncTask<Void,Void,Void> implements SensorEventListener{


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            bt.setVisibility(View.INVISIBLE);
            setContentView(R.layout.fragment_end);
            tv1= (TextView) findViewById(R.id.endtext);
            tv2= (TextView) findViewById(R.id.textView2);
            retest= (Button) findViewById(R.id.retest);
            double c = (MainActivity.c)*10;
            avg=  ((sum/sensorCount)*10);
            c=c-avg;
            if (c<0)
                c=c*(-1);
            tv1.setText(" Shake Result:\n Shake Percent\t "+(int)c+"%");
//            tv1.setText(""+avg);
            pgb= (ProgressBar) findViewById(R.id.progressBar);
            pgb1= (ProgressBar) findViewById(R.id.progressBar2);
            tv2.setText(" Catch Result:\n "+counter);
//            tv2.setText(""+Math.abs(-4)+"\n"+Math.abs(4)+"\n"+(Math.abs(-4.422)+Math.abs(4.422)));

            pgb1.setMax(13);
            pgb1.setProgress(counter);
            pgb.setProgress((int)c);
        retest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum=0;
                avg=0;
                sensorCount=0;
                finish();
            }
        });}

        @Override
        protected Void doInBackground(Void... params) {
            sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
           // tv.setText("X:"+ event.values[0]*10+"\nY: "+event.values[1]*10+"\nZ:"+event.values[2]*10 + "\n "+(event.values[0]*10+event.values[1]*10+event.values[2]*10));
            sensorCount++;
            //sum+= Math.abs(event.values[0])+Math.abs(event.values[1]);
            //sum+= event.values[0]+event.values[1]+event.values[2];
          //sum+=   (Math.sqrt(Math.pow(event.values[0], 2)) + Math.sqrt(Math.pow(event.values[1], 2)) + Math.sqrt(Math.pow(event.values[2], 2)));
           sum+= event.values[2]+event.values[1]+Math.abs(event.values[0]);
            System.out.println(sensorCount);
            //tv.setText(""+event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sum=0;
            avg=0;
            sensorCount=0;
        }

    }

    @Override
    protected void onDestroy() {
        sum=0;
        avg=0;
        sensorCount=0;
        super.onDestroy();

    }
}
