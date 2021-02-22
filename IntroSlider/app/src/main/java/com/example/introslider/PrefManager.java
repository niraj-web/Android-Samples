package com.example.introslider;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Welcome";

    private static final String Is_First_Time_Launch = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public void setIs_First_Time_Launch(boolean isFirstTimeLaunch) {
        editor.putBoolean(Is_First_Time_Launch, isFirstTimeLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLaunch()
    {
        return pref.getBoolean(Is_First_Time_Launch,true);
    }
}


