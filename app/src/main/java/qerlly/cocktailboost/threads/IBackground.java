package qerlly.cocktailboost.threads;

public interface IBackground<Params, Progress, Result> {
    Result doInBackground(Params params, IProgress<Progress> progress);
}
