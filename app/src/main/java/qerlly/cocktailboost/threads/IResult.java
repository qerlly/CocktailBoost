package qerlly.cocktailboost.threads;

import android.app.Activity;

public interface IResult<Result> {
    void onSuccess(Result result, Activity activity);

    void onError(Exception exception, Activity activity);
}