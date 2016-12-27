package qerlly.cocktailboost.activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import java.util.ArrayList;

import qerlly.cocktailboost.R;
import qerlly.cocktailboost.adapter.Item;
import qerlly.cocktailboost.db.DatabaseHelper;
import qerlly.cocktailboost.utils.ShakerShaker;
import qerlly.cocktailboost.utils.ThemeSwitcher;

public class RandomCocktailActivity extends AppCompatActivity {

    TextView mNameText;
    TextView mIngredientsText;
    TextView mRecipeText;

    private DatabaseHelper mDBHelper;
    private Cursor mCursor;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakerShaker mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeSwitcher().onSwitch(this);
        setContentView(R.layout.random_activity);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.random_cocktail);
        mToolbar.setLogo(R.drawable.cocktail_rand);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNameText = (TextView) findViewById(R.id.name_random);
        mIngredientsText = (TextView) findViewById(R.id.ingredients_random);
        mRecipeText = (TextView) findViewById(R.id.recipe_random);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakerShaker();
        mShakeDetector.setOnShakeListener(new ShakerShaker.OnShakeListener() {

            @Override
            public void onShake(int count) {
                randomCocktail();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void randomCocktail() {
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.checkAndCopyDB();
            mDBHelper.openDB();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            mCursor = mDBHelper.QueryData("select * from " + DatabaseHelper.TABLE + " order by random() limit 1");
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    do {
                        mNameText.setText(mCursor.getString(1));
                        mIngredientsText.setText(mCursor.getString(2));
                        mRecipeText.setText(mCursor.getString(3));
                    } while (mCursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

}



