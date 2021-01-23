package com.example.agendamu.utilities;


import androidx.appcompat.app.AppCompatActivity;

import com.example.agendamu.MainActivity;

public class ActivityHelper {
    private static MainActivity mainActivity;

    public static void setMainActivity(MainActivity mainActivity){
        ActivityHelper.mainActivity = mainActivity;
    }

    public static MainActivity getMainActivity(){
        return ActivityHelper.mainActivity;
    }

    public static void setActionBarTitle(String title){
        mainActivity.getSupportActionBar().setTitle(title);
    }
}
