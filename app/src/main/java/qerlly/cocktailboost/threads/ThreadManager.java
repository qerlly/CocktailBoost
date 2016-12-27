package qerlly.cocktailboost.threads;

import android.app.Activity;
import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {

   public static final int COUNT_CORE = 3;
   public final ExecutorService executor;
   public final Handler handler;
   public Activity activity;

    public ThreadManager() {
        this.executor = Executors.newFixedThreadPool(COUNT_CORE);
        this.handler = new Handler();
    }

    void link(Activity activity) {
        this.activity = activity;
    }

    void unlink() {
        this.activity = null;
    }

    <Params, Progress, Result> void execute(final IBackground<Params, Progress, Result> task, final Params params, final IProgress<Progress> progress, final IResult<Result> result) {
        if (this.activity == null) {
            throw new IllegalStateException();
        }
        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    final Result res = task.doInBackground(params, new IProgress<Progress>() {
                        @Override
                        public void onProgressUpdate(final Integer progress_, final Activity activity_) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progress.onProgressUpdate(progress_, activity);
                                }
                            });
                        }
                    });
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            result.onSuccess(res, activity);
                        }
                    });
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            result.onError(e, activity);
                        }
                    });
                }
            }
        });
    }


}