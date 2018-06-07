package com.example.webwerks.neostore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SPManager {

    static SPManager myManager;
    static SharedPreferences s;

    public static SPManager getInstance(Context context) {
        if (myManager == null) {
            myManager = new SPManager(context);
        }

        return myManager;
    }


    public SPManager(Context context) {

        if (s == null) {
            s = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
    }

    public void saveString(String key, String data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putString(key, data);
        editor.commit();
    }

    public void saveInt(String key, int data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putInt(key, data);
        editor.commit();
    }

    public void saveBoolean(String key, boolean data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    public void delete(String key) {
        SharedPreferences.Editor editor = s.edit();
        //editor.putString(key, "null");
        editor.remove(key);
        //editor.clear();
        editor.commit();
    }

    public String retriveString(String key) {
        return s.getString(key, "");
    }

    public int retriveInt(String key, int i) {
        return s.getInt(key, 0);
    }

    public boolean retriveBool(String key) {
        return s.getBoolean(key, false);
    }

    public  synchronized final void saveObject(String key, Object model) {
        s.edit().putString(key, new Gson().toJson(model)).commit();
    }

    public  synchronized final Object retriveObject(String key,
                                                    Class<?> modelClass) {
        return new Gson().fromJson(s.getString(key, null), modelClass);
    }

}