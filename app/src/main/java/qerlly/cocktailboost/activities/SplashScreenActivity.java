package qerlly.cocktailboost.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
                Thread.sleep(1500);
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
