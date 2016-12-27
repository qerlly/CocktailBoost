package qerlly.cocktailboost.threads;

import android.app.Activity;

public interface IProgress<Progress> {
    void onProgressUpdate (Integer progress, Activity activity);
}
