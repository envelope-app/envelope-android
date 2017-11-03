package com.mrinalraj.envelope.network;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mrinalraj.envelope.activities.HomeActivity;
import com.mrinalraj.envelope.activities.SplashScreen;
import com.mrinalraj.envelope.services.PrefManager;

import org.json.JSONObject;

/**
 * Created by mrinal on 9/7/2017.
 */

public class RegisterDevice extends AsyncTask<String,String,JSONObject> {

    private String dev_id,hash,err;
    private JSONObject reply;
    private Context c;

    public RegisterDevice(Context c){
        this.c=c;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONCustom jsonCustom=new JSONCustom();
        dev_id=params[0];
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*try{
            reply=jsonCustom.getJSONObjectFromURL("http://envelope.mrinalraj.com","GET");
            if (hash!=null){
                err=reply.optString("error");
                if (err.equals("0")){
                    hash=reply.optString("hash");
                }
                else{

                }
            }
        }
        catch(Exception e){
            Log.e("Exception caught",e.toString());
        }*/
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        /*PrefManager prefMan=new PrefManager(c,"devicekey");
        if (hash!=null){
            prefMan.add(dev_id,hash);
            Intent home=new Intent(c, HomeActivity.class);
            c.startActivity(home);
        }
        */
        c.startActivity(new Intent(c,HomeActivity.class));
        ((AppCompatActivity)c).finish();
        super.onPostExecute(jsonObject);
    }
}
