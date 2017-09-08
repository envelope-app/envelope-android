package com.mrinalraj.envelope.services;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context c;

    public PrefManager(Context c){
        this.c=c;
        pref=c.getSharedPreferences("first",0);
        editor=pref.edit();
    }
    public PrefManager(Context c,String prefName){
        this.c=c;
        pref=c.getSharedPreferences(prefName,0);
        editor=pref.edit();
    }

    public void add(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    public String get(String key){
        return pref.getString(key,"");
    }

    public boolean isFirst(){
        return pref.getBoolean("check",true);
    }

    public void setFalse(boolean falseVar){
        editor.putBoolean("check",false);
        editor.commit();
    }
}
