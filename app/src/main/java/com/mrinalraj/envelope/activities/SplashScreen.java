package com.mrinalraj.envelope.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.services.Coordinates;
import com.mrinalraj.envelope.services.LocationService;
import com.mrinalraj.envelope.services.LocationValidate;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "Log";
    private ProgressBar pb;
    private TextView prgrstxt;
    private double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        main();
    }

    @Override
    protected void onResume() {
        main();
        super.onResume();
    }

    public void main() {
        final String[] progressText = {"Making some awesome moves.", "Firing up the engines", "Shooting upwards.", "Launching escape pods.", "Bringing you the latest", "Searching the hot topics."};
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setIndeterminate(true);
        prgrstxt = (TextView) findViewById(R.id.progressText);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int i = r.nextInt(6);
                        prgrstxt.setText(progressText[i]);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 2000);

        LocationService ls=new LocationService(this);
        if (ls.canGetLocation()){
            lat=ls.getLatitude();
            lon=ls.getLongitude();
            Toast.makeText(this, lat+"   "+lon, Toast.LENGTH_SHORT).show();
            LocationValidate validate=new LocationValidate();
            Coordinates boundry[]={new Coordinates(29.888018,77.956319), new Coordinates(29.888033,77.964051),new Coordinates(29.895144,77.961556),new Coordinates(29.893767,77.954024)};
            Coordinates found=new Coordinates(lat,lon);
            boolean isInside=validate.isInside(boundry,4,found);
            Toast.makeText(this, "you are inside "+isInside, Toast.LENGTH_SHORT).show();
        }
    }


    public class background extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
