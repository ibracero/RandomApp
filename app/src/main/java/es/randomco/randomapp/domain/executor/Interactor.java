package es.randomco.randomapp.domain.executor;

public abstract class Interactor implements Runnable {

    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;

    public Interactor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public void executeInteractor() {
        interactorExecutor.run(this);
    }

    public void executeInMainThread(Runnable runnable) {
        mainThreadExecutor.execute(runnable);
    }

    public interface Callback<T> {
        void onSuccess(T response);
        void onError(Throwable throwable);
    }
}