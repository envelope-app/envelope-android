package com.mrinalraj.envelope.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.mrinalraj.envelope.PermissionAccess;
import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.activities.SplashScreen;
import com.mrinalraj.envelope.services.PrefManager;

public class WalkthroughIntro extends AppIntro {

    PrefManager introman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introman=new PrefManager(this);
        if(!introman.isFirst()){
            startActivity(new Intent(this, SplashScreen.class));
            finish();
        }
        addSlide(IntroSlide.newInstance(R.layout.screen0));
        addSlide(IntroSlide.newInstance(R.layout.screen1));
        addSlide(IntroSlide.newInstance(R.layout.screen2));
        addSlide(IntroSlide.newInstance(R.layout.screen3));
        addSlide(IntroSlide.newInstance(R.layout.screen4));
        setFadeAnimation();
        new PermissionAccess().invoke(this);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        introman.setFalse(false);
        startActivity(new Intent(this,SplashScreen.class));
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        introman.setFalse(false);
        startActivity(new Intent(this,SplashScreen.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

}
