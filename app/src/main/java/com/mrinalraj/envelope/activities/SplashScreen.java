package com.mrinalraj.envelope.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.intro.Intromanager;
import com.mrinalraj.envelope.intro.WalkthroughIntro;

public class SplashScreen extends AppCompatActivity {
    private Intromanager introman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        introman=new Intromanager(this);
        if(introman.isFirst()){
            introman.setFalse(false);
            startActivity(new Intent(this,WalkthroughIntro.class));
        }
    }
}
