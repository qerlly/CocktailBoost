package qerlly.cocktailboost.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import qerlly.cocktailboost.R;

public class ThemeSwitcher {
   public void onSwitch(Activity activity) {
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
       boolean Theme1= preferences.getBoolean("Theme1", false);
       boolean Theme2= preferences.getBoolean("Theme2", false);
       boolean Theme3= preferences.getBoolean("Theme3", false);

       if(Theme1) {
           activity.setTheme(R.style.AppTheme);
       }
       else if(Theme2) {
           activity.setTheme(R.style.BrownTheme);
       }
       else if(Theme3) {
           activity.setTheme(R.style.RedTheme);
       }
    }
}
