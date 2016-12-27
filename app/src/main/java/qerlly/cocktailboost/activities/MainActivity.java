package qerlly.cocktailboost.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;

import qerlly.cocktailboost.R;
import qerlly.cocktailboost.utils.ThemeSwitcher;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeSwitcher().onSwitch(this);
        setContentView(R.layout.menu_activity);


        findViewById(R.id.button_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1f, 0.6f));
                startActivity(new Intent(MainActivity.this, AllCocktailActivity.class));
            }
        });

        findViewById(R.id.button_favorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1f, 0.6f));
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
            }
        });

        findViewById(R.id.button_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1f, 0.6f));
                startActivity(new Intent(MainActivity.this, RandomCocktailActivity.class));
            }
        });

        findViewById(R.id.button_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1f, 0.6f));
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        findViewById(R.id.button_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(new AlphaAnimation(1f, 0.6f));
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Bundle mBundle = new Bundle();
        onSaveInstanceState(mBundle);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("bundle", mBundle);
        startActivity(intent);
        finish();
    }
}