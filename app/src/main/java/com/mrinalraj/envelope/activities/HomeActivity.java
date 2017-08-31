package com.mrinalraj.envelope.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mrinalraj.envelope.R;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        b=getIntent().getExtras();
        TextView tv=(TextView)findViewById(R.id.location);
        tv.setText("latitude : "+b.getString("lat")+"\n"+"longitude : "+b.getString("lon"));
    }
}
