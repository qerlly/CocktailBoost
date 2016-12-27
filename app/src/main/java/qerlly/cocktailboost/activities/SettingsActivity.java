package qerlly.cocktailboost.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import qerlly.cocktailboost.R;
import qerlly.cocktailboost.fragments.PrefFragment;
import qerlly.cocktailboost.utils.ThemeSwitcher;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeSwitcher().onSwitch(this);
        setContentView(R.layout.settings_activity);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PrefFragment()).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout mLinearLayout = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        android.support.v7.widget.Toolbar mToolbar = (android.support.v7.widget.Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar, mLinearLayout, false);
        mToolbar.setTitle(R.string.settings);
        mToolbar.setLogo(R.drawable.cocktail_sett);
        mLinearLayout.addView(mToolbar, 0);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}