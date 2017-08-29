package com.mrinalraj.envelope.intro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mrinal on 8/28/2017.
 */

public class Intromanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context c;

    public Intromanager(Context c){
        this.c=c;
        pref=c.getSharedPreferences("first",0);
        editor=pref.edit();
    }

    public boolean isFirst(){
        return pref.getBoolean("check",true);
    }

    public void setFalse(boolean falseVar){
        editor.putBoolean("check",false);
        editor.commit();
    }
}
