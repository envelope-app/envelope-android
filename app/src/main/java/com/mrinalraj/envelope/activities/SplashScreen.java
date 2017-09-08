package com.mrinalraj.envelope.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.network.RegisterDevice;
import com.mrinalraj.envelope.services.Coordinates;
import com.mrinalraj.envelope.services.LocationService;
import com.mrinalraj.envelope.services.LocationValidate;
import com.mrinalraj.envelope.services.PrefManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "Log";
    private ProgressBar pb;
    private TextView prgrstxt;
    private double lat,lon;
    private LocationValidate locationValidate;
    private LocationService ls;
    private PrefManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //initialisations
        locationValidate=new LocationValidate();
        Coordinates boundry[]={new Coordinates(29.888018,77.956319), new Coordinates(29.888033,77.964051),new Coordinates(29.895144,77.961556),new Coordinates(29.893767,77.954024)};
        ls=new LocationService(this);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setIndeterminate(true);
        prgrstxt = (TextView) findViewById(R.id.progressText);
        prefs=new PrefManager(this,"devicekey");

        final String[] progressText = {
                "Making some awesome moves.",
                "Firing up the engines",
                "Shooting upwards.",
                "Launching escape pods.",
                "Bringing you the latest",
                "Searching the hot topics."
        };

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

        if (ls.canGetLocation()){
            lat=ls.getLatitude();
            lon=ls.getLongitude();
            if (validateLocation(boundry,new Coordinates(lat,lon))){
                Toast.makeText(this, "Good to go", Toast.LENGTH_SHORT).show();
            }
            else{
                String devid=GetDeviceID();
                RegisterDevice registerDevice=new RegisterDevice(this);
                registerDevice.execute(" ");
            }
        }
    }

    private String GetDeviceID(){
        String m_androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        return m_androidId;
    }

    private boolean validateLocation(Coordinates[] boundry,Coordinates location){
        return locationValidate.isInside(boundry,4,location);
    }

}
